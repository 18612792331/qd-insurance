package com.qding.api.call.app.qding.v1_3_1;

import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.request.GetRoomByBuildingIdRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data.GetRoomByBuildingIdResponseData;
import com.qding.api.call.app.qding.v1_3_1.struct.brick.ClearAppData;
import com.qding.api.call.app.qding.v1_3_1.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_3_1.struct.brick.request.*;
import com.qding.api.call.app.qding.v1_3_1.struct.brick.response.data.*;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.*;
import com.qding.api.verifycode.ImgCodeConfig;
import com.qding.api.verifycode.VerifyCode;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.fuwuchuang.model.UnionLoginInfo;
import com.qding.fuwuchuang.service.IFuwuchuangService;
import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;
import com.qding.profee.rpc.response.fee.WithholdProjectResponse;
import com.qding.profee.rpc.service.IFeeRpcService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

//TODO FIX BUG 下一版本强制升级时 删除
public class CallBrick extends com.qding.api.call.app.qding.v1_3_0.CallBrick {


    @Autowired
    private RoomReadRemote roomReadRemoteService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private ProjectReadRemote projectService;

    @Autowired
    private AppConfigRemote appConfigRemote;

	@Autowired
	private SkipModeFitting skipMode;
    
    @Autowired
    private IFeeRpcService feeService;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;


    @Autowired
    private IFuwuchuangService alipayRpcService;

