package com.qding.api.call.app.qding.v1_3_0;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Dictonary;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.*;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.thrift.model.dictionary.Dictionary;
import com.qding.useraccount.service.IUserIntegralRemoteService;
import com.qding.useraccount.struct.request.*;
import com.qding.useraccount.struct.response.IntegralInfoResponse;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.request.AccountBalanceDetailRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.request.AccountPayDetailRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.request.AccountRefundDetailRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.request.AccountWalletHomePageRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.request.ChargeWalletRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.request.GetChargeValuesRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data.AccountBalanceDetailResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data.AccountPayDetailResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data.AccountRefundDetailResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data.AccountWalletHomePageResonseData;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data.ChargeWalletResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data.GetChargeValuesResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.smart.validate.WalletValidate;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.HttpMethod;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.oder.dto.OrderGeneratorDto;
import com.qding.oder.dto.SubOrderDto;
import com.qding.promotion.common.dto.PromotionCouponUserDto;
import com.qding.promotion.common.dto.TemplateWithCouponUserDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetUserCouponListRequest;
import com.qding.promotion.common.struct.response.GetUserCouponListResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.RechargeSkuRequest;
import com.qding.solr.struct.response.RechargeSkuResponse;
import com.qding.useraccount.common.AccountOptTypeEnum;
import com.qding.useraccount.service.IRUserPayPasswordService;
import com.qding.useraccount.service.IUserPredepositRemoteService;
import com.qding.useraccount.struct.UserPayPasswordInfo;
import com.qding.useraccount.struct.response.CreateOrderResponse;
import com.qding.useraccount.struct.response.PredepositDetailResponse;
import com.qding.useraccount.struct.response.PredepositInfoResponse;

/**
 * 我的钱包API
 * @author JIAWENZHENG
 *
 */
public class CallWallet extends Callable {
    
    @Autowired
    private IPromotionRemoteService promotionRemoteService;

    @Autowired
    private IUserPredepositRemoteService userPredepositAPI;
    
    @Autowired
    private IUserIntegralRemoteService userIntegralRemoteService;
    
    public CallWallet() {

    }

    public static WalletStatus getWalletStatus(String memberId) {
    	
		UserPayPasswordInfo payPassword = WalletValidate.getWalletPayPassword(memberId);

    	WalletStatus walletStatus = new WalletStatus();
         
        walletStatus.setStatus(
     		WalletValidate.getWalletStatus(payPassword)
     		);
         
        if(walletStatus.getStatus() == Constant.WALLET_STATUS_FORZEN) {
         	
        	walletStatus.setStatusTips(WalletValidate.getWalletStatusForzenTips(payPassword));
        }
         
        return walletStatus;
    }
    
    
    /**
     * 获取我得钱包首页信息
     * @param request
     * @return
     */
    @HTTP(alias = "accountWalletHomePage", isRequireAuth=true)
    @Deprecated
    public Response<AccountWalletHomePageResonseData> accountWalletHomePage(AccountWalletHomePageRequest request) {

        try {
            
            Response<AccountWalletHomePageResonseData> response = new Response<AccountWalletHomePageResonseData>();
            
            PredepositInfoRequest infoRequest = new PredepositInfoRequest();
            
            infoRequest.setMid(request.getMemberId());

            PredepositInfoResponse predepositInfoResponse = userPredepositAPI.get(infoRequest);
      
            checkAndContinue(predepositInfoResponse);
            
            AccountCollect account = transfor(AccountCollect.class, predepositInfoResponse);
            
            AccountWalletHomePageResonseData data = new AccountWalletHomePageResonseData();
            
            AccountWalletHome entity = new AccountWalletHome();
            
            entity.setAccount(account);
            
            entity.setAccountQdTicket(this.GetCouponMoneyForUser(request.getMemberId()));

            entity.setMemberId(request.getMemberId());
            IntegralInfoRequest integralInfoRequest = new IntegralInfoRequest();
            integralInfoRequest.setMid(request.getMemberId());

            IntegralInfoResponse integralInfoResponse = userIntegralRemoteService.get(integralInfoRequest);

            checkAndContinue(integralInfoResponse);

            BigDecimal accountIntegral =  integralInfoResponse.getAvailableNum();

            entity.setAccountIntegral(accountIntegral.longValue());
            
            entity.setWalletStatus(getWalletStatus(request.getMemberId()));

            List<Dictonary>  dictonaryList = getDictionary("my_wallet_sogan");

            if (QDStringUtil.isNotNull(dictonaryList) && dictonaryList.size()>0) {

                Dictonary dictonary = dictonaryList.get(0);

                String soganStr = dictonary.getValue();

                if (QDStringUtil.isNotNull(soganStr) && QDStringUtil.isNotEmpty(soganStr)) {

                    String[] soganStrArray = soganStr.split("\\|\\|");

                    String[] slogan ={QDStringUtil.isNotNull(soganStrArray)?soganStrArray[0]:""};

                    entity.setSlogan(slogan);

                    if (soganStrArray.length>1) {

                        String sloganDetail = soganStrArray[1];

                        entity.setSloganDetail(sloganDetail);
                    }
                }
            }
            
            data.setEntity(entity);
            
            response.setData(data);

            return response;
            
        } catch (Exception e) {

            return handleException(AccountWalletHomePageResonseData.class, e);
        }
    }

