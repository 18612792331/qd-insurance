package com.qding.insurance.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qding.brick.pojo.category.Category;
import com.qding.brick.pojo.contract.ContractV2;
import com.qding.brick.remote.contract.ProductContractRemote;
import com.qding.brick.remote.ware.CategoryRemote;
import com.qding.core.common.util.DateUtil;
import com.qding.framework.common.AppUser;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.domain.GuaranteeItem;
import com.qding.insurance.domain.GuaranteePlan;
import com.qding.insurance.domain.InsuranceProject;
import com.qding.insurance.domain.InsuranceSku;
import com.qding.insurance.domain.InsuranceWare;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;
import com.qding.insurance.domain.WareSpecConf;
import com.qding.insurance.param.InsuranceWareParam;
import com.qding.insurance.param.SaveGuaranteePlanParam;
import com.qding.insurance.param.SaveSkuAndGuaranteeParam;
import com.qding.insurance.param.SaveWareParam;
import com.qding.insurance.service.IGuaranteeService;
import com.qding.insurance.service.IInsuranceSkuService;
import com.qding.insurance.service.IInsuranceWareService;
import com.qding.insurance.service.ISpecService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.GuaranteeItemVo;
import com.qding.insurance.vo.InsuranceDetailVo;
import com.qding.insurance.vo.ModelResult;
import com.qding.insurance.vo.SpecVo;
import com.qding.manager.rpc.ManagerRPCService;

@RestController
@RequestMapping("/insuranceWare")
public class InsuranceWareController {

    Logger logger = Logger.getLogger(InsuranceWareController.class);

    @Autowired
    private IInsuranceWareService insuranceService;
    
    @Autowired
    private ProductContractRemote contractRemote;
    
    @Autowired
    private ISpecService specService;
    
    @Autowired
    private CategoryRemote categoryRemote;
    
    @Value("#{configproperties_disconf[bx_category_id]}")
    private String categoryId;
    
    @Autowired
    private IInsuranceSkuService skuService;
    
    @Autowired
    private IGuaranteeService guaranteeService;
    

    /**
     * 保险商品管理-详情
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult detail(HttpServletRequest request, String wareId) {
        
        ModelResult modelResult = new ModelResult();
        InsuranceDetailVo detailVo = new InsuranceDetailVo();
        
        // 商品信息
        InsuranceWareExtWithBLOBs ware = insuranceService.getExtById(wareId);
        detailVo.setWare(ware);
        
        // 规格选项(含已选)
        List<SpecVo> specList = specService.getAllSpecWithSelected(wareId);
        detailVo.setSpecList(specList);
        
        // SKU列表
        List<InsuranceSku> skuList = skuService.getWareSku(wareId);
        detailVo.setSkuList(skuList);
        
        // 保障内容列表
        List<GuaranteeItemVo> guaranteeList = guaranteeService.getWareGuarantee(wareId);
        detailVo.setGuaranteeList(guaranteeList);
        
        // 保障计划
        List<GuaranteePlan> planList = guaranteeService.getWarePlan(wareId);
        detailVo.setPlanList(planList);
        
        // 保障内容为按金额理赔的保障计划，权益值由元转化为万元
        changeRightValue(planList, guaranteeList);
        
        List<InsuranceProject> projectList = insuranceService.getWareProject(wareId);
        detailVo.setProjectList(projectList); 

        modelResult.setMessage("查询成功");
        modelResult.setEntity(detailVo);
        
        return modelResult;
    }
    
    /**
     * 保障内容为按金额理赔的保障计划，权益值由元转化为万元
     */
    private void changeRightValue(List<GuaranteePlan> planList, List<GuaranteeItemVo> guaranteeList){
        if(CollectionUtils.isEmpty(planList) || CollectionUtils.isEmpty(guaranteeList)){
            return;
        }
        
        for(GuaranteePlan plan : planList){
            for(GuaranteeItemVo itemVo : guaranteeList){
                if(plan.getGuaranteeItemId().equals(itemVo.getId()) && itemVo.getCompensateType()==Constant.COMPENSATERECORD_COMPENSATETYPE_MONEY){
                    plan.setRightValue(new BigDecimal(plan.getRightValue()).divide(new BigDecimal(10000)).toString());
                }
            }
        }
    }
    
    
    /**
     * 保险商品管理-列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult list(HttpServletRequest request, InsuranceWareParam param) {
        
        ModelResult modelResult = new ModelResult();

        ResultPage<InsuranceWare> resultPage = insuranceService.getResultPage(param);

        modelResult.setMessage("查询成功");
        modelResult.setResultPage(resultPage);
        
        return modelResult;
    }
    
    
    /**
     * 保险商品管理-商品信息保存（基础信息+详情信息+其他介绍）
     * ID为空，视为新增；ID非空，视为修改
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ModelResult save(HttpServletRequest request, @RequestBody SaveWareParam param) {
        
        ModelResult modelResult = new ModelResult();
        
        // 完善合同、供方信息
        try {
            fillContractInfo(param);
        } catch (Exception e) {
            logger.error("contract error, contractCode = "+param.getContractId(),e); 
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("关联合同错误");
            return modelResult;
        }
        
        Date now = new Date();
        AppUser appUser = ManagerRPCService.getCurrentUserInfo();
        
        // 完善时间、状态、操作人等信息
        param.setStatus(Constant.WARE_STATUS_UNAUDIT);
        param.setUpdateTime(now);
        param.setUpdateBy(appUser.getUsername());
        
        param.setOnlineTime(StringUtils.isNotEmpty(param.getOnlineTimeStr()) ? DateUtil.strToDate(param.getOnlineTimeStr(), Constant.DATE_FORMAT) : null);
        param.setOfflineTime(StringUtils.isNotEmpty(param.getOfflineTimeStr()) ? DateUtil.strToDate(param.getOfflineTimeStr(), Constant.DATE_FORMAT) : null);
        
        // 新增
        if(StringUtils.isEmpty(param.getId())){
            param.setId(Constant.ID_GENERATOR.generate());
            param.setCreateAt(now);
            param.setCreateBy(appUser.getUsername());
            param.setCategoryId1(Long.parseLong(categoryId));
            insuranceService.add(param);
        }else{
            insuranceService.update(param);
        }
        
        modelResult.setMessage("保存成功");
        modelResult.setEntity(param);
        
        return modelResult;
    }
    
    /**
     * 完善合同、供方信息
     * @throws Exception 
     */
    protected void fillContractInfo(InsuranceWareExtWithBLOBs param) throws Exception{
        // 完善合同、供方信息
        if(StringUtils.isNotEmpty(param.getContractId())){
            ContractV2 contractV2 = contractRemote.getContractDetailByCode_20(param.getContractId());
            param.setContractName(contractV2.getName());
            param.setProviderId(contractV2.getSupplierId()+"");
            param.setProviderName(contractV2.getSupplierName());
        }
    }
    
