package com.qding.api.call.app.qding.v3_3_0;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.EvaluateBody;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.EvaluateLable;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.LogisticsInfo;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.PackageNotice;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.PackageNoticeDetail;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.request.AddRoomEvaluateRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.request.EvaluateListRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.request.GetLogisticsInfoRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.request.GetPackageNoticeDetailRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.request.GetPackageNoticeDtoListRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.request.GetRoomEvaluateRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.request.LableListRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.response.AddRoomEvaluateResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.response.EvaluateListResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.response.GetLogisticsInfoResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.response.GetPackageNoticeDetailResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.response.GetPackageNoticeDtoListResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.response.GetRoomEvaluateResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.response.LableListResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.ConvertUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.evaluation.remote.qdapp.IQdAppRemote;
import com.qding.evaluation.struct.qdapp.bean.EvaluateFlagBean;
import com.qding.evaluation.struct.qdapp.bean.EvaluateSourcesBean;
import com.qding.evaluation.struct.qdapp.bean.EvaluteSourceBatchSaveBean;
import com.qding.evaluation.struct.qdapp.request.EvaluateFlagInfoRequest;
import com.qding.evaluation.struct.qdapp.request.EvaluateSourceBatchSaveRequest;
import com.qding.evaluation.struct.qdapp.request.EvaluateSourcesRequest;
import com.qding.evaluation.struct.qdapp.response.EvaluateFlagInfoResponse;
import com.qding.evaluation.struct.qdapp.response.EvaluateSourceBatchSaveResponse;
import com.qding.evaluation.struct.qdapp.response.EvaluateSourcesResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.realestate.dto.EvaluateBodyDto;
import com.qding.realestate.dto.LogisticsInfoDto;
import com.qding.realestate.dto.PackageNoticeDetailDto;
import com.qding.realestate.dto.PackageNoticeDto;
import com.qding.realestate.rpc.IEvaluateBodyRpc;
import com.qding.realestate.rpc.IRpcLogisticsService;
import com.qding.realestate.rpc.IRpcPackageNoticeService;

/**
 * @version V1.0
 * @Description: 龙湖地产入驻签约评价
 * @date 2017年12月4日 上午11:24:49
 */
@ExplainAnnotation(explain = "龙湖地产入驻")
public class CallRealestate extends Callable {

    @Resource
    private IQdAppRemote evaluationRemote;

    @Resource
    private IProfileService profileAPI;

    @Resource
    private ProjectReadRemote projectReadService;

    @Resource
    IEvaluateBodyRpc evaluateBodyRpc;

    @Resource
    private IRpcPackageNoticeService rpcPackageNoticeService;

    @Resource
    private IRpcLogisticsService rpcLogisticsService;

    @ExplainAnnotation(explain = "房屋评价服务")
    @HTTP(alias = "evaluateList", isRequireAuth = true)
    public Response<EvaluateListResponseData> evaluateList(EvaluateListRequest request, UserToken userToken) {

        Response<EvaluateListResponseData> response = new Response<EvaluateListResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        EvaluateListResponseData data = new EvaluateListResponseData();
        response.setData(data);
        try {
            MemberInfo memberInfo = getMemberInfo(userToken.getMemberId());
            List<EvaluateBodyDto> list = evaluateBodyRpc.getEvaluateBodyList(userToken.getMemberId(), memberInfo.getMobile());
            if (list != null && list.size() > 0) {
                List<EvaluateBody> listd = new ArrayList<EvaluateBody>();
                for (EvaluateBodyDto entity : list) {
                    EvaluateBody dto = new EvaluateBody();
                    dto.setBodyId(entity.getId());
                    dto.setBodyType(String.valueOf(entity.getStatus()));
                    dto.setRoomAddress(entity.getAddress());
                    EvaluateSourcesRequest req = new EvaluateSourcesRequest();
                    req.setSourceCode(dto.getBodyId());
                    req.setPageSize(1);
                    EvaluateSourcesResponse res = evaluationRemote.queryEvaluateSources(req);
                    if(res!=null && res.getList()!=null && res.getList().size()>0){
                    	dto.setStarScore(res.getList().get(0).getScore());
                    	dto.setStatus(1);
                    }
                    listd.add(dto);
                }
                data.setList(listd);
            }
        } catch (Exception e) {
            return handleException(EvaluateListResponseData.class, e);
        }
        return response;
    }

