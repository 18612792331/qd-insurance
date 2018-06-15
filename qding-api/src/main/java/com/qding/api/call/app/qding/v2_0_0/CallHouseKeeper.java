package com.qding.api.call.app.qding.v2_0_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.Report;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.ApplyReportRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.ApplyReportResponseData;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.Notice;
import com.qding.api.call.app.qding.v2_0_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v2_0_0.struct.brick.ProjectServiceList;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.request.GetHKIndexRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.request.GetMyReportByIdRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.request.GetNoticeByIdRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.request.GetProjectNoticeRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.response.data.GetHKIndexResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.response.data.GetMyReportByIdResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.response.data.GetNoticeByIdResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.response.data.GetProjectNoticeResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.QDMemberRemote;
import com.qding.api.util.QDVersionUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hk.domain.MatterApply;
import com.qding.hk.domain.ResMatterApply;
import com.qding.hk.domain.ResNotice;
import com.qding.hk.rpc.request.matter.MatterApplyRequest;
import com.qding.hk.rpc.request.notice.GetNoticeInfoRequest;
import com.qding.hk.rpc.request.notice.GetNoticeListRequest;
import com.qding.hk.rpc.response.matter.MatterApplyResponse;
import com.qding.hk.rpc.response.notice.GetNoticeInfoResponse;
import com.qding.hk.rpc.response.notice.GetNoticeListResponse;
import com.qding.hk.rpc.service.IMatterRpcService;
import com.qding.hk.rpc.service.INoticeRpcService;
import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetSelfRoomResponse;
import com.qding.passport.service.IPassportService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetAccountRequest;
import com.qding.passport.struct.response.GetAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 管家
 */
public class CallHouseKeeper extends com.qding.api.call.app.qding.v1_4_1.CallHouseKeeper {

    @Autowired
    private AppConfigRemote appConfigRemote;

    @Autowired
    private INoticeRpcService noticeService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private IMatterRpcService matterService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private IPassportService passportService;

    @Autowired
    private RoomReadRemote roomReadService;

    @Autowired
    private ProjectReadRemote projectService;


    /**
     * 管家首页
     *
     * @param request
     * @return
     */
    @HTTP(alias = "index")
    @ExplainAnnotation(explain = "管家首页")
    @Deprecated
    public Response<GetHKIndexResponseData> index(GetHKIndexRequest request) {
        try {
            Response<GetHKIndexResponseData> response = new Response<GetHKIndexResponseData>();

            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());

            Long projectId = Long.parseLong(request.getProjectId());

            /**
             * 社区首页业态
             */
            String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());

            /**
             *
             * @param projectId 项目ID (必传)
             * @param code 分类编码  (可选,不传)
             * @param version 版本号 (如果是APP，此参数必传 ,44)
             * @param type  1 微信 2APP3支付宝4PAD  (必传)
             * @return
             */
            List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId_20(projectId, null, version, brickSourceType);

            List<ProjectService> services = transforList(ProjectService.class, serviceItems);