    /**
     * 根据楼栋id获取房间列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getRooms")
    @Deprecated
    public Response<GetRoomByBuildingIdResponseData> getRooms(GetRoomByBuildingIdRequest request) {

        Response<GetRoomByBuildingIdResponseData> response = new Response<GetRoomByBuildingIdResponseData>();

        List<Room> rooms = roomReadRemoteService.getRoomsMobileByBuildingId(Long.parseLong(request.getBuildingId()));

        List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Room> rs =
                transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, rooms);

        GetRoomByBuildingIdResponseData data = new GetRoomByBuildingIdResponseData();

        data.setList(rs);

        response.setData(data);

        return response;
    }


    /**
     * 获取指定社区可用的服务
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getProjectPropertyServiceByProjectId")
    public Response<GetProjectPropertyServiceByProjectIdResponseData> getProjectPropertyServiceByProjectId(GetProjectPropertyServiceByProjectIdRequest request) {

        Response<GetProjectPropertyServiceByProjectIdResponseData> response = new Response<GetProjectPropertyServiceByProjectIdResponseData>();

        GetProjectPropertyServiceByProjectIdResponseData data = new GetProjectPropertyServiceByProjectIdResponseData();

        response.setCode(HttpStatus.OK.getStatusCode());

        List<ProjectService> services = Lists.newArrayList();

        Project project = projectReadService.get(Long.parseLong(request.getProjectId()));

        // 门禁卡类型，是否二维码
        if (1 == project.getIsGatewayQRCode()) {
            ProjectService isQRService = new ProjectService();
            isQRService.setContent("PROJECT_ISQR");
            isQRService.setName("门禁卡是否有二维码");
            isQRService.setSubType("");
            services.add(isQRService);
        }

        boolean isLongHu = isLongHuForFee(Long.parseLong(request.getProjectId()));

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsOpenFee()) {
            //// TODO: 2016/2/29 这里不限制物业缴费服务判断，在物业账单首页进行判断是否能缴纳物业费
            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_WYJF");

            projectService.setName("物业缴费");

            projectService.setSubType("");

            services.add(projectService);
        }


        if (isLongHu) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_LH");

            projectService.setName("是否是龙湖");

            projectService.setSubType("1");//龙湖物业

            services.add(projectService);

        } else {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_LH");

            projectService.setName("是否是龙湖");

            projectService.setSubType("2");//非龙湖物业

            services.add(projectService);

        }


        if (QDStringUtil.isNotNull(project) && 1 == project.getIsFix()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_BSBX");

            projectService.setName("报事报修");

            //呼叫中心模式
            projectService.setSubType("1");

            if (2 == project.getFixDistributionType().intValue()) {
                projectService.setSubType("2");        //直连模式
            }

            services.add(projectService);
        }

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsNeighbour()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_LJ");

            projectService.setName("邻聚");

            projectService.setSubType("");

            services.add(projectService);
        }

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsUpload()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_SCZJ");

            projectService.setName("上传证件");

            projectService.setSubType("");

            services.add(projectService);
        }

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsSpread()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_TGY");

            projectService.setName("推广员");

            projectService.setSubType("");

            services.add(projectService);
        }

        if (QDStringUtil.isNotNull(project) && 1 <= project.getGateType()) {

            ProjectService projectService = new ProjectService();
            projectService.setContent("PROJECT_TXZ");
            projectService.setName("门岗机通行类型");
            projectService.setSubType(String.valueOf(project.getGateType()));
            //3.4增加判断门禁通行及跳转（跳转采用身份验证）
            projectService.setServiceDesc(String.valueOf(project.getGatewayAccessRole()));
            services.add(projectService);
        }

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsShowBtLog()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_TXJL");

            projectService.setName("蓝牙通行证记录");

            projectService.setSubType("");

            services.add(projectService);

        }

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsOpenFeeForALIPAY()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_ALZF");

            projectService.setName("阿里支付方式");

            projectService.setSubType("");

            services.add(projectService);

        }

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsOpenFeeForPAD()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_PAD");

            projectService.setName("PAD支付");

            projectService.setSubType("");

            services.add(projectService);

        }

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsOpenFeeForWX()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_WX");

            projectService.setName("微信支付");

            projectService.setSubType("");

            services.add(projectService);

        }

        if (QDStringUtil.isNotNull(project) && 1 == project.getIsFixHk()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("CONTACT_HK");

            projectService.setName("联系管家");

            projectService.setSubType("");

            services.add(projectService);
        }

        if (QDStringUtil.isNotNull(project) && QDStringUtil.isNotNull(project.getBindType())) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("BIND_ROOM");

            projectService.setName("绑定房屋");

            String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
            if (Integer.parseInt(initVersion) >=320000){
            	projectService.setSubType(String.valueOf(project.getBindType()));
            } else {
            	int d=project.getBindType()&7;
            	projectService.setSubType(String.valueOf(d));
            }
            services.add(projectService);
        }
        if (QDStringUtil.isNotNull(project) && 1 == project.getIsCloudInterphone()) {

            ProjectService projectService = new ProjectService();
            projectService.setContent("CLOUD_INTERPHONE");
            projectService.setName("云对讲");
            projectService.setSubType("");
            services.add(projectService);
        } else {  // TODO: 2017/6/16 临时解决3.1版本ios APP异常兼容
            if (request.getAppDevice().getQdPlatform().toLowerCase().equals(Constant.QD_PLATFORM_IOS) &&
                    QDStringUtil.isNotEmpty(request.getAppUser().getCurMemberId())) {
                Project cloudIntercomProject = checkCloudIntercom(request.getAppUser().getCurMemberId());
                if (QDStringUtil.isNotNull(cloudIntercomProject)) {
                    ProjectService projectService = new ProjectService();
                    projectService.setContent("CLOUD_INTERPHONE");
                    projectService.setName("云对讲");
                    projectService.setSubType("");
                    services.add(projectService);
                }
            }
        }


        if (QDStringUtil.isNotNull(project) && 1 == project.getIsSupportAssistant()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("SUPPORT_ASSISTANT");

            projectService.setName("语音助手");

            projectService.setSubType("");

            services.add(projectService);
        }
        if (QDStringUtil.isNotNull(project) && 1 == project.getIsSupportChat()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("MEIQIA_HOUSEKEEPER");

            projectService.setName("美洽社区管家");

            projectService.setSubType("");

            services.add(projectService);
        }
        //1----------------
        if (QDStringUtil.isNotNull(project) && QDStringUtil.isNotNull(project.getNgGoodsDisplayStyle())) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("GOODS_LIST_STYLE");

            projectService.setName("商品列表");

            projectService.setSubType(String.valueOf(project.getNgGoodsDisplayStyle()));

            services.add(projectService);
        }
        
        if (QDStringUtil.isNotNull(project) && 1 == project.getAiRobot()) {

            ProjectService projectService = new ProjectService();

            projectService.setContent("PROJECT_ROBOT_AI");

            projectService.setName("AI机器人 ");

            projectService.setSubType("");

            services.add(projectService);
        }
        
        try {
            String grayReleasedPids = APIPropertiesClient.getValue("gray_released_projectIds");
            if (QDStringUtil.isNotEmpty(grayReleasedPids)) {
                String[] projectIdArray = grayReleasedPids.split(",");
                List<String> projectIdList = Arrays.asList(projectIdArray);
                boolean isGrayReleased = projectIdList.contains(request.getAppUser().getProjectId());
                if (isGrayReleased) {
                    ProjectService projectService = new ProjectService();
                    projectService.setContent("GRAY_RELEASED");
                    projectService.setName("灰度社区");
                    projectService.setSubType(String.valueOf(project.getBindType()));
                    services.add(projectService);
                }
            }
        } catch (Exception ex) {

        }

        try {
            String switchStatus = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_SWITCH_1.getGroupName(),Constant.Dict_K_V_Enum.DICT_SWITCH_1.getDictKey());
            if (QDStringUtil.isNotEmpty(switchStatus) && "open".equals(switchStatus)) {
                ProjectService projectService = new ProjectService();
                projectService.setContent("NETTY_SWITCH");
                projectService.setName("NETTY开关");
                projectService.setSubType("");
                services.add(projectService);
            }
        } catch (Exception ex) {

        }

        try {
            String switchStatus = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_SWITCH_2.getGroupName(),Constant.Dict_K_V_Enum.DICT_SWITCH_2.getDictKey());
            if (QDStringUtil.isNotEmpty(switchStatus) && "open".equals(switchStatus)) {
                ProjectService projectService = new ProjectService();
                projectService.setContent("NETTY_SSO_SWITCH");
                projectService.setName("NETTY单点登陆开关");
                projectService.setSubType("");
                services.add(projectService);
            }
        } catch (Exception ex) {
        }
        try {
            //3.3需求已砍，临时屏蔽
//            String switchStatus = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_SWITCH_7.getGroupName(),Constant.Dict_K_V_Enum.DICT_SWITCH_7.getDictKey());
//            if ("open".equals(switchStatus)) {
                WithholdProjectResponse  withholdProjectResponse = feeService.getProjectWithholdStatus(Long.parseLong(request.getProjectId()));
                if (  withholdProjectResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode() && 1 == withholdProjectResponse.getStatus().intValue()) {
                    ProjectService projectService = new ProjectService();
                    projectService.setContent("DK_WYF");
                    projectService.setName("物业费代扣");
                    projectService.setSubType("");
                    services.add(projectService);
                }
//            }
        } catch (Exception ex) {

        }

        data.setList(services);
        response.setData(data);

        return response;

    }


    public Project checkCloudIntercom(String memberId) {

        Project cloudIntercomProject = null;
        GetMemberRoomResponse memberRoomResponse = memberRoomAPI.listValidByMember(memberId);
        Set<Long> bindRoomPIdSet = new HashSet<>();
        if (HttpStatus.OK.getStatusCode() == memberRoomResponse.getReturnInfo().getCode()) {
            List<com.qding.member.model.MemberRoom> lsMemberRoom = memberRoomResponse.getList();
            for (MemberRoom memberRoom : lsMemberRoom) {
                bindRoomPIdSet.add(Long.parseLong(memberRoom.getProjectId()));
            }
        }

        if (CollectionUtils.isNotEmpty(bindRoomPIdSet)) {
            List<Project> projectList = projectReadService.getAllProjectInfoByIdSet(bindRoomPIdSet);
            if (CollectionUtils.isNotEmpty(projectList)) {
                for (Project project : projectList) {
                    if (QDStringUtil.isNotNull(project) && project.getIsCloudInterphone()!=0) {
                        cloudIntercomProject = project;
                        break;
                    }
                }
            }
        }

        return cloudIntercomProject;
    }

    /**
     * 临时添加判断是否是龙湖的接口
     *
     * @param projectId
     * @return
     */
    public boolean isLongHuForFee(Long projectId) {

        try {
            boolean isLongHu = projectService.isLongForProject(projectId);
            return isLongHu;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    /**
     * 创建唯一key用于标记系统验证码的key
     *
     * @param request
     * @return
     */
    @HTTP(alias = "createVerifyKey")
    public Response<CreateVerifyKeyResponseData> createVerifyKey(CreateVerifyKeyRequest request) {

        Response<CreateVerifyKeyResponseData> response = new Response<CreateVerifyKeyResponseData>();

        response.setData(new CreateVerifyKeyResponseData());

        response.setCode(HttpStatus.OK.getStatusCode());

        return response;

    }

    /**
     * 系统验证码生成器
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @HTTP(alias = "imgVerifyCode",checkVersion = false)
    public void imgVerifyCode(ImgVerifyCodeRequest request, HttpServletResponse response) throws IOException {

        String identity = request.getVerifyKey();
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");

        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        CreateImageCode vCode = new CreateImageCode(100, 30, 4, 10);
        try {
            VerifyCode.setImgCode(
                    new ImgCodeConfig(
                            vCode.getCode().toLowerCase(),
                            identity,
                            new RedisStoreDevice(),
                            true));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        vCode.write(response.getOutputStream());

    }


    /**
     * 获取区域字典信息
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getAreaDic")
    @Deprecated
    public Response<AreaDicResponseData> getAreaDic(AreaDicRequest request) {

        Response<AreaDicResponseData> response = new Response<AreaDicResponseData>();

        try {
            AreaDicResponseData data = new AreaDicResponseData();

            data.setAreaJsonStr(appConfigRemote.getAreaInfo());

            response.setData(data);

            return response;

        } catch (Exception e) {
            return handleException(AreaDicResponseData.class, e);
        }

    }


    /**
     * 通过支付宝服务窗用户口令获取支付宝用户
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getAliServiceUser")
    public Response<AliServiceLoginResponseData> getAliServiceUser(AliServiceLoginRequest request) {

        Response<AliServiceLoginResponseData> response = new Response<AliServiceLoginResponseData>();
        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
        UnionLoginInfo unionLoginInfo = alipayRpcService.getUnionLoginInfo(request.getAuthcode());
        if (QDStringUtil.isNotNull(unionLoginInfo)) {
            AliServiceLoginResponseData data = new AliServiceLoginResponseData();
            data.setAccountId(unionLoginInfo.getAccountId());
            data.setMemberId(unionLoginInfo.getMemberId());
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
        }
        return response;
    }

    @HTTP(alias = "clearAppData")
    public Response<ClearAppDataResponseData> clearAppData(ClearAppDataResponseRequest request) {

        Response<ClearAppDataResponseData> response = new Response<ClearAppDataResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        ClearAppDataResponseData data = new ClearAppDataResponseData();
        ClearAppData entity = new ClearAppData();
        entity.setCacheTimeStamp(0L);
        data.setEntity(entity);
        response.setData(data);

        return response;
    }

}
