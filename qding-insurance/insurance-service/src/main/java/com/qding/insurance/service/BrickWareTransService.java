package com.qding.insurance.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qding.brick.constants.ware.WareStatusEnum;
import com.qding.brick.domain.ware.qdadmin.OptUser;
import com.qding.brick.pojo.ware.Sku;
import com.qding.brick.pojo.ware.SkuExt;
import com.qding.brick.pojo.ware.SkuStock;
import com.qding.brick.pojo.ware.SkuStockConf;
import com.qding.brick.pojo.ware.Ware;
import com.qding.brick.pojo.ware.WareDesc;
import com.qding.brick.remote.ware.GeneralWareRemote;
import com.qding.core.common.Result;
import com.qding.insurance.domain.InsuranceSku;
import com.qding.insurance.domain.InsuranceWare;

@Service("brickWareTransService")
public class BrickWareTransService {
    
    private Logger logger = Logger.getLogger(BrickWareTransService.class);
    
    @Autowired
    private GeneralWareRemote wareRemote;
    
    /**
     * 保险商品同步到平台
     */
    public void transWare(InsuranceWare ware, List<InsuranceSku> skuList, String optRemark) throws Exception{
        
        Ware brickWare = new Ware();
        
        // 商品信息
        brickWare.setId(ware.getBrickWareId());
        brickWare.setCategoryId(ware.getCategoryId1()==null ? 0l : ware.getCategoryId1());
        brickWare.setCategoryId2(ware.getCategoryId2()==null ? 0l : ware.getCategoryId2());
        brickWare.setCategoryId3(ware.getCategoryId3()==null ? 0l : ware.getCategoryId3());
        brickWare.setName(ware.getTitle());
        brickWare.setProviderId(Long.parseLong(ware.getProviderId()));
        brickWare.setProviderName(ware.getProviderName());
        brickWare.setContractId(ware.getContractId());
        brickWare.setDescription(ware.getSubTitle());
        brickWare.setVersion(2); // 合同版本 2:2.0
        brickWare.setLastCategoryId(getLastCategory(ware.getCategoryId1(), ware.getCategoryId2(), ware.getCategoryId3()));
        brickWare.setSourceType(2); // 商品来源  2：千丁
        brickWare.setSupplierBn("QD"); // 商品对接平台  QD:千丁
        brickWare.setStatus(WareStatusEnum.PUBLISH);
        
        
        WareDesc wareDesc = new WareDesc();
        wareDesc.setKeywords(ware.getTitle());
        brickWare.setWareDesc(wareDesc);
        
        // sku信息
        List<Sku> skus = new ArrayList<Sku>();
        for(InsuranceSku insuranceSku : skuList){
            Sku sku = new Sku();
            sku.setWareName(insuranceSku.getSkuName());
            sku.setSpecial1(insuranceSku.getProjectType());
            sku.setSpecial2(insuranceSku.getStyleType());
            sku.setSpecial3(insuranceSku.getTimeType());
            sku.setIsMain(0);
            sku.setVersion(2);
            
            // 货品价格
            BigDecimal price = new BigDecimal(insuranceSku.getPrice());
            SkuExt skuExt = new SkuExt();
            skuExt.setLowPrice(price);
            skuExt.setUpPrice(price);
            skuExt.setMarketPrice(price);
            skuExt.setCostPrice(price);
            skuExt.setPrice(price);
            sku.setSkuExt(skuExt);
            
            // 货品库存
            List<SkuStock> stockList = new ArrayList<SkuStock>();
            SkuStock stock = new SkuStock();
            stock.setStock(9999L);
            stock.setUnlimited(1);
            stock.setStockType("project");
            
            // 库存配置
            List<SkuStockConf> stockConfList = new ArrayList<SkuStockConf>();
            SkuStockConf conf = new SkuStockConf();
            conf.setCityId(-1l);
            conf.setProjectId(-1l);
            stockConfList.add(conf);
            stock.setSkuStockConfs(stockConfList);
            
            stockList.add(stock);
            sku.setSkuStock(stockList);
            
            skus.add(sku);
        }
        brickWare.setSkus(skus);
        
        // 操作信息
        OptUser optUser = new OptUser();
        optUser.setOptUserId(ware.getCreateBy());
        optUser.setOptUserName(ware.getCreateBy());
        optUser.setOptRemark(optRemark);
        brickWare.setOptUser(optUser);
        
        logger.info("ware info to brick, wareId = "+ware.getId());
        
        Result result = null;
        
        if(ware.getBrickWareId()==null || ware.getBrickWareId() == 0L){
            result = wareRemote.addWare(brickWare,optUser);
        }else{
            result = wareRemote.updateWare(brickWare,optUser);
        }
        
        logger.info("ware info to brick, wareId = "+ware.getId()+", result = "+JSONObject.toJSONString(result));
        
        if(!result.isSuccess()){
            throw new Exception("同步商品到平台失败，message:["+result.getErrorMsg()+"]");
        }
        
        Ware resultWare = (Ware) result.get("RESULT_DEFAULT_MODEL");
        
        // 把平台ID复制到保险商品，后面会更新到数据库
        fillBrickId(resultWare,ware,skuList);
        
        // 直接上架
        publishWareByWareId(resultWare.getId(), optUser);
        
    }
    
    private void publishWareByWareId(Long wareId, OptUser optUser) throws Exception{
        
        optUser.setOptRemark("上架");
        List<Long> wareIds = new ArrayList<Long>();
        wareIds.add(wareId);
        Result result = wareRemote.publishWareByWareId(wareIds, optUser);
        if(!result.isSuccess()){
            throw new Exception("平台商品上架失败，message:["+result.getErrorMsg()+"]");
        }
    }
    
    /**
     * 复制平台商品ID 到 保险商品
     */ 
    private void fillBrickId(Ware resultWare, InsuranceWare ware, List<InsuranceSku> skuList){
        
        ware.setBrickWareId(resultWare.getId());
        
        List<Sku> brickSkuList = resultWare.getSkus();
        for(int i=0; i<brickSkuList.size(); i++){
            skuList.get(i).setBrickSkuId(brickSkuList.get(i).getId());
        }
    }
    
    
    private Long getLastCategory(Long c1,Long c2,Long c3){
        if(c3!=null && c3!=0L){
            return c3;
        }
        if(c2!=null && c2!=0L){
            return c2;
        }
        if(c1!=null && c1!=0L){
            return c1;
        }
        return 0L;
    }
}
  
