package com.qding.api.call.app.qding.v3_1_0;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.EntranceCardDto;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.ProjectDto;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.RoomBindDto;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.RoomDto;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request.ActiveRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request.BindAuthRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request.ListRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request.SaveRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request.SettingRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data.ActiveResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data.BindAuthResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data.IsNewCardResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data.ListResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data.SaveResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data.SettingResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.DateUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.member.domain.EntranceCard;
import com.qding.member.domain.EntranceCardLog;
import com.qding.member.domain.EntranceCardRoom;
import com.qding.member.domain.MemberRoom;
import com.qding.member.request.entrancecard.BindCardRequest;
import com.qding.member.request.entrancecard.GetMemberCardsRequest;
import com.qding.member.request.memberroom.GetMemberRoomRequest;
import com.qding.member.response.entrancecard.BindCardResponse;
import com.qding.member.response.entrancecard.GetMemberCardsResponse;
import com.qding.member.service.IEntranceCardRpcService;
import com.qding.member.service.IMemberRoomRpcService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;

@ExplainAnnotation(explain = "门禁卡")
public class CallEntranceCard extends Callable {
    
    @Autowired
    private IEntranceCardRpcService entranceCardRpcService;
    
    @Autowired
    private IMemberRoomRpcService memberRoomRpc;
    
    @Autowired
    private IProfileService profileAPI;
    
    @Autowired
    private RoomReadRemote roomReadRemoteClient;
    
    @Autowired
    private ProjectReadRemote projectReadService;
    
    private static Logger logger = Logger.getLogger(CallEntranceCard.class);
    
    @ExplainAnnotation(explain = "门禁卡管理列表")
    @HTTP(alias = "list", isNeadToken = true)
    public Response<ListResponseData> list(ListRequest request, UserToken userToken) {
        
        Response<ListResponseData> response = new Response<ListResponseData>();
        ListResponseData data = new ListResponseData();
        
        String memberId = userToken.getMemberId();  
//      String memberId = "8aa57da65bcdba8c015bd245d7120222";
        
        GetMemberCardsResponse getMemberCardsResponse;
        try {
            GetMemberCardsRequest getMemberCardsRequest = new GetMemberCardsRequest();
            getMemberCardsRequest.setMemberId(memberId);
            getMemberCardsRequest.setPageNo(request.getPageNo());
            getMemberCardsRequest.setPageSize(request.getPageSize());
            getMemberCardsResponse = entranceCardRpcService.getMemberCards(getMemberCardsRequest);
            checkAndContinue(getMemberCardsResponse);
            
            List<EntranceCardDto> dtoList = transToDtoList(getMemberCardsResponse.getCardList());
            data.setList(dtoList);
        } catch (Exception e) {
            return handleException(ListResponseData.class, e);
        }
        
        data.setTotalCount(getMemberCardsResponse.getTotalCount());
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }
    
    @ExplainAnnotation(explain = "用户开卡鉴权")
    @HTTP(alias = "bindAuth", isNeadToken = true)
    public Response<BindAuthResponseData> bindAuth(BindAuthRequest request, UserToken userToken) {
        
        Response<BindAuthResponseData> response = new Response<BindAuthResponseData>();
        BindAuthResponseData data = new BindAuthResponseData();
        try{
            String memberId = userToken.getMemberId();  
//          String memberId = "8aa57da65bcdba8c015bd245d7120222";
            
            int roomCount = getMemberRoomCount(memberId);
            if(roomCount == 0){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "开卡功能仅限业主使用！");
            }
            int bindedCount = entranceCardRpcService.CountMemberBinded(memberId,null);
            if(Constant.BINDED_MAX <= bindedCount){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "待刷卡门禁卡已达上限");
            }
            
