package com.qding.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.framework.common.service.order.ProductOrderBean;
import com.qding.insurance.dao.InsuranceSkuMapper;
import com.qding.insurance.domain.InsuranceSku;
import com.qding.insurance.service.IInsuranceOrderService;
import com.qding.insurance.service.IInsuranceSkuService;
import com.qding.insurance.vo.InsuranceDetailByOrderNoVo;

@Service("insuranceSkuService")
public class InsuranceSkuServiceImpl implements IInsuranceSkuService{
    
    @Autowired
    private InsuranceSkuMapper skuMapper;
    
    @Autowired
    private IInsuranceOrderService insuranceOrderService;
    
    @Override
    public void addInsuranceSku(List<InsuranceSku> list) {
        skuMapper.deleteByWareId(list.get(0).getWareId());
        skuMapper.batchInsert(list);
    }

    @Override
    public List<InsuranceSku> getWareSku(String wareId) {
        return skuMapper.selectByWareId(wareId);
    }

	@Override
	public InsuranceSku getById(String id) {
		return skuMapper.selectByPrimaryKey(id);
	}

	@Override
	public InsuranceSku getByBrickSkuId(String skuId) {
		return skuMapper.getByBrickSkuId(skuId);
	}
    
    
	/**
     * * 通过订单号批量获取订单状态先关信息
     * @param orderCodes  订单号列表
     * @return
     * 订单状态信息Bean的List
     */
	@Override
	public List<ProductOrderBean> getProductOrder(List<String> orderCodes) {
		List<ProductOrderBean> list = new ArrayList<ProductOrderBean>();
		if(orderCodes.size() > 0){
			for(String orderNo : orderCodes){
				ProductOrderBean productOrderBean = new ProductOrderBean();
				InsuranceDetailByOrderNoVo insuranceOrder = insuranceOrderService.getInsOderDetailByOrderNo(orderNo);
				if(insuranceOrder != null){
					productOrderBean.setDesc("");
					productOrderBean.setImgs(buildImgs(insuranceOrder));
					productOrderBean.setOrderCode(insuranceOrder.getOrderNo());
					productOrderBean.setOrderId(insuranceOrder.getId());
					productOrderBean.setOrderStatus(insuranceOrder.getStatus());
					productOrderBean.setStatusName(insuranceOrderStatusTransfer(String.valueOf(insuranceOrder.getStatus())));
					productOrderBean.setTitle(insuranceOrder.getTitle());
					list.add(productOrderBean);
				}
				
			}
		}
		return list;
	}

	/**
	 * 构建订单图片地址
	 * @param insuranceOrder
	 * @return
	 */
	private String[] buildImgs(InsuranceDetailByOrderNoVo insuranceOrder){
				if(StringUtils.isNotBlank(insuranceOrder.getDetailImage())){
					return	(insuranceOrder.getImage()+","+insuranceOrder.getDetailImage()).split(",");
				} else {
					return new String[]{insuranceOrder.getImage()};
				}
		
	}
	
	/**
	 * 保险订单状态转换
	 * 订单状态, 1：未支付，2：已支付，3：已完成，4：已取消
	 * @param statusCode
	 * @return
	 */
    private String insuranceOrderStatusTransfer(String statusCode){
    	if("1".equals(statusCode)){
    		return "未支付";
    	} else if("2".equals(statusCode)){
    		return "已支付";
    	} else if("3".equals(statusCode)){
    		return "已完成";
    	} else if("4".equals(statusCode)){
    		return "已取消";
    	} else {
    		return "未知";
    	}
    	
    }
    
}
  
