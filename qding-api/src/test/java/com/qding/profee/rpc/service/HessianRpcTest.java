package com.qding.profee.rpc.service;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.GetOrderDetailByCodeRequest;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;


public class HessianRpcTest {
    
    String legouUrl = "http://qaboss.qdingnet.com/legou/remote/legou";
    String roomUrl = "http://qaboss.qdingnet.com/brick/remote/v0/roomReadRemote";
    
    
    HessianProxyFactory factory = new HessianProxyFactory();
    
    ILegouRemoteService legouRpc;
    
    RoomReadRemote roomReadRemote;

    @Before
    public void init() throws MalformedURLException {
        factory.setChunkedPost(false);
        factory.setOverloadEnabled(true);
        legouRpc = (ILegouRemoteService) factory.create(ILegouRemoteService.class, legouUrl);
        roomReadRemote = (RoomReadRemote) factory.create(RoomReadRemote.class, roomUrl);
    }


    @Test
    public void getRoom(){
        
        Room room = roomReadRemote.get(713007L);
        System.out.println(JSON.toJSONString(room));
    }
    @Test
    public void getOrderDetailByCode(){
        
        GetOrderDetailByCodeRequest request = new GetOrderDetailByCodeRequest();
        request.setOrderCode("NG02100011706021623579662");

        GetOrderDetailByCodeResponse response = legouRpc.getOrderDetailByCode(request);
        System.out.println(JSON.toJSONString(response));
    }




}
