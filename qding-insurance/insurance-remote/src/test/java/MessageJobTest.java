import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.imessage.common.sender.IRemoteJobService;
import com.qding.imessage.common.struct.MsginfoRequest;
import com.qding.imessage.common.struct.MsginfoResponse;
import com.qding.insurance.vo.OrderPayMessage;

public class MessageJobTest {

    HessianProxyFactory factory = new HessianProxyFactory();

    private static IRemoteJobService remoteJobService;
    
    private MsginfoRequest msginfoRequest = null;

    @Before
    public void init() {
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            factory.setChunkedPost(false);
            remoteJobService = (IRemoteJobService) factory.create(IRemoteJobService.class,
                    "http://qaboss.qdingnet.com/imessage/remote/imessage");
            
            String tourl = "http://10.39.72.208:8080/insurance-remote/remote/imessage";
            
            msginfoRequest = new MsginfoRequest();
//            msginfoRequest.setName("模拟订单取消消息");
            msginfoRequest.setType(20);
            msginfoRequest.setIscallback(0);
            msginfoRequest.setTourl(tourl);
//            msginfoRequest.setToclass("OrderCancelMessageJob");
            msginfoRequest.setMaxRetrycount(0);// 重试次数
            msginfoRequest.setRetryInterval(6000L);// 单位: 毫秒
//            msginfoRequest.setMsgBody(paramJsonStr);
            // msginfoRequest.setTopicName("task_register");
            
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void OrderCancelTest() {

        String paramJsonStr = "{\"comment\":\"\",\"isReturnIntegral\":1,\"optName\":\"system\",\"orderCode\":\"NG17800011806081740208532\",\"orderStatus\":\"201\"}";

        msginfoRequest.setName("模拟订单取消消息");
        msginfoRequest.setToclass("OrderCancelMessageJob");
        msginfoRequest.setMsgBody(paramJsonStr);
        
        try {
            MsginfoResponse msginfoResponse = remoteJobService.SendImessage(msginfoRequest);
            System.out.println(msginfoResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void OrderPayedTest() {
        
        String paramJsonStr = "{\"isSplit\": 0,\"mid\": \"2c9180895a4a4a2f015a4a4e03780008\",\"orderCode\": \"BX06500011806121443575802\",\"payAt\": 1528368254672,\"payMid\": \"2c9180895a4a4a2f015a4a4e03780008\",rceType\": 0,"
            +"\"payTypeList\": [{\"orderPayType\": 41,\"payAmount\": \"0.01\",\"payType\": \"wx_gzh\"}],\"payeeId\": 0,\"productNo\": \"KD\",\"projectId\": 31602241129244,\"realpay\": \"0.01\","
            +"\"tradeStatus\": \"4\", \"tradeType\": \"41\",  \"userName\": \"千丁用户\", \"userPhone\": \"14444444443\"}";
        
        OrderPayMessage message = new OrderPayMessage();
        message.setOrderCode("BX06500011806121443575802");
        message.setPaySourceType("0");
        message.setPayAt("1528368254672");
        message.setRealpay("0.01");
        message.setTradeType("41");
        
        
        msginfoRequest.setName("模拟订单支付完成消息");
        msginfoRequest.setToclass("OrderPayedMessageJob");
        msginfoRequest.setMsgBody(JSONObject.toJSONString(message));
        
        try {
            MsginfoResponse msginfoResponse = remoteJobService.SendImessage(msginfoRequest);
            System.out.println(msginfoResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