    /**
     * 账户余额明细(--预存款退款明细)
     * @param request
     * @return
     */
    @HTTP(alias = "accountBalanceDetail", isRequireAuth=true)
    public Response<AccountBalanceDetailResponseData> accountBalanceDetail(AccountBalanceDetailRequest request) {

        try {
            
            Response<AccountBalanceDetailResponseData> response = new Response<AccountBalanceDetailResponseData>();

            PredepositDetailResponse detailResponse = userPredepositAPI.getDetails(transfor(PredepositDetailRequest.class, request));
          
            checkAndContinue(detailResponse);
            
            AccountBalanceDetailResponseData data =  transfor(AccountBalanceDetailResponseData.class, detailResponse);

            List<BalanceDetail> balanceDetailList=  data.getList();

            if (QDStringUtil.isNotNull(balanceDetailList) && balanceDetailList.size()>0) {

                for (BalanceDetail balanceDetail : balanceDetailList) {
                    if (1 == balanceDetail.getIsFamilyPay()){
                        balanceDetail.setPayTypeName("亲情支付");
                    }
                }
            }

            response.setData(data);
            
            return response;
            
        } catch (ServiceException e) {
          
            return handleException(AccountBalanceDetailResponseData.class, e);
        }
    }
    

    /**
     * 账户支付明细
     * @param request
     * @return
     */
    @HTTP(alias = "accountPayDetail", isRequireAuth=true)
    public Response<AccountPayDetailResponseData> accountPayDetail(AccountPayDetailRequest request) {

        try {
            
            Response<AccountPayDetailResponseData> response = new Response<AccountPayDetailResponseData>();

            PredepositDetailRequest predepositDetailRequest =  transfor(PredepositDetailRequest.class, request);
           
            //选择支付类型
            predepositDetailRequest.setOptType(AccountOptTypeEnum.EXPENDITURE);
            
            PredepositDetailResponse detailResponse = userPredepositAPI.getDetails(predepositDetailRequest);
            
            checkAndContinue(detailResponse);
            
            AccountPayDetailResponseData data = transfor(AccountPayDetailResponseData.class, detailResponse);
            
            List<MoneyDetail> moneyDetailList=  data.getList();
            
            if (QDStringUtil.isNotNull(moneyDetailList) && moneyDetailList.size()>0) {
            	
            	 for (MoneyDetail moneyDetail : moneyDetailList) {

                       moneyDetail.setOptTypeValue(2);
                        Integer isPay = 1;
                        if ( isPay == Integer.parseInt(moneyDetail.getIsFamilyPay()+"")){
                             moneyDetail.setPayTypeName("亲情支付");
                         }
                     if(Constant.PRODUCT_SKIP_APP.equals(moneyDetail.getSkipType())) continue;
                     moneyDetail = APIPropertiesClient.getProductOrderUrl(moneyDetail);
     			}
            	
            }
            
            response.setData(data);
            
            return response;
            
        } catch (ServiceException e) {
           
            return handleException(AccountPayDetailResponseData.class, e);
        }
    }

    
    /**
     * 账户退款明细
     * @param request
     * @return
     */
    @HTTP(alias = "accountRefundDetail", isRequireAuth=true)
    public Response<AccountRefundDetailResponseData> accountRefundDetail(AccountRefundDetailRequest request) {

        try {
            
            Response<AccountRefundDetailResponseData> response = new Response<AccountRefundDetailResponseData>();
            
            PredepositDetailResponse detailResponse = userPredepositAPI.getRestoreDetails(transfor(PredepositRestoreDetailRequest.class, request));
            
            checkAndContinue(detailResponse);
            
            AccountRefundDetailResponseData  data = transfor(AccountRefundDetailResponseData.class, detailResponse);
            
            List<MoneyDetail> moneyDetailList=  data.getList();
            
            if (QDStringUtil.isNotNull(moneyDetailList) && moneyDetailList.size()>0) {
            	
           	 for (MoneyDetail moneyDetail : moneyDetailList) {

                     if (1 == moneyDetail.getIsFamilyPay()){
                         moneyDetail.setPayTypeName("亲情支付");
                     }

                 moneyDetail.setOptTypeValue(1);
                 if(Constant.PRODUCT_SKIP_APP.equals(moneyDetail.getSkipType())) continue;
                 moneyDetail=APIPropertiesClient.getProductOrderUrl(moneyDetail);
                	
    			}
           	
           }
            
            response.setData(data);
            
            return response;
            
        } catch (ServiceException e) {
            
            return handleException(AccountRefundDetailResponseData.class, e);
        }
    }
    
    
    @Autowired
    private ProjectReadRemote projectReadRemote;
    
