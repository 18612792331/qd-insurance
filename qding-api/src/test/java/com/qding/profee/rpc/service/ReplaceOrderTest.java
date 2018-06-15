package com.qding.profee.rpc.service;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.profee.SearchParams.PropertyAccountDetailParams;
import com.qding.profee.SearchParams.PropertyWithdrawParams;
import com.qding.profee.enums.PropertyAccountTypeEnum;
import com.qding.profee.rpc.request.fee.*;
import com.qding.profee.rpc.response.fee.*;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * 代缴接口测试
 * Created by xiexiyang on 15/10/27.
 */
public class ReplaceOrderTest {
    String url = "http://devboss.qdingnet.com/profee-remote/remote/feeRpc";
    //String url = "http://localhost:8080/profee-remote/remote/feeRpc";
    HessianProxyFactory factory = new HessianProxyFactory();
    IFeeRpcService rpcService;


    @Before
    public void init() throws MalformedURLException {
        factory.setChunkedPost(false);
        factory.setOverloadEnabled(true);
        rpcService = (IFeeRpcService) factory.create(IFeeRpcService.class, url);
    }


    @Test
    public void getReplaceCustList(){
        GetReplaceCustListRequest request = new GetReplaceCustListRequest();
        request.setMemberId("s1ac2ec978a011e58a3044a8421ae0af");
        request.setProjectId(62L);
        request.setRoomId(514458L);

        GetReplaceCustListResponse response =  rpcService.getReplaceCustList(request);
        System.out.println(JSON.toJSONString(response));
    }


    @Test
    public void getReplaceFees(){
        GetReplaceFeeRequest request = new GetReplaceFeeRequest();
        request.setMemberId("s1ac2ec978a011e58a3044a8421ae0af");
        request.setProjectId(62L);
        request.setRoomId(514458L);
        request.setCustId(10000200000016L);

        GetReplaceFeeResponse response =  rpcService.getReplaceFees(request);
        System.out.println(JSON.toJSONString(response));
    }



    @Test
    public void createReplaceFeeOrder(){
        ReplaceFeeCreateOrderRequest request = new ReplaceFeeCreateOrderRequest();
        request.setMemberId("s1ac2ec978a011e58a3044a8421ae0af");
        request.setIsSupportCoupon(1);
        request.setIsLongHu(true);
        request.setProjectId("62");
        request.setProjectName("水晶郦城");
        request.setRegionId("3");
        request.setRegionName("重庆市");
        request.setRoomId("514458");
        request.setRoomCode("LC101-1-0404");
        request.setRoomName("重庆市水晶郦城1栋1-0102");
        request.setSourceType(0);
        request.setFeeOwnersCustid("10000200000014");
        FeeCreateOrderResponse response =  rpcService.createReplaceFeeOrder(request);
        System.out.println(JSON.toJSONString(response));
    }


}