            List<ProjectServiceList> list = Lists.newArrayList();
            List<String> serviceTypeList = Lists.newArrayList();
            for (ProjectService service : services) {
                //遍历service并分组
                String serviceType = String.valueOf(service.getServiceType());
                if (!serviceTypeList.contains(serviceType)) {
                    serviceTypeList.add(serviceType);
                }
            }
            for (String type : serviceTypeList) {
                ProjectServiceList projectServiceList = new ProjectServiceList();
                List<ProjectService> servicesGroup = Lists.newArrayList();
                String title = "";
                for (ProjectService service : services) {
                    if (service.getServiceType() != null && service.getServiceType().equals(type)) {
                        servicesGroup.add(service);
                        title = service.getServiceTypeName();
                    }
                }

                projectServiceList.setServices(servicesGroup);
                projectServiceList.setTitle(title);
                list.add(projectServiceList);
            }
            Project project = projectReadService.get(Long.parseLong(request.getProjectId()));
            List<String> phones = fittingHouseKeeperPhone(project);
            response.setData(new GetHKIndexResponseData(list, phones));
            return response;

        } catch (Exception e) {
            return handleException(GetHKIndexResponseData.class, e);
        }
    }


    /**
     * 组装管家电话
     *
     * @param project
     * @return
     * @throws ServiceException
     */
    public List<String> fittingHouseKeeperPhone(Project project) throws ServiceException {
        if (project == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
        }
        com.qding.api.call.app.qding.v1_3_0.struct.brick.Project p =
                transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, project);
        Set<String> phoneSet = new HashSet<String>();

        List<ProjectConcat> projectConcats = p.getConcats();
        for (ProjectConcat projectConcat : projectConcats) {
            if ("1".equals(projectConcat.getType())) {
                String[] phoneStrings = projectConcat.getPhones();
                for (String phone : phoneStrings) {
                    phoneSet.add(phone);
                }
            }
        }
        List<String> phoneList = new ArrayList<String>();
        if (phoneSet.size() > 0) {
            phoneList.addAll(phoneSet);
        }
        return phoneList;
    }

    /**
     * 根据社区id查询公告列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getProjectNotices")
    @ExplainAnnotation(explain = "根据社区id查询公告列表")
    public Response<GetProjectNoticeResponseData> getProjectNotices(GetProjectNoticeRequest request, UserToken userToken) {

        GetProjectNoticeResponseData data = new GetProjectNoticeResponseData ();
        try {
            String version = request.getAppDevice().getQdVersion();
            Response<GetProjectNoticeResponseData> response = new Response<GetProjectNoticeResponseData>();
            GetNoticeListRequest noticeRequest = transfor(GetNoticeListRequest.class, request);
            //2.7版本之前社区公告
            // TODO: 2016/11/17 2.8版本改为list方式传递类型
            String initVersion = skipMode.initVersion(version);
            if(Integer.parseInt(initVersion)>=280000) { //如果是2.8版本
                String title = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_PROJECT.getGroupName(),Constant.Dict_K_V_Enum.DICT_PROJECT.getDictKey());
                if(QDStringUtil.isEmpty(title)){
                    title ="社区公告";
                }
                data.setTitle(title);
                noticeRequest.setPosition(31);
            }else{
                noticeRequest.setPosition(30);
            }
            if (QDStringUtil.isNotNull(request.getNoticeType()) && 0 == request.getNoticeType()) {
                noticeRequest.setNoticeType(null);
            }
            //3.0版本增加查询当前公告是否对用户有用
            if(QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
                noticeRequest.setMid(userToken.getMemberId());
            }

            GetNoticeListResponse noticeResponse = noticeService.getNoticeList(noticeRequest);
            checkAndContinue(noticeResponse);
            ResultPage<ResNotice> resultPage = noticeResponse.getResultPage();
            List<ResNotice> noticeList = resultPage.getItems();
            if (noticeList == null) {
                noticeList = new ArrayList<ResNotice>();
            }

            List<Notice> noticList = Lists.newArrayList();
            Map<String, String> skipConfigMap = null;
            if (QDStringUtil.isNotEmpty(version)) {
                version = version.trim();
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            }

            for (ResNotice resNotice : noticeList) {
                Notice notice = transfor(Notice.class, resNotice);
                //如果有url
                if (QDStringUtil.isNotEmpty(resNotice.getUrl())) {
                    String skipStr = skipMode.fittingSkipUrl(skipConfigMap, resNotice.getUrl(), 1, QDStringUtil.isNotNull(resNotice.getIsShare()) ? resNotice.getIsShare() : 0
                            , resNotice.getShareTitle(), resNotice.getShareContent(), resNotice.getShareImageUrl(), resNotice.getUrl(),version,String.valueOf(resNotice.getId()));
                    notice.setSkipModel(skipStr);
                } else {  //没有url，则拼接公告地址访问
                    String noticeUrl = APIPropertiesClient.getSkipUrl(Constant.SkipType.NOTICE_DETAIL.toString());
                    noticeUrl = noticeUrl + resNotice.getId();
                    String skipStr = skipMode.fittingSkipUrl(skipConfigMap, noticeUrl, 1, QDStringUtil.isNotNull(resNotice.getIsShare()) ? resNotice.getIsShare() : 0
                            , resNotice.getShareTitle(), resNotice.getShareContent(), resNotice.getShareImageUrl(), noticeUrl,version,String.valueOf(resNotice.getId()));
                    notice.setSkipModel(skipStr);
                }
                if (QDStringUtil.isNotEmpty(resNotice.getBigUrl())) {
                    String[] imgs = {resNotice.getBigUrl()};
                    notice.setImgs(imgs);
                }
                noticList.add(notice);
            }

            data.setList(noticList);
            data.setRecordCount( noticeList.size());
            data.setTotalCount( resultPage.getTotalCount());
            data.setNoticeTypeList(APIPropertiesClient.selNoticeTypeInfoBean());
            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetProjectNoticeResponseData.class, e);
        }
    }

    /**
     * 根据公告id查询公告信息
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getNotice")
    @ExplainAnnotation(explain = "根据公告id查询公告信息")
    public Response<GetNoticeByIdResponseData> getNotice(GetNoticeByIdRequest request,UserToken userToken) {

        try {
            Response<GetNoticeByIdResponseData> response = new Response<GetNoticeByIdResponseData>();

            GetNoticeInfoRequest noticeInfoRequest = transfor(GetNoticeInfoRequest.class, request);

            //3.0 实现阅读数
            if(QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
                noticeInfoRequest.setMid(userToken.getMemberId());
            }
            GetNoticeInfoResponse noticeInfoResponse = noticeService.getNoticeInfoById(noticeInfoRequest);

            checkAndContinue(noticeInfoResponse);

            GetNoticeByIdResponseData data = transfor(GetNoticeByIdResponseData.class, noticeInfoResponse);

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetNoticeByIdResponseData.class, e);
        }
    }

    /**
     * 通过指定报事ID获取报事信息
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getMyReportById", isRequireAuth = true)
    @ExplainAnnotation(explain = "通过指定报事ID获取报事信息")
    public Response<GetMyReportByIdResponseData> getMyReportById(GetMyReportByIdRequest request) {

        try {
            Response<GetMyReportByIdResponseData> response = new Response<GetMyReportByIdResponseData>();
            MatterApplyResponse matterApplyResponse = matterService.searchMatterApplyByApplyId(request.getId());
            checkAndContinue(matterApplyResponse);
            MatterApply rm = matterApplyResponse.getMatterApply();
            Report report = transfor(Report.class, rm);

            List<String> pics = new ArrayList<>();

            if (QDStringUtil.isNotBlank(rm.getPic1())) {
                pics.add(rm.getPic1());
            }
            if (QDStringUtil.isNotBlank(rm.getPic2())) {
                pics.add(rm.getPic2());
            }
            if (QDStringUtil.isNotBlank(rm.getPic3())) {
                pics.add(rm.getPic3());
            }
            report.setPics(pics.toArray(new String[]{}));

            GetMyReportByIdResponseData data = new GetMyReportByIdResponseData();
            data.setEntity(report);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());

            return response;
        } catch (ServiceException e) {
            return handleException(GetMyReportByIdResponseData.class, e);
        }
    }



    /**
     * 报事申请
     *
     * @param request
     * @return
     */
    @HTTP(alias = "applyReport", isRequireAuth = true)
    public Response<ApplyReportResponseData> applyReport(ApplyReportRequest request) {

        try {
            Response<ApplyReportResponseData> response = new Response<ApplyReportResponseData>();
            Room room = roomReadService.get(Long.parseLong(request.getRoomId()));
            if (room == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
            }
            GetAccountResponse getAccountResponse = passportService.getAccountByAccountId(transfor(GetAccountRequest.class, request));
            checkAndContinue(getAccountResponse);
            MemberInfo memberInfo = getAccountResponse.getMemberInfo();
            if (memberInfo == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请先绑定手机号");
            }

            Project project = projectService.get(room.getProjectId());
            if (QDStringUtil.isNotNull(project)) {
                if (QDStringUtil.isNotNull(project.getIsFix()) && 1 != project.getIsFix()) {
                    ApplyReportResponseData data = new ApplyReportResponseData();
                    data.setMessage("当前社区暂不支持报事报修");
                    response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                    response.setData(data);
                    return response;
                }

                GetSelfRoomResponse selfRoomResponse = memberRoomAPI.getSelfRoom(memberInfo.getId(), request.getRoomId());
                checkAndContinue(selfRoomResponse);
                MemberRoom memberRoom = selfRoomResponse.getMemberRoom();
                MatterApplyRequest applyRequest = new MatterApplyRequest();
                ResMatterApply apply = transfor(ResMatterApply.class, request);
                apply.setRoomId(request.getRoomId());
                apply.setRoomNumber(room.getCsmRoomId());
                apply.setProjectName(room.getProjectName());
                apply.setProjectNumber(project.getCsmProjectId());
                apply.setIdentity(memberRoom.getRole());
                apply.setCityId((int) project.getRegionId());
                apply.setCityName(project.getRegionName());
                if (QDVersionUtil.getVersionCode(request.getAppDevice().getQdVersion()) == QDVersionUtil.VERSION_331
                        || QDVersionUtil.getVersionCode(request.getAppDevice().getQdVersion()) == QDVersionUtil.VERSION_332) {
                    apply.setContent(apply.getContent().replace("园区报修","工程维修"));
                }
                if (QDStringUtil.isEmpty(room.getGroupName()) || (room.getGroupName().toLowerCase()).equals("null")) {
                    apply.setBuilding(String.format("%s-%s",
                            room.getBuildingName(),
                            room.getName()));
                } else {
                    apply.setBuilding(String.format("%s-%s-%s",
                            room.getGroupName(),
                            room.getBuildingName(),
                            room.getName()));
                }
                String[] pics = request.getPics();
                if (pics != null) {
                    int picsSize = pics.length;
                    if (picsSize > 0) {
                        apply.setPic1(pics[0]);
                    }
                    if (picsSize > 1) {
                        apply.setPic2(pics[1]);
                    }
                    if (picsSize > 2) {
                        apply.setPic3(pics[2]);
                    }
                }
                applyRequest.setResMatterApply(apply);
                applyRequest.setLongHu(isLongHuForFee(room.getProjectId()));
                MatterApplyResponse applyResponse = matterService.matterApply(applyRequest);
                checkAndContinue(applyResponse);
                response.setData(new ApplyReportResponseData());
                return response;
            } else {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "没有获取到社区信息");
            }
        } catch (Exception e) {

            return handleException(ApplyReportResponseData.class, e);
        }

    }

}