    /**
     * 生成钱包充值订单
     * @param request
     * @return
     */
    @HTTP(alias="chargeWallet", supportMethod=HttpMethod.POST, isRequireAuth=true)
    public Response<ChargeWalletResponseData> chargeWallet(ChargeWalletRequest request) {
    	
    	try {
			//会员ID
			String memberId = request.getMemberId();
			//小区ID
			String projectId = request.getProjectId();
			//订单来源
			Integer orderSourceType = request.getOrderSourceType();
			//充值商品
			Long skuId = Long.parseLong(request.getSkuId());

			Project project = projectReadRemote.get(Long.parseLong(projectId));
			
			if(project == null) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
			}
			
			
			CreateOrderRequest createOrderRequest = new CreateOrderRequest();
			
			OrderGeneratorDto o = new OrderGeneratorDto();
			
			o.setMid(memberId);
			o.setProjectId(project.getId());
			o.setRegionId(project.getRegionId());
			o.setProjectName(project.getName());
			o.setPublicsId(QDStringUtil.isEmpty(request.getPublicsId()) ? null : Long.parseLong(request.getPublicsId()));
			o.setSourceType(orderSourceType);

			SubOrderDto subOrderDto = new SubOrderDto();
			subOrderDto.setWareCount(1);
			subOrderDto.setWareSkuId(skuId);
			
			o.setSubOrderlist(Arrays.asList(subOrderDto));
			
			createOrderRequest.setOrderDto(o);
			
			CreateOrderResponse createOrderResponse = userPredepositAPI.createGorder(createOrderRequest);
			checkAndContinue(createOrderResponse);
			
			ChargeWalletResponseData data = transfor(ChargeWalletResponseData.class, createOrderResponse);
			
			Response<ChargeWalletResponseData> response = new Response<>();
			
			response.setData(data);
			
			return response;
			
		} catch (ServiceException e) {
			
			return handleException(ChargeWalletResponseData.class, e);
		}
    }
    
    
    @Autowired
	private ISolrSkuService solrSku;
	
    /**
     * 获取充值列表
     * @param request
     * @return
     */
    @HTTP(alias="getChargeValues", isRequireAuth=true)
    public Response<GetChargeValuesResponseData> getChargeValues(GetChargeValuesRequest request) {
    	
    	try {
    		
			RechargeSkuResponse getChargeSkuResponse = solrSku.queryRechargeSku(new RechargeSkuRequest());
			
			checkAndContinue(getChargeSkuResponse);
			
			GetChargeValuesResponseData data = transfor(GetChargeValuesResponseData.class, getChargeSkuResponse);
			
			Response<GetChargeValuesResponseData> response = new Response<>();
			
			response.setData(data);
			
			return response;
		
    	} catch (Exception e) {
    		
    		return handleException(GetChargeValuesResponseData.class, e);
    	}
    	
    }
    /**
     * 千丁劵余额计算(不区分社区和业态)
     * 
     * @param
     * @return
     * @throws Exception
     */
    private int GetCouponMoneyForUser(String memberId) throws Exception{
            
		GetUserCouponListRequest request = new GetUserCouponListRequest();
    		
		request.setMid(memberId);
          
		GetUserCouponListResponse getUserCouponListResponse = promotionRemoteService.getUserCouponList(request);
            
        checkAndContinue(getUserCouponListResponse);
    
        //计算所有优惠券的总价值
        Integer totalCouponsPrice = 0;
            
        Iterator<PromotionCouponUserDto> commonIterator = getUserCouponListResponse.getCommonList().iterator();
            
        while(commonIterator.hasNext()) {
                
            PromotionCouponUserDto next = commonIterator.next();
                
            totalCouponsPrice += next.getPrice();
        }
           
        Iterator<TemplateWithCouponUserDto> specialBatchIterator = getUserCouponListResponse.getSpecialList().iterator();
           
        while(specialBatchIterator.hasNext()) {
                
            TemplateWithCouponUserDto nextBatch = specialBatchIterator.next();
                
            Iterator<PromotionCouponUserDto> specialIterator = nextBatch.getCouponUserDtoList().iterator();
                
            while(specialIterator.hasNext()) {
                
                PromotionCouponUserDto next = specialIterator.next();
                    
                totalCouponsPrice += next.getPrice();
            }
        }
            
        return totalCouponsPrice;
    }

    public List<Dictonary> getDictionary(String groupName) {
        try {
            List<Dictionary> d = DictionaryClient.findDictionaryByGroupNameOrderBySortAsc(groupName);
            return transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Dictonary.class, d);
        } catch (TException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
}