    @ExplainAnnotation(explain = "评价星级与标签联动列表")
    @HTTP(alias = "lableList", isRequireAuth = true)
    public Response<LableListResponseData> lableList(LableListRequest request, UserToken userToken) {

        Response<LableListResponseData> response = new Response<LableListResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        LableListResponseData data = new LableListResponseData();
        response.setData(data);
        try {
            EvaluateFlagInfoRequest evaluateFlagInfoRequest = new EvaluateFlagInfoRequest();
            if(request.getBodyType().equals("0")){
            	evaluateFlagInfoRequest.setProductNo(Constant.PRODUCT_NO_LHZY);
            }else{
            	evaluateFlagInfoRequest.setProductNo(Constant.PRODUCT_NO_LHRZ);
            }
            //获取某个业态下所有标签，app端做赛选
            EvaluateFlagInfoResponse evaluateFlagInfoResponse = evaluationRemote.queryFlagInfos(evaluateFlagInfoRequest);
            checkAndContinue(evaluateFlagInfoResponse);
            List<EvaluateFlagBean> list = evaluateFlagInfoResponse.getList();
            if (list != null && list.size() > 0) {
                List<EvaluateLable> listl = new ArrayList<EvaluateLable>();
                for (EvaluateFlagBean bean : list) {
                    EvaluateLable lable = new EvaluateLable();
                    lable.setLableId(bean.getFlagItem());
                    lable.setLableName(bean.getFlagName());
                    lable.setStarLevel(bean.getScore());
                    listl.add(lable);
                }
                data.setList(listl);
            }
        } catch (Exception e) {
            return handleException(LableListResponseData.class, e);
        }
        return response;
    }

    @ExplainAnnotation(explain = "添加入住签约评论")
    @HTTP(alias = "addRoomEvaluate", isRequireAuth = true)
    public Response<AddRoomEvaluateResponseData> addRoomEvaluate(AddRoomEvaluateRequest request, UserToken userToken) {
        Response<AddRoomEvaluateResponseData> response = new Response<AddRoomEvaluateResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        AddRoomEvaluateResponseData data = new AddRoomEvaluateResponseData();
        response.setData(data);
        try {
            MemberInfo memberInfo = getMemberInfo(userToken.getMemberId());

            Project project = projectReadService.get(Long.parseLong(request.getAppUser().getProjectId()));

            EvaluteSourceBatchSaveBean evaluteSourceBatchSaveBean = new EvaluteSourceBatchSaveBean();
            evaluteSourceBatchSaveBean.setOriginSourceCode(request.getBodyId());//订单号
            evaluteSourceBatchSaveBean.setSourceCode(request.getBodyId());//商品ID
            evaluteSourceBatchSaveBean.setUserId(memberInfo.getId());//会员ID
            evaluteSourceBatchSaveBean.setUserName(memberInfo.getName());//会员昵称
            evaluteSourceBatchSaveBean.setUserAccount(userToken.getAccountId());//账户ID
            evaluteSourceBatchSaveBean.setUserPhone(memberInfo.getMobile());//手机号
            evaluteSourceBatchSaveBean.setRegionId(project.getRegionId());//城市ID
            evaluteSourceBatchSaveBean.setRegionName(project.getRegionName());//城市名称
            evaluteSourceBatchSaveBean.setProjectId(project.getId());//社区ID
            evaluteSourceBatchSaveBean.setProjectName(project.getName());//社区名称
            
            if(request.getBodyType().equals("0")){
            	//业态号
            	evaluteSourceBatchSaveBean.setProductNo(Constant.PRODUCT_NO_LHZY);
            	evaluteSourceBatchSaveBean.setSourceName("龙湖签约评价");//商品名称
            	evaluteSourceBatchSaveBean.setProductValue("龙湖签约评价");//业态名称
            }else{
            	evaluteSourceBatchSaveBean.setProductNo(Constant.PRODUCT_NO_LHRZ);
            	evaluteSourceBatchSaveBean.setSourceName("龙湖入住评价");//商品名称
            	evaluteSourceBatchSaveBean.setProductValue("龙湖入住评价");//业态名称
            }
            evaluteSourceBatchSaveBean.setAnonymousFlag(0);//是否匿名
            evaluteSourceBatchSaveBean.setScore(request.getStarLevel());//评分
            evaluteSourceBatchSaveBean.setFlagItems(request.getLableIds());//评分标签索引
            evaluteSourceBatchSaveBean.setSourceValue(request.getContent());//评价内容
            evaluteSourceBatchSaveBean.setUserIp("0.0.0.0");//IP地址

            List<EvaluteSourceBatchSaveBean> evaluteList = Lists.newArrayList();
            evaluteList.add(evaluteSourceBatchSaveBean);
            EvaluateSourceBatchSaveRequest batchSaveRequest = new EvaluateSourceBatchSaveRequest();
            batchSaveRequest.setBeans(evaluteList);
            EvaluateSourceBatchSaveResponse batchSaveResponse = evaluationRemote.saveBatchEvaluateSource(batchSaveRequest);
            checkAndContinue(batchSaveResponse);
        } catch (Exception e) {
            return handleException(AddRoomEvaluateResponseData.class, e);
        }
        return response;
    }

    private MemberInfo getMemberInfo(String memberId) throws ServiceException {
        GetMemberRequest memberRequest = new GetMemberRequest();
        memberRequest.setMemberId(memberId);
        GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
        checkAndContinue(memberResponse);
        return memberResponse.getMemberInfo();
    }