            data.setAuth(com.qding.member.constant.Constant.YES);
            // 社区门禁卡类型(此属性获取挪到社区基础属性接口，此处不再返回)
//            Project project = projectReadService.get(Long.parseLong(request.getProjectId()));
//            data.setIsQR(project.getIsGatewayQRCode());
            
        } catch (Exception e) {
            return handleException(BindAuthResponseData.class, e);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }
    
    @ExplainAnnotation(explain = "卡号是否新卡")
    @HTTP(alias = "isNewCard", isNeadToken = false)
    public Response<IsNewCardResponseData> isNewCard(SettingRequest request) {
        
        Response<IsNewCardResponseData> response = new Response<IsNewCardResponseData>();
        IsNewCardResponseData data = new IsNewCardResponseData();
        response.setData(data);
        try{
            if(!isQdingCard(request.getCardNo())){
                data.setIsNew(com.qding.member.constant.Constant.NO);
                data.setMessage("您扫描的卡不是千丁智能门禁卡！");
                return response;
            }
            
            EntranceCard entranceCard = entranceCardRpcService.getCard(request.getCardNo());
            if(entranceCard != null && com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_CLOSED != entranceCard.getStatus()){
                data.setIsNew(com.qding.member.constant.Constant.NO);
                data.setMessage("此门禁卡已被其他用户绑定！");
                return response;
            }
            
            data.setIsNew(com.qding.member.constant.Constant.YES);
            
        } catch (Exception e) {
            return handleException(IsNewCardResponseData.class, e);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }
    
    
    
    @ExplainAnnotation(explain = "门禁卡设置（改卡）")
    @HTTP(alias = "settingForUpdate", isNeadToken = true)
    public Response<SettingResponseData> settingForUpdate(SettingRequest request, UserToken userToken) {
        Response<SettingResponseData> response = new Response<SettingResponseData>();
        SettingResponseData data = new SettingResponseData();
        
        try {
            String memberId = userToken.getMemberId();  
//            String memberId = "8aa57da65bcdba8c015bd245d7120222";
            
            EntranceCard entranceCard = entranceCardRpcService.getCard(request.getCardNo());
            if(entranceCard == null){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "卡信息不存在");
            }
            if(!memberId.equals(entranceCard.getMemberId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "非本人卡，不可操作");
            }
            
            EntranceCardDto cardDto = transToDto(entranceCard);
            
            int roomCount = getMemberRoomCount(memberId);
            
            // 房屋列表  优先已绑的
            List<ProjectDto> projectList = getSettingRoomList(memberId,request.getProjectId(),request.getCardNo());
            
            data.setCard(cardDto);
            data.setMemberRoomNum(roomCount);
            data.setRoomListMaxNum(Constant.SHOW_ROOM_NUM);
            data.setProjectList(projectList);
            
        } catch (Exception e) {
            return handleException(SettingResponseData.class, e);
        }
        
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }
    
    @ExplainAnnotation(explain = "门禁卡设置（开卡）")
    @HTTP(alias = "settingForNew", isNeadToken = true)
    public Response<SettingResponseData> settingForNew(SettingRequest request, UserToken userToken) {
        Response<SettingResponseData> response = new Response<SettingResponseData>();
        SettingResponseData data = new SettingResponseData();
        
        try {
            String memberId = userToken.getMemberId();  
//            String memberId = "8aa57da65bcdba8c015bd245d7120222";
            
            int count = entranceCardRpcService.CountMemberBinded(memberId,null);
            if(Constant.BINDED_MAX <= count){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "待刷卡门禁卡已达上限");
            }
            
            EntranceCard entranceCard = entranceCardRpcService.getCard(request.getCardNo());
            if(entranceCard!=null && com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_CLOSED!=entranceCard.getStatus()){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "卡已被使用");
            }
            
            // 可正常绑卡
            EntranceCardDto cardDto = new EntranceCardDto();
            
            // 填充卡信息
            fillCardDto(request.getCardNo(), cardDto);
            
            int roomCount = getMemberRoomCount(memberId);
            
            // 房屋列表 优先当前社区
            List<ProjectDto> projectList = getSettingRoomList(memberId,request.getProjectId(),null);
            
            data.setCard(cardDto);
            data.setMemberRoomNum(roomCount);
            data.setRoomListMaxNum(Constant.SHOW_ROOM_NUM);
            data.setProjectList(projectList);
            
        } catch (Exception e) {
            return handleException(SettingResponseData.class, e);
        }
        
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }
    
    @ExplainAnnotation(explain = "开卡提交")
    @HTTP(alias = "newSave", isNeadToken = true)
    public Response<SaveResponseData> newSave(SaveRequest request, UserToken userToken) {
        
        Response<SaveResponseData> response = new Response<SaveResponseData>();
        SaveResponseData data = new SaveResponseData();
        
        try {
            
            if(!isQdingCard(request.getCardNo())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "您扫描的卡不是千丁智能门禁卡！");
            }
            
            String memberId = userToken.getMemberId();  
//            String memberId = "8aa57da65bcdba8c015bd245d7120222";
            
            int count = entranceCardRpcService.CountMemberBinded(memberId,null);
            if(Constant.BINDED_MAX <= count){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "待刷卡门禁卡已达上限");
            }
            
            BindCardRequest bindCardRequest = transRpcRequest(request,"add",memberId);
            bindCardRequest.setMemberId(memberId);
            BindCardResponse bindCardResponse = entranceCardRpcService.bindCard(bindCardRequest);
            checkAndContinue(bindCardResponse);
            
            EntranceCardDto dto = transToDto(bindCardResponse.getCard());
            data.setCard(dto);
            
        } catch (Exception e) {
            return handleException(SaveResponseData.class, e);
        }
        
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }
    
    
    @ExplainAnnotation(explain = "改卡提交")
    @HTTP(alias = "updateSave", isNeadToken = true)
    public Response<SaveResponseData> updateSave(SaveRequest request, UserToken userToken) {
        Response<SaveResponseData> response = new Response<SaveResponseData>();
        SaveResponseData data = new SaveResponseData();
        
        try {
            String memberId = userToken.getMemberId(); 
//            String memberId = "8aa57da65bcdba8c015bd245d7120222";
            
            int count = entranceCardRpcService.CountMemberBinded(memberId,request.getCardNo());
            if(Constant.BINDED_MAX <= count){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "待刷卡门禁卡已达上限");
            }
            
            BindCardRequest bindCardRequest = transRpcRequest(request,"modify",memberId);
            bindCardRequest.setMemberId(memberId);
            BindCardResponse bindCardResponse = entranceCardRpcService.reBindCard(bindCardRequest);
            checkAndContinue(bindCardResponse);
            
            EntranceCardDto dto = transToDto(bindCardResponse.getCard());
            data.setCard(dto);
            
        } catch (Exception e) {
            return handleException(SaveResponseData.class, e);
        }
        
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }
    
    
    @ExplainAnnotation(explain = "续期/激活")
    @HTTP(alias = "renewal", isNeadToken = true)
    public Response<SaveResponseData> renewal(SettingRequest request, UserToken userToken) {
        
        Response<SaveResponseData> response = new Response<SaveResponseData>();
        SaveResponseData data = new SaveResponseData();
        response.setData(data);
        
        try {
            
            Date now = new Date();
            String memberId = userToken.getMemberId(); 
//            String memberId = "8aa57da65bcdba8c015bd245d7120222";
            
            int count = entranceCardRpcService.CountMemberBinded(memberId,request.getCardNo());
            if(Constant.BINDED_MAX <= count){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "待刷卡门禁卡已达上限");
            }
            
            EntranceCard entranceCard = entranceCardRpcService.getCard(request.getCardNo());
            if(entranceCard == null){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "卡信息不存在");
            }
            if(!memberId.equals(entranceCard.getMemberId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "操作人与卡信息不匹配，不允许操作");
            }
            
            String oldStr = DateUtil.Date2Str(entranceCard.getEffectiveBeginDate())+" - "+DateUtil.Date2Str(entranceCard.getEffectiveEndDate());
            
            if(com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_EXPIRE == entranceCard.getStatus()){
                // 已过期， 从now重新计算有效期
                entranceCard.setEffectiveBeginDate(now);
                entranceCard.setEffectiveEndDate(DateUtil.addDay(now, Constant.EFFECTIVE_DAYS));
            }else{
                // 未过期，延长有效期
                entranceCard.setEffectiveEndDate(DateUtil.addDay(entranceCard.getEffectiveEndDate(), Constant.EFFECTIVE_DAYS));
            }
            entranceCard.setStatus(com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_BINDED);
            entranceCard.setBindTime(now.getTime());
            entranceCard.setActivateTime(now.getTime());
            entranceCardRpcService.updateCard(entranceCard);
            
            String newStr = DateUtil.Date2Str(entranceCard.getEffectiveBeginDate())+" - "+DateUtil.Date2Str(entranceCard.getEffectiveEndDate());
            
            EntranceCardLog log = new EntranceCardLog();
            BeanUtils.copyProperties(log, entranceCard);
            log.setId(com.qding.member.constant.Constant.ENTRANCECARD_LOG_UUID.generate());
            log.setMemberName(entranceCard.getMemberNickname());
            log.setOptType("续期/激活");
            log.setOptTime(now.getTime());
            log.setMemo("操作前："+oldStr+"\n操作后："+newStr);
            entranceCardRpcService.saveCardLog(log);
            
            EntranceCardDto cardDto = transToDto(entranceCard);
            data.setCard(cardDto);
        } catch (Exception e) {
            return handleException(SaveResponseData.class, e);
        }
        
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }
    
    @ExplainAnnotation(explain = "刷卡")
    @HTTP(alias = "active", isNeadToken = true)
    public Response<ActiveResponseData> active(ActiveRequest request, UserToken userToken) {

        Response<ActiveResponseData> response = new Response<ActiveResponseData>();
        ActiveResponseData data = new ActiveResponseData();
        List<String> activedList = new ArrayList<String>();
        response.setData(data);
        data.setActivedList(activedList);
        try {

            Date now = new Date();
            String memberId = userToken.getMemberId();
            // String memberId = "8aa57da65bcdba8c015bd245d7120222";

            String cardNOs = request.getCardNOs();
            String[] cardNoArr = cardNOs.split(",");
            for (String cardNo : cardNoArr) {
                
                EntranceCard entranceCard = entranceCardRpcService.getCard(cardNo);
                
                if (entranceCard != null && memberId.equals(entranceCard.getMemberId())
                        && com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_BINDED == entranceCard.getStatus()) {
                    
                    entranceCard.setStatus(com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_NORMAL);
                    entranceCard.setActivateTime(now.getTime());
                    entranceCardRpcService.updateCard(entranceCard, request.getProjectId());

                    EntranceCardLog log = new EntranceCardLog();
                    BeanUtils.copyProperties(log, entranceCard);
                    log.setId(com.qding.member.constant.Constant.ENTRANCECARD_LOG_UUID.generate());
                    log.setMemberName(entranceCard.getMemberNickname());
                    log.setOptType("刷卡");
                    log.setOptTime(now.getTime());
                    entranceCardRpcService.saveCardLog(log);

                    activedList.add(cardNo);
                }
            }
        } catch (Exception e) {
            return handleException(ActiveResponseData.class, e);
        }

        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }
    
    
    @ExplainAnnotation(explain = "销卡")
    @HTTP(alias = "close", isNeadToken = true)
    public Response<ResponseData> close(SettingRequest request, UserToken userToken) {
        
        Response<ResponseData> response = new Response<ResponseData>();
        
        try {
            String memberId = userToken.getMemberId(); 
//            String memberId = "8aa57da65bcdba8c015bd245d7120222";
            EntranceCard entranceCard = entranceCardRpcService.getCard(request.getCardNo());
            if(entranceCard == null){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "卡信息不存在");
            }
            if(!memberId.equals(entranceCard.getMemberId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "操作人与卡信息不匹配，不允许操作");
            }
//            if(com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_BINDED != entranceCard.getStatus()
//                    && com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_NORMAL != entranceCard.getStatus()){
//                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "只有绑定状态的卡片才能销卡");
//            }
            
            boolean result = entranceCardRpcService.unBindCard(request.getCardNo(),null);
            if(!result){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "门禁销卡失败");
            }
            
        } catch (Exception e) {
            return handleException(ResponseData.class, e);
        }
        
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }
    
    
    /****************** 以下为非接口方法 ***********************/
    
    protected boolean isQdingCard(String cardNo){
        Matcher matcher = Pattern.compile(Constant.CARD_NO_REGEX).matcher(cardNo);
        return matcher.matches();
    }
    
    /**
     * @param String action add:开卡，modify:改卡
     */
    protected BindCardRequest transRpcRequest(SaveRequest request,String action,String memberId)  throws Exception{ 
        BindCardRequest rpcSaveRequest = new BindCardRequest();
        BeanUtils.copyProperties(rpcSaveRequest, request);
        List<com.qding.member.vo.RoomDto> rpcRoomList = new ArrayList<com.qding.member.vo.RoomDto>();
        for(RoomDto roomDto : request.getRoomList()){
            com.qding.member.vo.RoomDto rpcRoom = new com.qding.member.vo.RoomDto();
            BeanUtils.copyProperties(rpcRoom, roomDto);
            rpcRoomList.add(rpcRoom);
        }
        rpcSaveRequest.setRoomList(rpcRoomList);
        
        // 填充member信息
        GetMemberRequest getMemberRequest = new GetMemberRequest();
        getMemberRequest.setMemberId(memberId);
        GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);
        checkAndContinue(getMemberResponse);
        rpcSaveRequest.setMemberMobile(getMemberResponse.getMemberInfo().getMobile());
        rpcSaveRequest.setMemberNickname(getMemberResponse.getMemberInfo().getNickName());
        
        if("add".equals(action)){
            // 开卡操作，计算卡有效期
            Date now = new Date();
            Date effectiveEnd = DateUtil.addDay(now, Constant.EFFECTIVE_DAYS);
            rpcSaveRequest.setEffectiveBeginDate(now);
            rpcSaveRequest.setEffectiveEndDate(effectiveEnd);
        }
        
        return rpcSaveRequest;
    }
    
    protected  List<EntranceCardDto> transToDtoList(List<EntranceCard> list) throws Exception{
        if(list == null){
            return null;
        }
        
        List<EntranceCardDto> dtoList = new ArrayList<EntranceCardDto>();
        
        EntranceCardDto dto = null;
        for(EntranceCard domain : list){
            
            dto = transToDto(domain);
            dtoList.add(dto);
        }
        
        return dtoList;
    }
    
    protected  EntranceCardDto transToDto(EntranceCard domain) throws Exception{
        if(domain == null){
            return null;
        }
        
        EntranceCardDto dto = new EntranceCardDto();
        BeanUtils.copyProperties(dto, domain);
        dto.setEffectiveBegin(DateUtil.Date2Str(domain.getEffectiveBeginDate()));
        dto.setEffectiveEnd(DateUtil.Date2Str(domain.getEffectiveEndDate()));
        
        // 卡即将到期提醒
        if(domain.getEffectiveEndDate()!=null && DateUtil.addDay(new Date(), Constant.EXPIRE_DAYS).after(domain.getEffectiveEndDate())){
            dto.setIsSoonExpire(com.qding.member.constant.Constant.YES);
            
            if(com.qding.member.constant.Constant.ENTRANCE_CARD_STATUS_EXPIRE == domain.getStatus()){
                // 已过期， 从now重新计算有效期
                Date now = new Date();
                dto.setRenewalBegin(DateUtil.Date2Str(now));
                dto.setRenewalEnd(DateUtil.Date2Str(DateUtil.addDay(now, Constant.EFFECTIVE_DAYS)));
            }else{
                // 未过期，延长有效期
                dto.setRenewalBegin(dto.getEffectiveBegin());
                dto.setRenewalEnd(DateUtil.Date2Str(DateUtil.addDay(domain.getEffectiveEndDate(), Constant.EFFECTIVE_DAYS)));
            }
        }
        
        return dto;
    }
    
    protected void fillCardDto(String cardNo,EntranceCardDto cardDto){
        Date now = new Date();
        Date effectiveEnd = DateUtil.addDay(now, Constant.EFFECTIVE_DAYS);
        cardDto.setCardNo(cardNo);
        cardDto.setStatus(5);
        cardDto.setEffectiveBegin(DateUtil.Date2Str(now));
        cardDto.setEffectiveEnd(DateUtil.Date2Str(effectiveEnd));
    }
    
    /**
     * 门禁卡设置页面--用户房屋列表
     * 
     */
    protected List<ProjectDto> getSettingRoomList(String memberId,String projectId,String cardNo){
        
        List<ProjectDto> ret = null;
        
        // 已绑房屋ID汇总
        List<Long> bindRoomIdList = null;
        if(StringUtils.isNoneEmpty(cardNo)){
            List<EntranceCardRoom> bindRoomList = entranceCardRpcService.getBindRooms(cardNo);
            if(CollectionUtils.isNotEmpty(bindRoomList)){
                bindRoomIdList = new ArrayList<Long>();
                for(EntranceCardRoom entranceCardRoom : bindRoomList){
                    bindRoomIdList.add(entranceCardRoom.getRoomId());
                }
            }
        }
        
        // 获取用户绑定房屋列表
        List<MemberRoom> memberRoomList = getMemberBindRooms(memberId,projectId,bindRoomIdList);
        
        // 对房屋按社区分组
        if(CollectionUtils.isNotEmpty(memberRoomList)){
            ret = memberRoomGroup(memberRoomList,projectId,bindRoomIdList);
        }
        
        return ret;
    }
    
    /**
     * 按社区分组，当前社区在最前
     */
    protected List<ProjectDto> memberRoomGroup(List<MemberRoom> memberRoomList,String currentProjectId,List<Long> bindRoomIdList){
        List<ProjectDto> ret = new ArrayList<ProjectDto>();
        
        // 社区分组
        Set<String> projectIdSet = new LinkedHashSet<String>();
        // 当前社区在最前
        for(MemberRoom memberRoom : memberRoomList){
            if(currentProjectId.equals(memberRoom.getProjectId())){
                projectIdSet.add(memberRoom.getProjectId());
                break;
            }
        }
        // 其他社区任意
        for(MemberRoom memberRoom : memberRoomList){
            projectIdSet.add(memberRoom.getProjectId());
        }
        // 按社区组装Dto
        ProjectDto projectDto = null;
        List<RoomBindDto> roomBindList = null;
        RoomBindDto roomBindDto = null;
        for(String projectId : projectIdSet){
            projectDto = new ProjectDto();
            projectDto.setProjectId(projectId);
            fillProjectDesc(projectDto);//填充社区信息
            // 该社区的房屋列表
            roomBindList = new ArrayList<RoomBindDto>();
            for(MemberRoom memberRoom : memberRoomList){
                if(memberRoom.getProjectId().equals(projectId)){
                    roomBindDto = new RoomBindDto();
                    roomBindDto.setRoomId(memberRoom.getRoomId()+"");
                    fillRoomDesc(roomBindDto);//填充房屋信息
                    // 该房屋是否已绑定
                    if(bindRoomIdList!=null && bindRoomIdList.contains(memberRoom.getRoomId())){
                        roomBindDto.setIsBind(com.qding.member.constant.Constant.YES);
                    }
                    roomBindList.add(roomBindDto);
                }
            }
            
            // 基础数据不返回已停用房屋信息，这里临时过滤掉没有房屋信息的房屋
            // 正式发布前，基础数据会清洗，把已停用房屋的关系解绑
            removeDisableRoom(roomBindList);
            
            projectDto.setRoomBindList(roomBindList);
            ret.add(projectDto);
        }
        
        return ret;
    }
    
    private void removeDisableRoom(List<RoomBindDto> roomBindList){
        for(int i=roomBindList.size()-1; i>=0; i--){
            if(StringUtils.isBlank(roomBindList.get(i).getRoomName())){
                roomBindList.remove(i);
            }
        }
    }
    
    protected void fillRoomDesc(RoomBindDto roomBindDto){
        Room room = roomReadRemoteClient.get(Long.parseLong(roomBindDto.getRoomId()));
        if(room != null){
            roomBindDto.setRoomName((StringUtils.isNotBlank(room.getGroupName())? room.getGroupName()+"-" : "")
                                    + room.getBuildingName()+"-"+room.getName());
        }
    }
    protected void fillProjectDesc(ProjectDto projectDto){
        Project project = projectReadService.get(Long.parseLong(projectDto.getProjectId()));
        if(project != null){
            projectDto.setCityId(project.getRegionId()+"");
            projectDto.setCityName(project.getRegionName());
            projectDto.setProjectName(project.getName());
        }
    }
    
    protected int getMemberRoomCount(String memberId){
        GetMemberRoomRequest getMemberRoomRequest = new GetMemberRoomRequest();
        getMemberRoomRequest.setMemberId(memberId);
        int count = memberRoomRpc.CountMemberRoom(getMemberRoomRequest);
        return count;
    }
    
    /**
     * 首先，已绑房屋<cardNo不为空>
     * 其次，用户当前社区房屋
     * 最后，其他社区房屋
     */
    protected List<MemberRoom> getMemberBindRooms(String memberId,String projectId,List<Long> bindRoomIdList){
        // 多次查询到的用户房屋信息在这里汇总
        List<MemberRoom> memberRoomList = new ArrayList<MemberRoom>();
        
        // 每次查询的结果集
        List<MemberRoom> queryList = null;
        
        GetMemberRoomRequest getMemberRoomRequest = new GetMemberRoomRequest();
        getMemberRoomRequest.setMemberId(memberId);
        getMemberRoomRequest.setLimitNum(Constant.SHOW_ROOM_NUM);
        
        
        // 存在已绑房屋，优先查
        if(bindRoomIdList != null){
            getMemberRoomRequest.setRoomIdList(bindRoomIdList);
            queryList = memberRoomRpc.getMemberRoom(getMemberRoomRequest);
            if(CollectionUtils.isNotEmpty(queryList)){
                memberRoomList.addAll(queryList);
            }
        }
        
        // 房屋数量不满足显示数量要求
        if(memberRoomList.size() < Constant.SHOW_ROOM_NUM){
            // 查询当前社区房屋（过滤掉已绑房屋）
            getMemberRoomRequest.clearParam();
            getMemberRoomRequest.setExcludeRoomIdList(bindRoomIdList);
            getMemberRoomRequest.setProjectId(projectId);
            queryList = memberRoomRpc.getMemberRoom(getMemberRoomRequest);
            if(CollectionUtils.isNotEmpty(queryList)){
                memberRoomList.addAll(queryList);
            } 
        }
        
        // 房屋数量仍然不满足显示数量要求
        if(memberRoomList.size() < Constant.SHOW_ROOM_NUM){
            // 查询其他房屋（过滤掉当前社区、已绑房屋）
            getMemberRoomRequest.clearParam();
            getMemberRoomRequest.setExcludeRoomIdList(bindRoomIdList);
            getMemberRoomRequest.setExcludeProjectId(projectId);
            queryList = memberRoomRpc.getMemberRoom(getMemberRoomRequest);
            if(CollectionUtils.isNotEmpty(queryList)){
                memberRoomList.addAll(queryList);
            } 
        }
        
        if(memberRoomList.size() > Constant.SHOW_ROOM_NUM){
            memberRoomList = memberRoomList.subList(0, Constant.SHOW_ROOM_NUM);
        }
        
        return memberRoomList;
    }
    
}
  
