package com.qding.api.imessage;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.api.constant.Constant;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.JsonUtil;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.cache.FeedBean;
import com.qding.imessage.common.sender.IRemoteJobService;
import com.qding.imessage.common.struct.MsginfoRequest;
import com.qding.imessage.common.struct.MsginfoResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberByAccountIdRequest;
import com.qding.passport.struct.response.GetMemberResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;


/**
 * Created by Administrator on 2015/8/14.
 */
public class IntegralMessage {

    private  String imessageUrl = "";

    private  String jobUrl ="";

    private  Integer type ;

    private   String toClass ="";

    private  int maxRetrycount;

    private  Long retryInterval;

    private  static  IRemoteJobService remoteJobService;

    @Autowired
    private  IHotCycleRemoteService hotCycleRemoteService;

    @Autowired
    private  IProfileService profileAPI;

    private Logger logger = Logger.getLogger(IntegralMessage.class);

    public void init() {

        HessianProxyFactory factory = new HessianProxyFactory();
        try {

            factory.setChunkedPost(false);
            remoteJobService = (IRemoteJobService) factory.create(IRemoteJobService.class, imessageUrl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public   void assembleIntegralMessage(IntegralMessageBeanT integralMessageBeanT){
      try {

          String memberId = integralMessageBeanT.getAbstractId();
          String businessId = integralMessageBeanT.getTbusinessId();
          Long projectId = integralMessageBeanT.getPorjectId();

          switch (integralMessageBeanT.getBusinessType()) {

              //通行证
              case Constant.INTEGRAL_PASS_PORT:

                  memberId = getMemberIdByAccountId(integralMessageBeanT.getAbstractId());
                  businessId = memberId;
                  break;

              //访客通行积分规则
              case Constant.INTEGRAL_VISITOR_ACCESS:

                  memberId = getMemberIdByAccountId(integralMessageBeanT.getAbstractId());
                  businessId = memberId;
                  break;

              //访客通行积分规则
              case Constant.INTEGRAL_VISITOR_ACCESS_M:

                  memberId = getMemberIdByAccountId(integralMessageBeanT.getAbstractId());
                  businessId = memberId;
                  break;

              //活动发帖
              case Constant.INTEGRAL_PUBLISH_FEED:

                  memberId = getMemberIdByAccountId(integralMessageBeanT.getAbstractId());
                  break;

              //累积赞数 (feedId获取memberId)
              case Constant.INTEGRAL_PRAISE:

                  FeedBean  praiseFeed = getFeedBeanByFeedId (integralMessageBeanT.getAbstractId());
                  memberId = getMemberByFeedBean(praiseFeed);
                  projectId = QDStringUtil.isNotNull(praiseFeed)?Long.parseLong(praiseFeed.getCommunityId()):null;
                  break;

              //累积评论数 (feedId获取memberId)
              case Constant.INTEGRAL_COMMENT:

                  FeedBean  commentFeed = getFeedBeanByFeedId (integralMessageBeanT.getAbstractId());
                  memberId = getMemberByFeedBean(commentFeed);
                  projectId = QDStringUtil.isNotNull(commentFeed)?Long.parseLong(commentFeed.getCommunityId()):null;
                  break;

              //保修评价(过accountId单获取保修会员ID ，注意memberId和businessId为同值都需修改)
              case Constant.INTEGRAL_REPAIR_EVALUATE:

                  memberId = getMemberIdByAccountId(integralMessageBeanT.getAbstractId());
                  businessId = memberId;
                  break;

              //签到
              case Constant.INTEGRAL_SIGN_IN:

                  businessId = memberId;
                  break;

              //物业费
              case Constant.INTEGRAL_PROPERTY_MANAGE_FEE:

                  break;

              //停车费
              case Constant.INTEGRAL_PARKING_FEE:

                  break;

              //评价商品（含业态）
              case Constant.INTEGRAL_EVALUATE:

                  break;

              default:
                  break;

          }

          logger.info("IntegralGatherAPI [memberId:"+memberId+",businessId:"+businessId+"," +
                  "businessType:"+integralMessageBeanT.getBusinessType()+",isBack:"+integralMessageBeanT.getIsBack()+",porjectId:"+projectId+",optTime:"+integralMessageBeanT.getOptTime()+",cardinal:"+integralMessageBeanT.getCardinal()+",sbusinessId:"+integralMessageBeanT.getSbusinessId()+"]");

          if (QDStringUtil.isNotNull(memberId) && QDStringUtil.isNotEmpty(memberId)) {

              IntegralMessageBean integralMessageBean = new IntegralMessageBean();
              integralMessageBean.setProjectId(projectId);
              integralMessageBean.setOptTime(integralMessageBeanT.getOptTime());
              integralMessageBean.setMemberId(memberId);
              integralMessageBean.setIsBack(integralMessageBeanT.getIsBack());
              integralMessageBean.setBusinessType(integralMessageBeanT.getBusinessType());
              integralMessageBean.setBusinessId(businessId);
              integralMessageBean.setCardinal(QDStringUtil.isNull(null)||"".equals(integralMessageBeanT.getCardinal())?"1":integralMessageBeanT.getCardinal());
              integralMessageBean.setSbusinessId(integralMessageBeanT.getSbusinessId());

              String paramJsonStr = JSON.toJSONString(integralMessageBean);

              MsginfoRequest msginfoRequest = new MsginfoRequest();
              msginfoRequest.setName("积分采集调度");
              msginfoRequest.setType(type);
              msginfoRequest.setIscallback(0);
              msginfoRequest.setTourl(jobUrl);
              msginfoRequest.setToclass(toClass);
              msginfoRequest.setMaxRetrycount(maxRetrycount);//重试次数
              msginfoRequest.setRetryInterval(retryInterval);//单位: 毫秒
              msginfoRequest.setMsgBody(paramJsonStr);
              try {
                  MsginfoResponse msginfoResponse = remoteJobService.SendImessage(msginfoRequest);
                  logger.info("IntegralGatherAPI Imessage Response code:"+msginfoResponse.getReturnInfo().getCode()+"," +
                          "message:"+msginfoResponse.getReturnInfo().getMessage());
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }catch (Exception e) {

          e.printStackTrace();
      }


    }
    
    public MsginfoResponse sendImessage(MsginfoRequest request) throws Exception {
        MsginfoResponse response = remoteJobService.SendImessage(request);
        return response;
    }

    /**
     * 通过feedId获取feedBean
     * @param feedId
     * @return
     */
    private  FeedBean getFeedBeanByFeedId (String feedId){

        FeedBean feed =  hotCycleRemoteService.getFeedBean(feedId);
        return  feed;
    }

    private String  getMemberByFeedBean( FeedBean feed){

        String memberId ="";
        if (QDStringUtil.isNotNull(feed)) {
            String accountId = feed.getUserId();
            memberId = getMemberIdByAccountId ( accountId);
        }
        return memberId;
    }

    /**
     * 通过账户ID获取会员ID
     * @param accountId
     * @return
     */
    private   String getMemberIdByAccountId (String accountId) {

        String memberId = "";
        GetMemberByAccountIdRequest request = new GetMemberByAccountIdRequest();
        request.setAccountId(accountId);
        GetMemberResponse response = profileAPI.getMemberByAccountId(request);
        logger.info("IntegralGatherAPI  get member  for accountId is "+accountId+ " Response code:"+response.getReturnInfo().getCode()+"," +
                "message:"+response.getReturnInfo().getMessage());

        if(HttpStatus.OK.getStatusCode()!=response.getReturnInfo().getCode() || QDStringUtil.isNotNull(response.getMemberInfo())) {
            MemberInfo memberInfo = response.getMemberInfo();
            memberId = memberInfo.getId();
        }

        return memberId;
    }





    public String getImessageUrl() {
        return imessageUrl;
    }

    public void setImessageUrl(String imessageUrl) {
        this.imessageUrl = imessageUrl;
    }

    public  String getJobUrl() {
        return jobUrl;
    }

    public  void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public  Integer getType() {
        return type;
    }

    public  void setType(Integer type) {
        this.type = type;
    }

    public  String getToClass() {
        return toClass;
    }

    public  void setToClass(String toClass) {
        this.toClass = toClass;
    }

    public  int getMaxRetrycount() {
        return maxRetrycount;
    }

    public  void setMaxRetrycount(int maxRetrycount) {
        this.maxRetrycount = maxRetrycount;
    }

    public  Long getRetryInterval() {
        return retryInterval;
    }

    public  void setRetryInterval(Long retryInterval) {
        this.retryInterval = retryInterval;
    }
}
