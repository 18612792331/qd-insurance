package com.qding.profee.rpc.service;

import com.qding.api.util.CreateShortUrlUtil;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Create by jinhaishan on 18/3/6
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"
        ,"classpath:applicationContext-remote.xml"
       })
public class Test {

    @org.junit.Test
    public void name() {
        CreateShortUrlUtil.ServiceUrlConvertShortUrl("https://qam.iqdnet.com/translator?skip={'entity':{'skno':'2402','id':'20180306161358532303','source':'api','pcode':'0'}}", 24 * 60 * 60L);
        try {
            Thread.sleep(9000000000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
