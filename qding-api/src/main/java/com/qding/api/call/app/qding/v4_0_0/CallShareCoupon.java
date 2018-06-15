package com.qding.api.call.app.qding.v4_0_0;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;

import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.ShareCouponDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.SkuDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.request.ShareCouponRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.response.CouponDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.response.ShareCouponDetailDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.response.ShareCouponResponseData;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.promotion.common.domain.sharecoupon.ShareCouponGroupDetail;
import com.qding.promotion.common.dto.PromotionCouponUserDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.service.IShareCouponRemoteService;
import com.qding.promotion.common.struct.response.GetPromotionCouponByCodeResponse;
import com.qding.promotion.common.struct.response.sharecoupon.ShareCouponResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.SkuRequest;
import com.qding.solr.struct.response.SkuResponse;
import com.qding.solr.struct.sku.SkuDetailInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxiaoxing on 2018/5/3.
 */
@ExplainAnnotation(explain = "拼团领券")
public class CallShareCoupon extends Callable {

    private static final Logger logger = Logger.getLogger(CallShareCoupon.class);

    @Autowired
    private IShareCouponRemoteService shareCouponRemoteService;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private IPromotionRemoteService promotionRemoteService;

    @Autowired
    private ISolrSkuService solrSkuService;


    @ExplainAnnotation(explain = "创建拼团")
    @HTTP(alias = "createShareCoupon", isNeadToken = true, isRequireAuth = true)
    public Response<ShareCouponResponseData> createShareCoupon(ShareCouponRequest request, UserToken userToken){
        Response<ShareCouponResponseData> response = new Response<ShareCouponResponseData>();
        ShareCouponResponseData data = new ShareCouponResponseData();
        try{
            if(QDStringUtil.isEmpty(request.getShareId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "shareId为空");
            }
            if(QDStringUtil.isEmpty(userToken.getMemberId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "memberId为空");
            }
            MemberInfo memberInfo = getMemberInfo(userToken.getMemberId());

            ShareCouponResponse shareCouponResponse = shareCouponRemoteService.createShareGroup(request.getShareId(),memberInfo.getId(),memberInfo.getMobile());
            checkAndContinue(shareCouponResponse);

            ShareCouponDTO shareCouponDTO = new ShareCouponDTO();

            initShareCouponDTO(shareCouponDTO,shareCouponResponse);

            data.setShareCouponDTO(shareCouponDTO);

            data.setSkuDTOList(getSkuDtoList(request.getSkuIds()));

            response.setCode(HttpStatus.OK.getStatusCode());
        }catch (Exception e){
            return handleException(ShareCouponResponseData.class, e);
        }
        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "参加拼团")
    @HTTP(alias = "joinShareCoupon", isNeadToken = true, isRequireAuth = true)
    public Response<ShareCouponResponseData> joinShareCoupon(ShareCouponRequest request, UserToken userToken){
        Response<ShareCouponResponseData> response = new Response<ShareCouponResponseData>();
        ShareCouponResponseData data = new ShareCouponResponseData();
        try{
            if(QDStringUtil.isEmpty(request.getGroupId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "groupId为空");
            }
            if(QDStringUtil.isEmpty(userToken.getMemberId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "memberId为空");
            }
            MemberInfo memberInfo = getMemberInfo(userToken.getMemberId());

            ShareCouponResponse shareCouponResponse = shareCouponRemoteService.joinShareGroup(request.getGroupId(),memberInfo.getId(),memberInfo.getMobile());
            checkAndContinue(shareCouponResponse);

            ShareCouponDTO shareCouponDTO = new ShareCouponDTO();

            initShareCouponDTO(shareCouponDTO,shareCouponResponse);

            data.setShareCouponDTO(shareCouponDTO);

            data.setSkuDTOList(getSkuDtoList(request.getSkuIds()));

            response.setCode(HttpStatus.OK.getStatusCode());
        }catch (Exception e){
            return handleException(ShareCouponResponseData.class, e);
        }
        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "获取拼团信息")
    @HTTP(alias = "getShareCoupon")
    public Response<ShareCouponResponseData> getShareCoupon(ShareCouponRequest request){
        Response<ShareCouponResponseData> response = new Response<ShareCouponResponseData>();
        ShareCouponResponseData data = new ShareCouponResponseData();
        try{
            if(QDStringUtil.isEmpty(request.getGroupId())){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "groupId为空");
            }
            ShareCouponResponse shareCouponResponse = shareCouponRemoteService.getShareGroup(request.getGroupId());
            checkAndContinue(shareCouponResponse);

            ShareCouponDTO shareCouponDTO = new ShareCouponDTO();

            initShareCouponDTO(shareCouponDTO,shareCouponResponse);

            data.setShareCouponDTO(shareCouponDTO);

            data.setSkuDTOList(getSkuDtoList(request.getSkuIds()));

            response.setCode(HttpStatus.OK.getStatusCode());
        }catch (Exception e){
            return handleException(ShareCouponResponseData.class, e);
        }
        response.setData(data);
        return response;
    }

    @ExplainAnnotation(explain = "获取活动商品")
    @HTTP(alias = "getShareCouponSku")
    public Response<ShareCouponResponseData> getShareCouponSku(ShareCouponRequest request){
        Response<ShareCouponResponseData> response = new Response<ShareCouponResponseData>();
        ShareCouponResponseData data = new ShareCouponResponseData();
        try{
            if(request.getSkuIds()==null || request.getSkuIds().size()==0){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "skuIds为空");
            }

            ShareCouponDTO shareCouponDTO = new ShareCouponDTO();
            data.setShareCouponDTO(shareCouponDTO);
            data.setSkuDTOList(getSkuDtoList(request.getSkuIds()));

            response.setCode(HttpStatus.OK.getStatusCode());
        }catch (Exception e){
            return handleException(ShareCouponResponseData.class, e);
        }
        response.setData(data);
        return response;
    }


    /**
     * 获取活动商品信息
     * @param skuIds
     * @return
     */
    private List<SkuDTO> getSkuDtoList(List<Long> skuIds){
        if(skuIds==null || skuIds.size()==0){
            return new ArrayList<SkuDTO>(0);
        }
        List<SkuDTO> list = new ArrayList<>();
        try{
            SkuRequest skuRequest = new SkuRequest();
            skuRequest.setSortedSkuIds(skuIds);
            SkuResponse skuResponse = solrSkuService.querySku(skuRequest);
            if(skuResponse!=null && skuResponse.getReturnInfo().getCode()==200){
                if(skuResponse.getSkus()!=null && skuResponse.getSkus().size()>0){
                    for(SkuDetailInfo skuDetailInfo : skuResponse.getSkus()){
                        SkuDTO dto = new SkuDTO();
                        dto.setPrice(skuDetailInfo.getPrice());
                        dto.setMarketPrice(skuDetailInfo.getMarketPrice());
                        dto.setName(skuDetailInfo.getName());
                        dto.setSkuId(Long.valueOf(skuDetailInfo.getSkuId()));
                        dto.setSkuImgUrl(skuDetailInfo.getSkuImgUrl());
                        dto.setWareImgUrl(skuDetailInfo.getWareImgUrl());
                        list.add(dto);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private MemberInfo getMemberInfo(String memberId) throws ServiceException{
        GetMemberRequest memberRequest = new GetMemberRequest();
        memberRequest.setMemberId(memberId);
        GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
        checkAndContinue(memberResponse);
        return memberResponse.getMemberInfo();
    }

    private void initShareCouponDTO(ShareCouponDTO shareCouponDTO,ShareCouponResponse shareCouponResponse) throws ServiceException{
        shareCouponDTO.setShareId(shareCouponResponse.getShareCouponActivityInfoDto().getShareCouponActivity().getId());
        shareCouponDTO.setActivityName(shareCouponResponse.getShareCouponActivityInfoDto().getShareCouponActivity().getName());
        shareCouponDTO.setStartTime(shareCouponResponse.getShareCouponActivityInfoDto().getShareCouponActivity().getStartTime());
        shareCouponDTO.setEndTime(shareCouponResponse.getShareCouponActivityInfoDto().getShareCouponActivity().getEndTime());
        shareCouponDTO.setMaxNum(shareCouponResponse.getShareCouponActivityInfoDto().getShareCouponActivity().getMaxPerson());
        shareCouponDTO.setGroupId(shareCouponResponse.getShareCouponGroupDto().getShareCouponGroup().getId());
        shareCouponDTO.setGroupStatus(shareCouponResponse.getShareCouponGroupDto().getShareCouponGroup().getGroupStatus());

        shareCouponDTO.setShareContent(shareCouponResponse.getShareCouponActivityInfoDto().getShareCouponActivity().getShareContent());
        shareCouponDTO.setShareTitle(shareCouponResponse.getShareCouponActivityInfoDto().getShareCouponActivity().getShareTitle());
        shareCouponDTO.setShareImage(shareCouponResponse.getShareCouponActivityInfoDto().getShareCouponActivity().getShareImage());

        if(shareCouponResponse.getShareCouponGroupDto().getDetailList()!=null){
            List<ShareCouponDetailDTO> shareCouponDetailDTOList = new ArrayList<ShareCouponDetailDTO>(shareCouponResponse.getShareCouponGroupDto().getDetailList().size());
            for(ShareCouponGroupDetail detail : shareCouponResponse.getShareCouponGroupDto().getDetailList()){
                MemberInfo memberInfo = getMemberInfo(detail.getMemberId());
                ShareCouponDetailDTO dto = new ShareCouponDetailDTO();
                dto.setRole(detail.getRole());
                dto.setMemberId(memberInfo.getId());
                dto.setImageUrl(memberInfo.getHeadImg());
                dto.setMobile(memberInfo.getMobile());
                dto.setName(memberInfo.getName());
                if(QDStringUtil.isNotEmpty(detail.getCouponCode())){
                    //获取券信息
                    PromotionCouponUserDto pcu = getCouponInfoByCode(detail.getCouponCode());
                    if(pcu!=null && QDStringUtil.isNotEmpty(pcu.getCode())) {
                        CouponDTO couponDTO = new CouponDTO();
                        couponDTO.setCode(pcu.getCode());
                        couponDTO.setDesc(pcu.getDesc());
                        couponDTO.setEndTime(pcu.getValidEnd());
                        couponDTO.setNote(pcu.getNote());
                        couponDTO.setStartTime(pcu.getValidStart());
                        couponDTO.setPrice(pcu.getPrice());
                        dto.setCouponDTO(couponDTO);
                    }
                }
                shareCouponDetailDTOList.add(dto);
            }
            shareCouponDTO.setShareCouponDetailDTOList(shareCouponDetailDTOList);
        }

    }

    private PromotionCouponUserDto getCouponInfoByCode(String code) throws ServiceException{
        GetPromotionCouponByCodeResponse response = promotionRemoteService.getPromotionCouponByCode(code);
        checkAndContinue(response);
        return response.getPromotionCouponUserDto();
    }
}
