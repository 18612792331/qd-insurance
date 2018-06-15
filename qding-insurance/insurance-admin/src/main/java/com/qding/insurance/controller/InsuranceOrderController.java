package com.qding.insurance.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qding.brick.pojo.category.Category;
import com.qding.brick.pojo.contract.ContractV2;
import com.qding.brick.remote.contract.ProductContractRemote;
import com.qding.brick.remote.ware.CategoryRemote;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.domain.GuaranteePlan;
import com.qding.insurance.domain.InsuranceOrder;
import com.qding.insurance.domain.InsuranceSku;
import com.qding.insurance.domain.InsuranceWare;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;
import com.qding.insurance.domain.WareSpecConf;
import com.qding.insurance.param.InsuranceOrderParam;
import com.qding.insurance.param.InsuranceWareParam;
import com.qding.insurance.param.SaveGuaranteePlanParam;
import com.qding.insurance.param.SaveSkuAndGuaranteeParam;
import com.qding.insurance.service.IGuaranteeService;
import com.qding.insurance.service.IInsuranceOrderService;
import com.qding.insurance.service.IInsuranceSkuService;
import com.qding.insurance.service.IInsuranceWareService;
import com.qding.insurance.service.ISpecService;
import com.qding.insurance.util.Constant;
import com.qding.insurance.vo.GuaranteeItemVo;
import com.qding.insurance.vo.InsuranceOrderDetailVo;
import com.qding.insurance.vo.InsuranceOrderPageResult;
import com.qding.insurance.vo.ModelResult;
import com.qding.insurance.vo.SpecVo;
import com.qding.manager.rpc.ManagerRPCService;

@RestController
@RequestMapping("/insuranceOrder")
public class InsuranceOrderController {

    Logger logger = Logger.getLogger(InsuranceOrderController.class);

    @Autowired
    private IInsuranceOrderService orderService;
    
    /**
     * 保险订单管理-列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult list(HttpServletRequest request, InsuranceOrderParam param) {
        
        ModelResult modelResult = new ModelResult();

        ResultPage<InsuranceOrderPageResult> resultPage = orderService.getResultPage(param);

        modelResult.setMessage("查询成功");
        modelResult.setResultPage(resultPage);
        
        return modelResult;
    }
    
    /**
     * 保险订单管理-详情
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelResult detail(HttpServletRequest request, @RequestParam String id) {
        
        ModelResult modelResult = new ModelResult();

        InsuranceOrderDetailVo orderDetailVo = orderService.getOrderDetailById(id);

        modelResult.setMessage("查询成功");
        modelResult.setEntity(orderDetailVo);
        
        return modelResult;
    }
    
    

}
