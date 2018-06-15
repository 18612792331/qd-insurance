package com.qding.api.call.app.qding.v2_4_0;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.*;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.request.GetMallIndexRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.request.GetModuleDetailRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.response.data.GetMallAllCategoryResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.response.data.GetMallIndexResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.response.data.GetMallModuleDetailResponseData;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.sysconfig.dto.MallManagementModuleDto;
import com.qding.sysconfig.dto.MallManagementModuleRpcDto;
import com.qding.sysconfig.dto.MallProjectCategoryRecommendDto;
import com.qding.sysconfig.rpc.service.MallModuleRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class CallSysConfig extends com.qding.api.call.app.qding.v2_1_0.CallSysConfig {

    @Autowired
    private MallModuleRpcService mallModuleRpcService;

    @HTTP(alias = "getMallIndex")
    @ExplainAnnotation(explain = "商城首页")
    public Response<GetMallIndexResponseData> getMallIndex(GetMallIndexRequest request) {
        try {
            Response<GetMallIndexResponseData> response = new Response<GetMallIndexResponseData>();
            GetMallIndexResponseData data = new GetMallIndexResponseData();
            MallIndex entity = new MallIndex();
            List<MallProjectCategoryRecommendDto> categoryList = mallModuleRpcService.getCategoryListLastest(Long.parseLong(request.getProjectId()));
            List<CategoryInfo> categoryInfoList = transforList(CategoryInfo.class, categoryList);
            entity.setCategoryInfoList(categoryInfoList);//品类
            //来源0-banner,1-栏目,2-推荐
            List<MallManagementModuleRpcDto> bannerList = mallModuleRpcService.getManagermentModeleList(Long.parseLong(request.getProjectId()), 6, 0);//banner
            List<BannerInfo> bannerInfoList = transforList(BannerInfo.class, bannerList);
            entity.setBannerInfoList(bannerInfoList);

            List<MallManagementModuleRpcDto> itemList = mallModuleRpcService.getManagermentModeleList(Long.parseLong(request.getProjectId()), null, 1);//栏目
            List<ItemInfo> itemInfoList = transforList(ItemInfo.class, itemList);
            entity.setItemInfoList(itemInfoList);

            List<MallManagementModuleRpcDto> recommendList = mallModuleRpcService.getManagermentModeleList(Long.parseLong(request.getProjectId()), null, 2);//推荐
            List<RecommendInfo> recommendInfoList = transforList(RecommendInfo.class, recommendList);
            entity.setRecommendInfoList(recommendInfoList);

            response.setCode(HttpStatus.OK.getStatusCode());
            data.setEntity(entity);
            response.setData(data);
            return response;
        } catch (Exception ex) {
            return handleException(GetMallIndexResponseData.class, ex);
        }
    }

    @HTTP(alias = "getMallModuleDetail")
    @ExplainAnnotation(explain = "模块详情页")
    public Response<GetMallModuleDetailResponseData> getMallModuleDetail(GetModuleDetailRequest request) {
        try {
            Response<GetMallModuleDetailResponseData> response = new Response<GetMallModuleDetailResponseData>();
            GetMallModuleDetailResponseData data = new GetMallModuleDetailResponseData();
            MallManagementModuleRpcDto dto = mallModuleRpcService.getManagermentModeleDetailById(request.getId(), Long.parseLong(request.getProjectId()));
            ModuleInfo entity = transfor(ModuleInfo.class, dto);
            data.setEntity(entity);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        } catch (Exception ex) {
            return handleException(GetMallModuleDetailResponseData.class, ex);
        }
    }


    @HTTP(alias = "getMallDetail")
    @ExplainAnnotation(explain = "获取全部品类")
    public Response<GetMallAllCategoryResponseData> getMallAllCategory(GetMallIndexRequest request) {
        try {
            Response<GetMallAllCategoryResponseData> response = new Response<GetMallAllCategoryResponseData>();
            GetMallAllCategoryResponseData data = new GetMallAllCategoryResponseData();
            List<MallProjectCategoryRecommendDto> list = mallModuleRpcService.getCategoryListAll(Long.parseLong(request.getProjectId()));
            List<CategoryInfo> categoryInfoList = transforList(CategoryInfo.class, list);
            data.setCategoryInfoList(categoryInfoList);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        } catch (Exception ex) {
            return handleException(GetMallAllCategoryResponseData.class, ex);
        }
    }
}
