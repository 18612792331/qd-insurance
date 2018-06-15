import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.fastjson.JSONObject;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.dubbo.QDDubboProxyFactory;
import com.qding.insurance.rpc.IInsurancePolicyRpc;
import com.qding.insurance.rpc.request.FinishCompensateRequest;
import com.qding.insurance.rpc.request.IsInsuranceProductRequest;
import com.qding.insurance.rpc.request.ProductCompensateRequest;
import com.qding.insurance.rpc.response.IsInsuranceProductResponse;
import com.qding.insurance.rpc.response.ProductCompensateResponse;
import com.qding.insurance.rpc.response.UserPolicyListResponse;

public class IInsurancePolicyRpcTest {

	IInsurancePolicyRpc policyRpc;

	@Before
	public void init() {
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName("policyRpc-test");
		application.setOwner("");

		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setTimeout(10000);
		registry.setProtocol("zookeeper");
		registry.setAddress("10.37.251.221:2181,10.37.251.221:2182,10.37.251.222:2181");// dev
		// registry.setAddress("10.37.253.31:2181,10.37.253.31:2182,10.37.253.31:2183");//qa

		// QDDubboProxyFactory不能重用
		policyRpc = (IInsurancePolicyRpc) new QDDubboProxyFactory(application, registry)
				.create(IInsurancePolicyRpc.class, null);
	}


	// 根据房屋ID获取房屋投保状态 √
	@Test
	public void getRoomInsureStatusByRoomIDTest() throws Exception {

		BaseResponse roomInsureStatusByRoomID = policyRpc.getRoomInsureStatusByRoomID("888");
		System.err.println(JSONObject.toJSON(roomInsureStatusByRoomID));
	}

	// 根据MID获取用户保单列表 √
	@Test
	public void getUserInsurancePolicyListTest() throws Exception {

		UserPolicyListResponse userInsurancePolicyList = policyRpc.getUserInsurancePolicyList("MID003");
		System.err.println(JSONObject.toJSON(userInsurancePolicyList));
	}
	
	@Test
    public void isInsuranceProductTest() throws Exception {
        IsInsuranceProductRequest request = new IsInsuranceProductRequest();
        
        request.setRoomID("R11111");
        request.setProductId("p111");
        
        IsInsuranceProductResponse response = policyRpc.isInsuranceProduct(request);
        
        System.err.println(JSONObject.toJSON(response));
    }
	
	@Test
	public void productCompensateTest() throws Exception {
	    ProductCompensateRequest request = new ProductCompensateRequest();
	    
	    request.setRoomID("R11111");
	    request.setOrderNo("orderNo111113");
	    request.setApplyMoney("1000");
	    request.setProductId("p111");
	    request.setContactName("lyt");
	    request.setContactPhone("13411111");
	    request.setReportAt(new Date());
	    request.setAccidentMemo("测试事故");
	    request.setAccidentImgs("http://baidu.com");
	    
	    ProductCompensateResponse response = policyRpc.productCompensate(request);
	    
	    System.err.println(JSONObject.toJSON(response));
	}
	
	@Test
    public void cancelCompensateTest() throws Exception {
        FinishCompensateRequest request = new FinishCompensateRequest();
        
        request.setRoomID("R11111");
        request.setOrderCode("orderNo11111");
        
        BaseResponse response = policyRpc.cancelCompensate(request);
        
        System.err.println(JSONObject.toJSON(response));
    }
	@Test
	public void finishCompensateTest() throws Exception {
	    FinishCompensateRequest request = new FinishCompensateRequest();
	    
	    request.setRoomID("R11111");
	    request.setOrderCode("orderNo111113");
	    
	    BaseResponse response = policyRpc.finishCompensate(request);
	    
	    System.err.println(JSONObject.toJSON(response));
	}
	
	
}