    @ExplainAnnotation(explain = "获取入住签约评论")
    @HTTP(alias = "getRoomEvaluate", isRequireAuth = true)
    public Response<GetRoomEvaluateResponseData> getRoomEvaluate(GetRoomEvaluateRequest request, UserToken userToken) {
        Response<GetRoomEvaluateResponseData> response = new Response<GetRoomEvaluateResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetRoomEvaluateResponseData data = new GetRoomEvaluateResponseData();
        response.setData(data);
        try {
            EvaluateSourcesRequest req = new EvaluateSourcesRequest();
            req.setSourceCode(request.getBodyId());
            req.setPageSize(1);
            EvaluateSourcesResponse res = evaluationRemote.queryEvaluateSources(req);
            checkAndContinue(res);
            List<EvaluateSourcesBean> list = res.getList();
            if (list != null && list.size() > 0) {
                EvaluateSourcesBean bean = list.get(0);
                data.setContent(bean.getSourceValue());
                data.setStarLevel(bean.getScore());
                data.setStatus(1);
                List<String> temp = bean.getFlagNames();
                if (temp != null && temp.size() > 0) {
                    List<EvaluateLable> list1 = new ArrayList<EvaluateLable>();
                    for (String s : temp) {
                        EvaluateLable lable = new EvaluateLable();
                        lable.setLableName(s);
                        list1.add(lable);
                    }
                    data.setList(list1);
                }
            }
        } catch (Exception e) {
            return handleException(GetRoomEvaluateResponseData.class, e);
        }
        return response;
    }


    @ExplainAnnotation(explain = "获取入住通知包裹列表")
    @HTTP(alias = "getPackageNoticeDtoList", isRequireAuth = true)
    public Response<GetPackageNoticeDtoListResponseData> getPackageNoticeDtoList(GetPackageNoticeDtoListRequest request, UserToken userToken) {
        Response<GetPackageNoticeDtoListResponseData> response = new Response<GetPackageNoticeDtoListResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            MemberInfo memberInfo = getMemberInfo(userToken.getMemberId());
            List<PackageNoticeDto> packageNoticeDtoList = rpcPackageNoticeService.getPackageNoticeDtoList(memberInfo.getMobile());
            List<PackageNotice> packageNoticeList = Lists.transform(packageNoticeDtoList, new Function<PackageNoticeDto, PackageNotice>() {
                @Override
                public PackageNotice apply(PackageNoticeDto input) {
                    return ConvertUtil.copy(input, PackageNotice.class);
                }
            });
            GetPackageNoticeDtoListResponseData getPackageNoticeDtoListResponseData = new GetPackageNoticeDtoListResponseData(packageNoticeList);
            response.setData(getPackageNoticeDtoListResponseData);
        } catch (Exception e) {
            return handleException(GetPackageNoticeDtoListResponseData.class, e);
        }
        return response;
    }


    @ExplainAnnotation(explain = "获取入住通知包裹详情")
    @HTTP(alias = "getPackageNoticeDetailDto")
    public Response<GetPackageNoticeDetailResponseData> getPackageNoticeDetailDto(GetPackageNoticeDetailRequest request) {
        Response<GetPackageNoticeDetailResponseData> response = new Response<GetPackageNoticeDetailResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            PackageNoticeDetailDto packageNoticeDetailDto = rpcPackageNoticeService.getPackageNoticeDetailDto(request.getPackageNoticeId());
            PackageNotice packageNotice = ConvertUtil.copy(packageNoticeDetailDto.getPackageNoticeDto(), PackageNotice.class);
            PackageNoticeDetail packageNoticeDetail = ConvertUtil.copy(packageNoticeDetailDto, PackageNoticeDetail.class);
            packageNoticeDetail.setPackageNotice(packageNotice);
            GetPackageNoticeDetailResponseData getPackageNoticeDtoListResponseData = new GetPackageNoticeDetailResponseData(packageNoticeDetail);
            response.setData(getPackageNoticeDtoListResponseData);
        } catch (Exception e) {
            return handleException(GetPackageNoticeDetailResponseData.class, e);
        }
        return response;
    }

    @ExplainAnnotation(explain = "获取入住通知物流详情")
    @HTTP(alias = "getLogisticsInfoList")
    public Response<GetLogisticsInfoResponseData> getLogisticsInfoList(GetLogisticsInfoRequest request) {
        Response<GetLogisticsInfoResponseData> response = new Response<GetLogisticsInfoResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            List<LogisticsInfoDto> logisticsInfoDtoList = rpcLogisticsService.getLogisticsInfoDtoList(request.getPackageNumber(), request.getPackageName());
            List<LogisticsInfo> logisticsInfoList = Lists.transform(logisticsInfoDtoList, new Function<LogisticsInfoDto, LogisticsInfo>() {
                @Override
                public LogisticsInfo apply(LogisticsInfoDto input) {
                   return ConvertUtil.copy(input, LogisticsInfo.class);
                }
            });
            GetLogisticsInfoResponseData getLogisticsInfoResponseData = new GetLogisticsInfoResponseData(logisticsInfoList);
            response.setData(getLogisticsInfoResponseData);
        } catch (Exception e) {
            return handleException(GetLogisticsInfoResponseData.class, e);
        }
        return response;
    }
}