    /**
     * 商品规格选项列表
     */
    @RequestMapping(value = "allSpec", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult allSpec(HttpServletRequest request) {
        
        ModelResult modelResult = new ModelResult();

        List<SpecVo> list = specService.getAllSpec();

        modelResult.setMessage("查询成功");
        modelResult.setList(list);
        
        return modelResult;
    }
    
    /**
     * 保险品类
     */
    @RequestMapping(value = "category", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult category(HttpServletRequest request, String parentId) {
        
        ModelResult modelResult = new ModelResult();
        
        parentId = StringUtils.isEmpty(parentId) ? categoryId : parentId;
        
        List<Long> idlist = new ArrayList<Long>();
        idlist.add(Long.parseLong(parentId));
        
        List<Category> list = categoryRemote.getSelectSubCategory(idlist);
        
        modelResult.setMessage("查询成功");
        modelResult.setList(list);
        return modelResult;
    }
    
    /**
     * 保险商品管理-保存 商品规格、SKU、保障内容
     */
    @RequestMapping(value = "saveSkuAndGuarantee", method = RequestMethod.POST)
    @ResponseBody
    public ModelResult saveSkuAndGuarantee(HttpServletRequest request, @RequestBody SaveSkuAndGuaranteeParam param) {
        
        ModelResult modelResult = new ModelResult();
        
        if(StringUtils.isBlank(param.getWareId())){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("商品ID为空");
            return modelResult;
        }
        
        // 保存已选商品规格
        saveWareSpecConf(param.getWareId(), param.getSpecCodes());
        
        // 保存SKU
        saveSku(param.getWareId(),param.getSkuList());
        
        // 保障内容
        saveGuarantee(param.getWareId(),param.getGuaranteeItemList());
        
        modelResult.setMessage("保存成功");
        modelResult.setEntity(param);
        
        return modelResult;
    }
    
    protected void saveGuarantee(String wareId,List<GuaranteeItemVo> guaranteeItemList){
        if(CollectionUtils.isNotEmpty(guaranteeItemList)){
            for(GuaranteeItemVo guaranteeItemVo : guaranteeItemList){
                guaranteeItemVo.setId(Constant.ID_GENERATOR.generate());
                guaranteeItemVo.setWareId(wareId);
            }
            guaranteeService.addGuarantee(guaranteeItemList);
        }
    }
    
    protected void saveSku(String wareId,List<InsuranceSku> skuList){
        
        if(CollectionUtils.isNotEmpty(skuList)){
            for(InsuranceSku sku : skuList){
                sku.setId(Constant.ID_GENERATOR.generate());
                sku.setWareId(wareId);
            }
            skuService.addInsuranceSku(skuList);
        }
    }
    
    protected void saveWareSpecConf(String wareId, String specCodes){
        
        if(StringUtils.isNotBlank(specCodes)){
            
            List<WareSpecConf> toAddList = new ArrayList<WareSpecConf>();
            WareSpecConf conf = null;
            
            for(String code : specCodes.split(",")){
                conf = new WareSpecConf();
                conf.setId(Constant.ID_GENERATOR.generate());
                conf.setSpecValueCode(code);
                conf.setWareId(wareId);
                toAddList.add(conf);
            }
            
            specService.addWareSpecConf(toAddList);
        }
    }
    
    /**
     * 保险商品管理-保存保障计划
     */
    @RequestMapping(value = "saveGuaranteePlan", method = RequestMethod.POST)
    @ResponseBody
    public ModelResult saveGuaranteePlan(HttpServletRequest request, @RequestBody SaveGuaranteePlanParam param) {
        
        ModelResult modelResult = new ModelResult();
        
        if(StringUtils.isBlank(param.getWareId())){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("商品ID为空");
            return modelResult;
        }
        
        for(GuaranteePlan plan : param.getPlanList()){
            plan.setId(Constant.ID_GENERATOR.generate());
            plan.setWareId(param.getWareId());
            
            // 如果保障内容的理赔方式为按金额，权益值由万元转换为元
            GuaranteeItem item = guaranteeService.getItemById(plan.getGuaranteeItemId());
            if(item == null){
                modelResult.setCode(ModelResult.CODE_VALID_ERROR);
                modelResult.setMessage("保障内容ID错误");
                return modelResult;
            }
            if(item.getCompensateType() == Constant.COMPENSATERECORD_COMPENSATETYPE_MONEY){
                plan.setRightValue(new BigDecimal(plan.getRightValue()).multiply(new BigDecimal(10000)).toString());
            }
        }
        
        guaranteeService.addGuaranteePlan(param.getPlanList());
        
        modelResult.setMessage("保存成功");
        
        return modelResult;
    }
    
    
    
    /**
     * 保险商品管理-提交审核
     *  wareId 商品ID
     */
    @RequestMapping(value = "toAudit", method = RequestMethod.POST)
    @ResponseBody
    public ModelResult toAudit(HttpServletRequest request, String wareId) {
        
        ModelResult modelResult = new ModelResult();
        
        InsuranceWare ware = insuranceService.getById(wareId);
        
        if(ware.getStatus() != Constant.WARE_STATUS_UNAUDIT){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("商品当前状态不可提交审核");
            return modelResult;
        }
        
        Date now = new Date();
        String userName = ManagerRPCService.getCurrentUserInfo().getUsername();
        
        ware.setStatus(Constant.WARE_STATUS_TOAUDIT);
        ware.setToAuditAt(now);
        ware.setUpdateTime(now);
        ware.setToAuditBy(userName);
        ware.setUpdateBy(userName);
        
        insuranceService.update(ware);
        
        modelResult.setMessage("操作成功");
        
        return modelResult;
    }
    /**
     * 保险商品管理-审核
     * wareId 商品ID
     * isPass 是否审核通过，0-不通过，1-通过
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    @ResponseBody
    public ModelResult audit(HttpServletRequest request, String wareId, String isPass) {
        
        ModelResult modelResult = new ModelResult();
        
        InsuranceWare ware = insuranceService.getById(wareId);
        
        if(ware.getStatus() != Constant.WARE_STATUS_TOAUDIT){
            modelResult.setCode(ModelResult.CODE_VALID_ERROR);
            modelResult.setMessage("商品当前状态不可审核");
            return modelResult;
        }
        
        Date now = new Date();
        String userName = ManagerRPCService.getCurrentUserInfo().getUsername();
        
        ware.setAuditAt(now);
        ware.setUpdateTime(now);
        ware.setAuditBy(userName);
        ware.setUpdateBy(userName);
        Integer toStatus = Constant.YES_STRING.equals(isPass) ? Constant.WARE_STATUS_AUDIT_OK : Constant.WARE_STATUS_AUDIT_FAIL;
        ware.setStatus(toStatus);
        
        insuranceService.update(ware);
        
        modelResult.setMessage("操作成功");
        
        return modelResult;
    }
    
    

}
