package com.qding.api.call.app.qding.v3_3_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.BuyGoodInfoBean;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.AfterSalesDetailDto;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.AfterSalesReasonDto;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.AfterSalesSubOrderDto;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.AfterSalesTypeDto;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.request.*;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.response.data.*;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.legou.domain.OrderGoods;
import com.qding.legou.dto.AfterSalesApplyDto;
import com.qding.legou.dto.LegouOrderDetailDto;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.GetOrderDetailByCodeRequest;
import com.qding.legou.struct.request.UploadAfterSalesImgRequest;
import com.qding.legou.struct.response.*;
import com.qding.newsell.service.IAfterSalesRemoteService;
import com.qding.thrift.model.dictionary.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CallNewSell extends Callable {

    private final static List<String> supportImageType = new ArrayList<String>();

    static {
        supportImageType.add(".jpg");
        supportImageType.add(".jpeg");
        supportImageType.add(".png");
    }


    @Autowired
    private IAfterSalesRemoteService afterSalesRemoteService;

    @Autowired
    private ILegouRemoteService legouRemoteService;


    @ExplainAnnotation(explain = "通过主订单获取子订单列表")
    @HTTP(alias = "getSubOrdersByPcode")
    public Response<GetSubOrdersByPcodeResponseData> getSubOrdersByPcode(GetSubOrdersByPcodeRequest request) {

        Response<GetSubOrdersByPcodeResponseData> response = new Response<GetSubOrdersByPcodeResponseData>();
        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
        try {
            GetOrderDetailByCodeRequest orderDetailByCodeRequest = new GetOrderDetailByCodeRequest();
            orderDetailByCodeRequest.setOrderCode(request.getOrderCode());
            GetOrderDetailByCodeResponse orderDetailByCodeResponse = legouRemoteService.getOrderDetailByCode(orderDetailByCodeRequest);
            checkAndContinue(orderDetailByCodeResponse);
            LegouOrderDetailDto legouOrderDetailDto = orderDetailByCodeResponse.getLegouOrderDetailDto();
            List<OrderGoods> orderGoodsList = legouOrderDetailDto.getOrderGoodsList();

            com.qding.legou.struct.request.GetAfterSalesDetailsByOrderCodeRequest getAfterSalesDetailsByOrderCodeRequest = new com.qding.legou.struct.request.GetAfterSalesDetailsByOrderCodeRequest();
            getAfterSalesDetailsByOrderCodeRequest.setOrderCode(request.getOrderCode());
            GetAfterSalesDetailsByOrderCodeResponse getAfterSalesDetailsByOrderCodeResponse = afterSalesRemoteService.getAfterSalesDetailsByOrderCode(getAfterSalesDetailsByOrderCodeRequest);
            checkAndContinue(getAfterSalesDetailsByOrderCodeResponse);
            List<com.qding.legou.dto.AfterSalesDetailDto> afterSalesDetailDtoList = getAfterSalesDetailsByOrderCodeResponse.getAfterSalesDetailList();
            List<String> afterSalesSubCodeList = Lists.newArrayList();
            for (com.qding.legou.dto.AfterSalesDetailDto afterSalesDetailDto : afterSalesDetailDtoList) {
                afterSalesSubCodeList.add(afterSalesDetailDto.getQdSubOrderCode());
            }

            List<AfterSalesSubOrderDto> list = Lists.newArrayList();
            for (OrderGoods orderGoods : orderGoodsList) {
                if (Constant.NEWSE_PSF_MAKING_CODE.equals(orderGoods.getMarkingCode())) continue;
                AfterSalesSubOrderDto afterSalesSubOrderDto = transfor(AfterSalesSubOrderDto.class, orderGoods);
                boolean isExit = afterSalesSubCodeList.contains(orderGoods.getCode());
                if (isExit) {
                    afterSalesSubOrderDto.setIsAfterSalesApply(1);
                } else {
                    afterSalesSubOrderDto.setIsAfterSalesApply(0);
                }
                list.add(afterSalesSubOrderDto);
            }
            GetSubOrdersByPcodeResponseData data = new GetSubOrdersByPcodeResponseData();
            data.setList(list);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (Exception ex) {
            return handleException(GetSubOrdersByPcodeResponseData.class, ex);
        }
        return response;
    }


    @ExplainAnnotation(explain = "售后申请")
    @HTTP(alias = "afterSalesApply", isNeadToken = true, isRequireAuth = true)
    public Response<AfterSalesApplyResponseData> afterSalesApply(AfterSalesApplyRequest request, UserToken userToken) {

        Response<AfterSalesApplyResponseData> response = new Response<AfterSalesApplyResponseData>();
        AfterSalesApplyResponseData data = new AfterSalesApplyResponseData();
        try {
            AfterSalesApplyDto afterSalesApply = transfor(AfterSalesApplyDto.class, request);
            afterSalesApply.setMemberId(userToken.getMemberId());
            com.qding.legou.struct.request.AfterSalesApplyRequest applyRequest = new com.qding.legou.struct.request.AfterSalesApplyRequest();
            applyRequest.setAfterSalesApply(afterSalesApply);
            AfterSalesApplyResponse afterSalesApplyResponse = afterSalesRemoteService.afterSalesApply(applyRequest);
            checkAndContinue(afterSalesApplyResponse);
        } catch (Exception ex) {
            return handleException(AfterSalesApplyResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "指定订单下售后申请")
    @HTTP(alias = "getAfterSalesDetailsByOrderCode")
    public Response<GetAfterSalesDetailsByOrderCodeResponseData> getAfterSalesDetailsByOrderCode(GetAfterSalesDetailsByOrderCodeRequest request) {
        Response<GetAfterSalesDetailsByOrderCodeResponseData> response = new Response<GetAfterSalesDetailsByOrderCodeResponseData>();
        GetAfterSalesDetailsByOrderCodeResponseData data = new GetAfterSalesDetailsByOrderCodeResponseData();
        try {
            com.qding.legou.struct.request.GetAfterSalesDetailsByOrderCodeRequest getAfterSalesDetailsByOrderCodeRequest = transfor(com.qding.legou.struct.request.GetAfterSalesDetailsByOrderCodeRequest.class, request);
            GetAfterSalesDetailsByOrderCodeResponse getAfterSalesDetailsByOrderCodeResponse = afterSalesRemoteService.getAfterSalesDetailsByOrderCode(getAfterSalesDetailsByOrderCodeRequest);
            checkAndContinue(getAfterSalesDetailsByOrderCodeResponse);
            List<AfterSalesDetailDto> afterSalesDetailList = transforList(AfterSalesDetailDto.class, getAfterSalesDetailsByOrderCodeResponse.getAfterSalesDetailList());
            data.setAfterSalesDetailList(afterSalesDetailList);
        } catch (Exception ex) {
            return handleException(GetAfterSalesDetailsByOrderCodeResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;

    }

    @ExplainAnnotation(explain = "获取指定第三方支持的物流公司")
    @HTTP(alias = "getLogisticsCompanys")
    public Response<GetLogisticsCompanysResponseData> getLogisticsCompanys(GetLogisticsCompanysRequest request) {

        Response<GetLogisticsCompanysResponseData> response = new Response<GetLogisticsCompanysResponseData>();
        GetLogisticsCompanysResponseData data = new GetLogisticsCompanysResponseData();
        try {
            com.qding.legou.struct.request.GetLogisticsCompanysRequest getLogisticsCompanysRequest = new com.qding.legou.struct.request.GetLogisticsCompanysRequest();
            getLogisticsCompanysRequest.setSourceType(request.getSourceType());
            GetLogisticsCompanysResponse getLogisticsCompanysResponse = afterSalesRemoteService.getLogisticsCompanys(getLogisticsCompanysRequest);
            checkAndContinue(getLogisticsCompanysResponse);
            transfor(data, getLogisticsCompanysResponse);
        } catch (Exception ex) {
            return handleException(GetLogisticsCompanysResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }

    @ExplainAnnotation(explain = "获取指定第三方售后类型")
    @HTTP(alias = "getAfterSalesType")
    public Response<GetAfterSalesTypeResponseData> getAfterSalesType(GetAfterSalesTypeRequest request) {

        Response<GetAfterSalesTypeResponseData> response = new Response<GetAfterSalesTypeResponseData>();
        GetAfterSalesTypeResponseData data = new GetAfterSalesTypeResponseData();
        try {
            List<Dictionary> dictionaryList = DictionaryClient.findDictionaryByGroupNameOrderBySortAsc(Constant.Dict_K_V_Enum.DICT_AFTER_SALES_TYPE.getGroupName());
            List<AfterSalesTypeDto> list = Lists.newArrayList();
            for (Dictionary dictionary : dictionaryList) {
                AfterSalesTypeDto afterSalesTypeDto = new AfterSalesTypeDto();
                afterSalesTypeDto.setLabel(dictionary.getDictValue());
                afterSalesTypeDto.setValue(Integer.valueOf(dictionary.getDictKey()));
                list.add(afterSalesTypeDto);
            }
            data.setList(list);
        } catch (Exception ex) {
            return handleException(GetAfterSalesTypeResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "获取指定第三方售后原因")
    @HTTP(alias = "getAfterSalesReasons")
    public Response<GetAfterSalesReasonResponseData> getAfterSalesReasons(GetAfterSalesReasonRequest request) {

        Response<GetAfterSalesReasonResponseData> response = new Response<GetAfterSalesReasonResponseData>();
        GetAfterSalesReasonResponseData data = new GetAfterSalesReasonResponseData();
        try {
            List<Dictionary> dictionaryList = DictionaryClient.findDictionaryByGroupNameOrderBySortAsc(Constant.Dict_K_V_Enum.DICT_AFTER_SALES_REASON.getGroupName());
            List<AfterSalesReasonDto> list = Lists.newArrayList();
            for (Dictionary dictionary : dictionaryList) {
                AfterSalesReasonDto afterSalesReasonDto = new AfterSalesReasonDto();
                afterSalesReasonDto.setLabel(dictionary.getDictValue());
                afterSalesReasonDto.setValue(Integer.valueOf(dictionary.getDictKey()));
                list.add(afterSalesReasonDto);
            }
            data.setList(list);
        } catch (Exception ex) {
            return handleException(GetAfterSalesReasonResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "售后寄件")
    @HTTP(alias = "afterSalesLogistics", isRequireAuth = true, isNeadToken = true)
    public Response<AfterSalesLogisticsResponseData> afterSalesLogistics(AfterSalesLogisticsRequest request, UserToken userToken) {

        Response<AfterSalesLogisticsResponseData> response = new Response<AfterSalesLogisticsResponseData>();
        AfterSalesLogisticsResponseData data = new AfterSalesLogisticsResponseData();
        try {
            com.qding.legou.struct.request.AfterSalesLogisticsRequest afterSalesLogisticsRequest = transfor(com.qding.legou.struct.request.AfterSalesLogisticsRequest.class, request);
            afterSalesLogisticsRequest.setMemberId(userToken.getMemberId());
            AfterSalesLogisticsResponse afterSalesLogisticsResponse = afterSalesRemoteService.afterSalesLogistics(afterSalesLogisticsRequest);
            checkAndContinue(afterSalesLogisticsResponse);
        } catch (Exception ex) {
            return handleException(AfterSalesLogisticsResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }

    @ExplainAnnotation(explain = "售后寄件图片上传")
    @HTTP(alias = "uploadAfterSalesImg")
    public Response<UploadAfterSalesImgResponseData> uploadAfterSalesImg(HttpServletRequest request) {

        Response<UploadAfterSalesImgResponseData> response = new Response<UploadAfterSalesImgResponseData>();
        UploadAfterSalesImgResponseData data = new UploadAfterSalesImgResponseData();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> images = multipartRequest.getFiles("images");
        if(images.size()>0) {
            try {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "一次只能上传一张照片");
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        for (MultipartFile image : images) {
            try {
                if (image == null || StringUtils.isEmpty(image.getOriginalFilename())) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请选择上传图片");
                }

                String fileName = image.getOriginalFilename();
                String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();

                if (image.getContentType().indexOf("image") == -1) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "不是有效的图片");

                }
                if (!supportImageType.contains(ext)) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "不支持的图片格式");
                }

                File tempFile = null;
                try {
                    tempFile = File.createTempFile("upload-uploadImage", ext);
                    if (tempFile != null) {
                        UploadAfterSalesImgRequest uploadAfterSalesImgRequest = new UploadAfterSalesImgRequest();
                        uploadAfterSalesImgRequest.setImgFile(tempFile);
                        UploadAfterSalesImgResponse uploadAfterSalesImgResponse = afterSalesRemoteService.uploadAfterSalesImg(uploadAfterSalesImgRequest);
                        ReturnInfo returnInfo = uploadAfterSalesImgResponse.getReturnInfo();
                        if (HttpStatus.OK.getStatusCode() == returnInfo.getCode()) {
                            transfor(data, uploadAfterSalesImgResponse);
                        }
                        response.setData(data);
                        response.setCode(HttpStatus.OK.getStatusCode());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (tempFile != null) {
                        tempFile.delete();
                    }
                }

            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}
