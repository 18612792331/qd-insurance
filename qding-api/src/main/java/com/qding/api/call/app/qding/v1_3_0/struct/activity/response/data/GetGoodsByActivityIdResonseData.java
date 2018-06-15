package com.qding.api.call.app.qding.v1_3_0.struct.activity.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.struct.ResponseData;

public class GetGoodsByActivityIdResonseData  extends ResponseData{

    
    private static final long serialVersionUID = 4471488084201946506L;
    
    
    /**
     * 商品信息列表
     */
    private List<Goods> list;

    public List<Goods> getList() {
        return list;
    }



    public void setList(List<Goods> list) {
        this.list = list;
    }



    public GetGoodsByActivityIdResonseData(List<Goods> list) {
        super();
        this.list = list;
    }
    
    public GetGoodsByActivityIdResonseData(){
        
    }
    
    @Override
    public String toString() {
        return "GetGoodsByActivityIdResonseData [list=" + list
                + ", toString()="+ super.toString() + "]";
    }
    

}
