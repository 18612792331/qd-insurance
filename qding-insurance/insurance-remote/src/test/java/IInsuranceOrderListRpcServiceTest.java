

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.qding.framework.common.dubbo.QDDubboProxyFactory;
import com.qding.framework.common.service.order.ProductOrderBean;
import com.qding.insurance.rpc.IInsuranceOrderListRpcService;

public class IInsuranceOrderListRpcServiceTest {
	IInsuranceOrderListRpcService insuranceOrderListRpcService;


	@Before
	public void init() {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("orderRpc-test");
        application.setOwner("");
 
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setTimeout(10000);
        registry.setProtocol("zookeeper");
        registry.setAddress(" 10.37.251.221:2181,10.37.251.221:2182,10.37.251.222:2181");//dev
//        registry.setAddress("10.37.253.31:2181,10.37.253.31:2182,10.37.253.31:2183");//qa
 
        // QDDubboProxyFactory不能重用
        insuranceOrderListRpcService = (IInsuranceOrderListRpcService) new QDDubboProxyFactory(application, registry).create(IInsuranceOrderListRpcService.class,null);
    }

	@Test
	public void testGetProductOrder() {
		List<String> list =new ArrayList<String>();
		list.add("BX06500011803232324357580");
		list.add("BX06500011806141308445362");
		list.add("NG17800011806081740208532");
		list.add("BX06500011806120943239742");
		list.add("BD20180601666668");
		
		
		List<ProductOrderBean> resultList= insuranceOrderListRpcService.getProductOrder(list);
		System.out.println(resultList.size());
	}

	@Test
	public void testGetGoodsJson() {
		Long skuId = 815525L;
    	String result = insuranceOrderListRpcService.getGoodsJson(skuId);
    	System.out.println(result);
	}

}
