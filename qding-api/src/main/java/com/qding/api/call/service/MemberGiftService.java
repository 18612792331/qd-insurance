package com.qding.api.call.service;

import com.google.common.collect.Lists;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v3_0_0.struct.user.GiftInfoDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.user.GiftLableDTO;
import com.qding.api.constant.Constant;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberRoom;
import com.qding.passport.struct.MemberInfo;
import com.qding.promotion.common.domain.PromotionGiftPackageCoupon;
import com.qding.promotion.common.domain.PromotionGiftPackageWare;
import com.qding.promotion.common.params.PromotionGiftPackageParams;
import com.qding.promotion.common.service.IPromotionGiftPackageRemoteService;
import com.qding.promotion.common.struct.request.GiftPackageReceiveDetailRequest;
import com.qding.promotion.common.struct.request.PromotionGiftPackageRequest;
import com.qding.promotion.common.struct.response.PromotionGiftPackageReceiveResponse;
import com.qding.promotion.common.struct.response.PromotionGiftPackageResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qd on 2017/8/18.
 */
public class MemberGiftService extends Callable {

    private static final Logger logger = Logger.getLogger(MemberGiftService.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private IPromotionGiftPackageRemoteService promotionGiftPackageRemoteService;


    //检查是否显示新手礼包
    public boolean  checkGiftPackage(String curMemberId,Long projectId) throws ServiceException{

        PromotionGiftPackageRequest promotionGiftPackageRequest = new PromotionGiftPackageRequest();
        if (QDStringUtil.isNotEmpty(curMemberId)) {
            List<MemberRoom>  rooms = memberService.getRoomsByMultiRole(curMemberId, String.valueOf(projectId),Constant.GIFT_ROLE_LIST,true);//可领取礼包房屋ID （当前社区，当前人，拥有业主|亲人|租客身份的最早绑定的房屋）
            if (CollectionUtils.isNotEmpty(rooms)) {
                String roomId = rooms.get(0).getRoomId();
                promotionGiftPackageRequest.setRoomId(roomId);
            }
            promotionGiftPackageRequest.setMid(curMemberId);
        }

        promotionGiftPackageRequest.setProjectId(String.valueOf(projectId));
        PromotionGiftPackageResponse promotionGiftPackageResponse = promotionGiftPackageRemoteService.getGiftPackageInfoCommon(promotionGiftPackageRequest);
        checkAndContinue(promotionGiftPackageResponse);
        List<PromotionGiftPackageParams>  promotionGiftPackageParamsList = promotionGiftPackageResponse.getGiftPackageParamsList();

        //如果当前社区没有配置礼包
        if(CollectionUtils.isEmpty(promotionGiftPackageParamsList)) {
            return false;
        }
        //如果未登录，且当前社区配置了新手礼包
        if(QDStringUtil.isEmpty(curMemberId) && CollectionUtils.isNotEmpty(promotionGiftPackageParamsList) ) {
            return true;
        }

        PromotionGiftPackageReceiveResponse receiveInfo = promotionGiftPackageResponse.getReceiveInfo();
        if ( QDStringUtil.isNull(receiveInfo)) {
            logger.error("Class:MemberGiftService,method:checkGiftPackage, get promotion PromotionGiftPackageReceiveResponse is null ! ");
            return  false;
        }
        if (receiveInfo.getGoodsReceivable() || receiveInfo.getCouponsReceivable()){
            return  true;
        }

        return false;
    }


    /**
     * 获取指定用户不分社区领取过的礼包
     * @param memberInfo
     * @param promotionGiftPackageResponse
     * @return
     */
    public  List<GiftInfoDTO>  getReceivedGiftForAllProject(MemberInfo memberInfo, PromotionGiftPackageResponse promotionGiftPackageResponse) {

        List<GiftInfoDTO> receivedList = Lists.newArrayList();
        List<PromotionGiftPackageParams> receivedPackageList = promotionGiftPackageResponse.getReceivedPackageList();
        if (CollectionUtils.isNotEmpty(receivedPackageList)) {
            for (PromotionGiftPackageParams giftPackageParams : receivedPackageList) {
                if ( Constant.GIFT_TYPE_SKU == giftPackageParams.getType() ) {   //实物礼包
                    List<PromotionGiftPackageWare> wareList = giftPackageParams.getGiftPackageWareList();
                    if (CollectionUtils.isNotEmpty(wareList)) {
                        for (PromotionGiftPackageWare giftPackageWare : wareList) {
                            GiftInfoDTO giftInfoDTO = new GiftInfoDTO(giftPackageWare.getId(), giftPackageWare.getWareName(), Constant.GIFT_TYPE_SKU + "",
                                    giftPackageWare.getImage(), giftPackageWare.getIntroduction(), giftPackageWare.getSkuId(),
                                    Constant.GIFT_CANUSE_NO,Constant.GIFT_TYPE_SKU);
                            receivedList.add(giftInfoDTO);
                        }

                    }
                } else {
                    List<PromotionGiftPackageCoupon> couponList = giftPackageParams.getGiftPackageCouponList();
                    boolean isNewMember = false;
                    if (memberInfo.getCreateTime() > giftPackageParams.getValidStart()) {
                        isNewMember = true;
                    }
                    if (CollectionUtils.isNotEmpty(couponList)) {
                        for (PromotionGiftPackageCoupon giftPackageCoupon : couponList) {
                            if (giftPackageCoupon.getType() == (isNewMember?1:2)) {
                                GiftInfoDTO giftInfoDTO = new GiftInfoDTO(giftPackageCoupon.getId(), giftPackageCoupon.getTitle(), isNewMember?String.valueOf( Constant.GIFT_TYPE_QUAN_NEW):String.valueOf( Constant.GIFT_TYPE_QUAN_OLD) ,
                                        giftPackageCoupon.getImage(), giftPackageCoupon.getIntroduction(), giftPackageCoupon.getId(),Constant.GIFT_CANUSE_NO, isNewMember?Constant.GIFT_TYPE_QUAN_NEW:Constant.GIFT_TYPE_QUAN_OLD);
                                receivedList.add(giftInfoDTO);
                            }
                        }

                    }
                }
            }
        }
        return receivedList;
    }


    /**
     * 获取指定用户指定房屋下未领取过的礼包
     * @param roomId
     * @param memberInfo
     * @param promotionGiftPackageResponse
     * @return
     */
    public  List<GiftInfoDTO>  getUnclaimedGiftPackageByProjectId( String roomId, MemberInfo memberInfo, PromotionGiftPackageResponse promotionGiftPackageResponse) {

        List<GiftInfoDTO> unclaimedlist = Lists.newArrayList();
        List<PromotionGiftPackageParams> giftPackageParamsList = promotionGiftPackageResponse.getGiftPackageParamsList();
        PromotionGiftPackageReceiveResponse receiveInfo = promotionGiftPackageResponse.getReceiveInfo();
        if ( QDStringUtil.isNull(receiveInfo)) {
            logger.error("Class:MemberGiftService,method:getUnclaimedGiftPackageByProjectId, get promotion PromotionGiftPackageReceiveResponse is null ! ");
            return  unclaimedlist;
        }
        if (CollectionUtils.isNotEmpty(giftPackageParamsList)  ) {
            for (PromotionGiftPackageParams giftPackageParams : giftPackageParamsList) {
                int type = giftPackageParams.getType();
                if ( Constant.GIFT_TYPE_SKU == type ) { //实物礼包
                    List<PromotionGiftPackageWare> wareList = giftPackageParams.getGiftPackageWareList();
                    if (CollectionUtils.isNotEmpty(wareList)) {
                        if ( receiveInfo.getGoodsReceivable()) { //如果未领取过实物礼包,且当前礼包可以领
                            for (PromotionGiftPackageWare giftPackageWare : wareList) {
                                GiftInfoDTO giftInfoDTO = new GiftInfoDTO(giftPackageWare.getId(), giftPackageWare.getTitle(), Constant.GIFT_TYPE_SKU + "",
                                        giftPackageWare.getImage(), giftPackageWare.getIntroduction(), giftPackageWare.getSkuId(),
                                        QDStringUtil.isNotEmpty(roomId)?Constant.GIFT_CANUSE_YES:Constant.GIFT_CANUSE_NOBINGROOM,Constant.GIFT_TYPE_SKU);
                                unclaimedlist.add(giftInfoDTO);
                            }
                        }
                    }
                } else {  //券礼包
                    List<PromotionGiftPackageCoupon>  couponList = giftPackageParams.getGiftPackageCouponList();
                    if (CollectionUtils.isNotEmpty(couponList)) {
                        if ( receiveInfo.getCouponsReceivable()) { //如果未领取过券礼包，且当前礼包可以领

                            // 资料是否完善
                            boolean isComplete = false;
                            if (StringUtils.isNotBlank(memberInfo.getHeadImg()) && StringUtils.isNotBlank(memberInfo.getName())
                                    && memberInfo.getBirthday() != 0L && memberInfo.getFamilyStatus() != 0 && QDStringUtil.isNotEmpty(roomId)) {
                                isComplete = true;
                            }
                            boolean isNewMember = false;
                            if (memberInfo.getCreateTime() > giftPackageParams.getValidStart()) {
                                isNewMember = true;
                            }

                            for (PromotionGiftPackageCoupon giftPackageCoupon : couponList) {
                                if ((isNewMember && giftPackageCoupon.getType()==1)|| (!isNewMember && giftPackageCoupon.getType()==2) ) {
                                    GiftInfoDTO giftInfoDTO  = new GiftInfoDTO(giftPackageCoupon.getGiftPackageId(), giftPackageCoupon.getTitle(), isNewMember?String.valueOf( Constant.GIFT_TYPE_QUAN_NEW):String.valueOf( Constant.GIFT_TYPE_QUAN_OLD) ,
                                            giftPackageCoupon.getImage(), giftPackageCoupon.getIntroduction(), giftPackageCoupon.getId(),
                                            isComplete?Constant.GIFT_CANUSE_YES:Constant.GIFT_CANUSE_INCOMPLETE, isNewMember?Constant.GIFT_TYPE_QUAN_NEW:Constant.GIFT_TYPE_QUAN_OLD);
                                    unclaimedlist.add(giftInfoDTO);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return  unclaimedlist;
    }
}
