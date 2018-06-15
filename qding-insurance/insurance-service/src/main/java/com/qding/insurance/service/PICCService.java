package com.qding.insurance.service;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.qding.insurance.picc.bean.Applicant;
import com.qding.insurance.picc.bean.GeneralInfoReturn;
import com.qding.insurance.picc.bean.Insured;
import com.qding.insurance.picc.bean.InsuredPlan;
import com.qding.insurance.picc.bean.InsuredReturn;
import com.qding.insurance.picc.bean.InsuredReturns;
import com.qding.insurance.picc.bean.Insureds;
import com.qding.insurance.picc.bean.PolicyInfo;
import com.qding.insurance.picc.bean.PolicyInfoReturn;
import com.qding.insurance.picc.bean.PolicyInfoReturns;
import com.qding.insurance.picc.bean.PolicyInfos;
import com.qding.insurance.picc.bean.ReturnInfo;
import com.qding.insurance.picc.dto.PICCInsureRequestParamDto;
import com.qding.insurance.picc.dto.PICCInsureResponseParamDto;
import com.qding.insurance.picc.enums.EnumInsureRespCodeType;
import com.qding.message.util.Md5Util;

@Service("piccService")
public class PICCService {
	private Logger logger = LoggerFactory.getLogger(PICCService.class);
	
//	@Value("#{configproperties_disconf[rationType_schemeAmount_relationship]}")
//    private String relationship;
//	
//	@Value("#{configproperties_disconf[WSDLURL]}")
//	private String WSDLURL;
//	
//	@Value("#{configproperties_disconf[INSURE_METHOD_NAME]}")
//	private String INSURE_METHOD_NAME;
//	
//	@Value("#{configproperties_disconf[INTERFACENO_INSURESERVICE]}")
//	private String INTERFACENO_INSURESERVICE;
//	
//	@Value("#{configproperties_disconf[MD5_SECRET_KEY]}")
//	private String MD5_SECRET_KEY;
//	
//	@Value("#{configproperties_disconf[PLATEFORMCODE]}")
//	private String PLATEFORMCODE;
//	
//	@Value("#{configproperties_disconf[RiskCode]}")
//	private String RiskCode;
//	
//	@Value("#{configproperties_disconf[ArguSolution]}")
//	private String ArguSolution;
//	
//	@Value("#{configproperties_disconf[Quantity]}")
//	private String Quantity;
//	
//	@Value("#{configproperties_disconf[SENDSMS]}")
//	private String SENDSMS;
	
	private String encodingStyle = "GB2312";
	
	private String WSDLURL = "http://partnertest.mypicc.com.cn/ecooperation/webservice/insure?wsdl";
	private final String INSURE_METHOD_NAME = "insureService";	//投保的方法名为insureService
	private final String INTERFACENO_INSURESERVICE = "001001";	//投保的interfaceNo为001001
	private final String MD5_SECRET_KEY = "Picc37mu63ht38mw";	//测试环境调用PICC密钥
	private final String PLATEFORMCODE = "CPI000446";
	private final String RiskCode = "JEB";	//险种代码
	private final String ArguSolution = "1";	//争议决绝方式，1-诉讼
	private final String Quantity = "1";	//投保份数
	private final String SENDSMS = "N";	//是否发送短信
	
	/**
	 * 投保
	 * @param param
	 * @return
	 */
	public PICCInsureResponseParamDto insureService(PICCInsureRequestParamDto dto) {
		PICCInsureResponseParamDto respDto = new PICCInsureResponseParamDto();
		//拼装参数
		String param = assembleParams(dto);
		
		String result = ""; //请求接口返回的结果
		
		//最大重试次数
		int maxRetryTime = 3;
		//重试次数
		int time = 0;
		
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		
		Client client = factory.createClient(WSDLURL);
		try {
			// 给方法传递参数，并且调用方法
			while (StringUtils.isEmpty(result) && time < maxRetryTime) {
				if (time != 0) {
					Thread.sleep(10000);
				}
				Object[] res = client.invoke(INSURE_METHOD_NAME, new Object[] { INTERFACENO_INSURESERVICE, param });
				result = res[0].toString();
				logger.info("result:" + result);
				time++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!StringUtils.isEmpty(result)) {
			respDto = parseInsureStringXml(respDto, result);
		}else {
			respDto.setRespCode(EnumInsureRespCodeType.FAIL_NULL_RESULT.getCode());
			respDto.setRespMsg(EnumInsureRespCodeType.FAIL_NULL_RESULT.getDescription());
		}
		
		return respDto;
	}
	
	//拼装xml参数
	private String assembleParams(PICCInsureRequestParamDto dto) {
		Document document = null;
		Element root = DocumentHelper.createElement("ApplyInfo");
		document = DocumentHelper.createDocument(root);
		document.setXMLEncoding(encodingStyle);
		
		//设置通用信息
		Element generalInfoEle = root.addElement("GeneralInfo");
		Element uUIDEle = generalInfoEle.addElement("UUID");
		String uUID = "QDHL" + System.currentTimeMillis();
		uUIDEle.setText(uUID);
		generalInfoEle.addElement("PlateformCode").setText(PLATEFORMCODE);
		
		//StringBuilder保存UUID+所有SumAmount节点的值+密钥，以便进行MD5加密
		StringBuilder sb = new StringBuilder();
		sb.append(uUID);
		
		//设置投保信息
		PolicyInfos policyInfos = dto.getPolicyInfos();
		if (policyInfos != null) {
			Element policyInfosEle = root.addElement("PolicyInfos");
			List<PolicyInfo> policyInfoList = policyInfos.getPolicyInfoList();
			if (policyInfoList != null && policyInfoList.size() > 0) {
				for (int i = 0; i < policyInfoList.size(); i++) {
					PolicyInfo policyInfo = policyInfoList.get(i);
					Element policyInfoEle = policyInfosEle.addElement("PolicyInfo");
					policyInfoEle.addElement("SerialNo").setText(String.valueOf(i + 1));
					policyInfoEle.addElement("RiskCode").setText(RiskCode);
					policyInfoEle.addElement("OperateTimes").setText(policyInfo.getOperateTimes());
					policyInfoEle.addElement("StartDate").setText(policyInfo.getStartDate());
					policyInfoEle.addElement("EndDate").setText(policyInfo.getEndDate());
					policyInfoEle.addElement("StartHour").setText(policyInfo.getStartHour());
					policyInfoEle.addElement("EndHour").setText(policyInfo.getEndHour());
					policyInfoEle.addElement("SumPremium").setText(policyInfo.getSumPremium());
					policyInfoEle.addElement("ArguSolution").setText(ArguSolution);
					policyInfoEle.addElement("HouseAddress").setText(policyInfo.getHouseAddress());
					policyInfoEle.addElement("Quantity").setText(Quantity);
					
					//拼接StringBuilder字符串
					sb.append(policyInfo.getSumPremium());
					
					//设置投保方案
					InsuredPlan insuredPlan = policyInfo.getInsuredPlan();
					if (insuredPlan != null) {
						Element insuredPlanEle = policyInfoEle.addElement("InsuredPlan");
						insuredPlanEle.addElement("RationType").setText(insuredPlan.getRationType());
						
						//根据不同的方案代码设置保单信息中的保额
//						JSONObject jo = JSONObject.parseObject(relationship);
//						if (jo != null) {
//							Set<String> keySet = jo.keySet();
//							for (String key : keySet) {
//								if (key.equals(insuredPlan.getRationType())) {
//									policyInfoEle.addElement("SumAmount").setText(jo.getString(key));
//									logger.info("relationship:" + relationship + ", key = " + key);
//								}
//							}
//						}
						policyInfoEle.addElement("SumAmount").setText("811200");
					}
					
					//设置投保人信息
					Applicant applicant = policyInfo.getApplicant();
					if (applicant != null) {
						Element applicantEle = policyInfoEle.addElement("Applicant");
						applicantEle.addElement("AppliName").setText(applicant.getAppliName());
						applicantEle.addElement("AppliIdType").setText(applicant.getAppliIdType());
						applicantEle.addElement("AppliIdNo").setText(applicant.getAppliIdNo());
						applicantEle.addElement("AppliIdMobile").setText(applicant.getAppliIdMobile());
						applicantEle.addElement("AppliIdEmail").setText(applicant.getAppliIdEmail());
						applicantEle.addElement("AppliIdentity").setText(applicant.getAppliIdentity());
					}
					
					//设置被投保人信息
					Insureds insureds = policyInfo.getInsureds();
					if (insureds != null) {
						Element insuredsEle = policyInfoEle.addElement("Insureds");
						List<Insured> insuredList = insureds.getInsuredList();
						if (insuredList != null && insuredList.size() > 0) {
							for (int j = 0; j < insuredList.size(); j++) {
								Element insuredEle = insuredsEle.addElement("Insured");
								Insured insured = insuredList.get(j);
								insuredEle.addElement("InsuredSeqNo").setText(String.valueOf(j + 1));
								insuredEle.addElement("InsuredName").setText(insured.getInsuredName());
								insuredEle.addElement("InsuredIdType").setText(insured.getInsuredIdType());
								insuredEle.addElement("InsuredIdNo").setText(insured.getInsuredIdNo());
								insuredEle.addElement("InsuredEmail").setText(insured.getInsuredEmail());
								insuredEle.addElement("SendSMS").setText(SENDSMS);
							}
						}
					}
					
					//设置扩展信息，AppliContent：投保人财产地址个数；InsuredContent：被保险人财产地址个数
					Element ExtendInfosEle = policyInfoEle.addElement("ExtendInfos");
					ExtendInfosEle.addElement("ExtendInfo").addAttribute("key", "AppliContent").setText("1");
					ExtendInfosEle.addElement("ExtendInfo").addAttribute("key", "InsuredContent").setText("1");
				}
			}
		}
		
		//设置通用信息中Md5Value节点的值
		String md5Value = null;
		try {
			sb.append(MD5_SECRET_KEY);
			md5Value = Md5Util.md5(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		generalInfoEle.addElement("Md5Value").setText(md5Value);
		
		//document转xml字符串
		String result = document.asXML();
		logger.info(result);
		return result;
	}
	
	/**
	 * 解析xml字符串，并拼装返回dto对象
	 * @param result
	 * @return
	 */
	private PICCInsureResponseParamDto parseInsureStringXml(PICCInsureResponseParamDto respDto, String result) {
		ReturnInfo rie = new ReturnInfo();;
		GeneralInfoReturn gire = null;
		PolicyInfoReturns pirse = null;
		PolicyInfoReturn pire = null;
		InsuredReturns irse = null;
		InsuredReturn ire = null;
		try {
			//将字符串转为xml
			Document parseText = DocumentHelper.parseText(result);
			
			//获取根节点
			Element rootElement = parseText.getRootElement();
			
			if (rootElement != null) {
				//获取根节点下的子节点GeneralInfoReturn
				Element generalInfoEle = rootElement.element("GeneralInfoReturn");
				
				//获取GeneralInfoReturn节点下的节点
				if (generalInfoEle != null) {
					String uuid = generalInfoEle.elementTextTrim("UUID");
					String plateformCode = generalInfoEle.elementTextTrim("PlateformCode");
					String errorCode = generalInfoEle.elementTextTrim("ErrorCode");
					String errorMessage = generalInfoEle.elementTextTrim("ErrorMessage");
					
					//报文通用属性<ErrorCode>节点的值为"00"是保单出单成功的必要前提，如果不是"00"直接return
					if (!"00".equals(errorCode)) {
						respDto.setRespCode(EnumInsureRespCodeType.FAIL_WRONG_ERRORCODE.getCode());
						respDto.setRespMsg("保单出单失败，失败原因是:" + EnumInsureRespCodeType.FAIL_WRONG_ERRORCODE.getDescription());
						return respDto;
					}
					
					//拼装GeneralInfoReturn
					gire = new GeneralInfoReturn();
					gire.setUuid(uuid);
					gire.setPlateformCode(plateformCode);
					gire.setErrorCode(errorCode);
					gire.setErrorMessage(errorMessage);
					
					//设置ReturnInfo中GeneralInfoReturn的值
					rie.setGeneralInfoReturn(gire);
				}
				
				//获取根节点下的子节点PolicyInfoReturns
				Element policyInfoEle = rootElement.element("PolicyInfoReturns");
				if (policyInfoEle != null) {
					ArrayList<PolicyInfoReturn> pireList = new ArrayList<PolicyInfoReturn>();
					
					//获取PolicyInfoReturns节点下的PolicyInfoReturn节点
					Iterator policyInfoReturnIterator = policyInfoEle.elementIterator("PolicyInfoReturn");
					
					while (policyInfoReturnIterator.hasNext()) {
						Element policyInfoReturnEle = (Element) policyInfoReturnIterator
								.next();
						if (policyInfoReturnEle != null) {
							//获取PolicyInfoReturn节点下的节点
							String serialNo = policyInfoReturnEle.elementTextTrim("SerialNo");
							String policyNo = policyInfoReturnEle.elementTextTrim("PolicyNo");
							String policyUrl = policyInfoReturnEle.elementTextTrim("PolicyUrl");
							String downloadUrl = policyInfoReturnEle.elementTextTrim("DownloadUrl");
							String saveResult = policyInfoReturnEle.elementTextTrim("SaveResult");
							String saveMessage = policyInfoReturnEle.elementTextTrim("SaveMessage");
							String saveTimes = policyInfoReturnEle.elementTextTrim("SaveTimes");
							
							//投保单对应的<PolicyNo>节点值非空是保单出单成功的必要前提，如果为空直接return
							if (policyNo == null || policyNo.equals("")) {
								respDto.setRespCode(EnumInsureRespCodeType.FAIL_WITHOUT_POLICYNO.getCode());
								respDto.setRespMsg("保单出单失败，失败原因是:" + EnumInsureRespCodeType.FAIL_WITHOUT_POLICYNO.getDescription());
								return respDto;
							}
							
							//投保单对应的<SaveResult>节点值为"00"是保单出单成功的必要前提，如果不为"00"直接return
							if (!saveResult.equals("00")) {
								respDto.setRespCode(EnumInsureRespCodeType.FAIL_WRONG_SAVERESULT.getCode());
								respDto.setRespMsg("保单出单失败，失败原因是:" + EnumInsureRespCodeType.FAIL_WRONG_SAVERESULT.getDescription());
								return respDto;
							}
							
							//获取PolicyInfoReturns节点下的InsuredReturns节点
							Element insuredReturnsEle = policyInfoEle.element("InsuredReturns");
							if (insuredReturnsEle != null) {
								ArrayList<InsuredReturn> ireList = new ArrayList<InsuredReturn>();
								
								//获取InsuredReturns节点下的InsuredReturn节点
								Iterator insuredReturnIterator = policyInfoEle.elementIterator("InsuredReturn");
								while (insuredReturnIterator.hasNext()) {
									Element insuredReturnEle = (Element) insuredReturnIterator.next();
									
									//获取InsuredReturn节点下的节点
									String insuredSeqNo = insuredReturnEle.elementTextTrim("InsuredSeqNo");
									String checkResult = insuredReturnEle.elementTextTrim("CheckResult");
									String checkMessage = insuredReturnEle.elementTextTrim("CheckMessage");
									
									//拼装InsuredReturn
									ire = new InsuredReturn();
									ire.setInsuredSeqNo(insuredSeqNo);
									ire.setCheckResult(checkResult);
									ire.setCheckMessage(checkMessage);
									
									//将InsuredReturn放到集合中
									ireList.add(ire);
								}
								
								//拼装InsuredReturns
								irse = new InsuredReturns();
								irse.setIreList(ireList);
							}
							
							//拼装PolicyInfoReturn
							pire = new PolicyInfoReturn();
							pire.setSerialNo(serialNo);
							pire.setPolicyNo(policyNo);
							pire.setPolicyUrl(policyUrl);
							pire.setDownloadUrl(downloadUrl);
							pire.setSaveResult(saveResult);;
							pire.setSaveMessage(saveMessage);
							pire.setSaveTimes(saveTimes);
							pire.setInsuredReturns(irse);
							
							//将PolicyInfoReturn放到集合中
							pireList.add(pire);
						}
					}
					
					//拼装PolicyInfoReturns
					pirse = new PolicyInfoReturns();
					pirse.setPireList(pireList);
				}
				
				//设置ReturnInfo中PolicyInfoReturns的值
				rie.setPolicyInfoReturns(pirse);
			}
			
			//判断投某个保单是否出单成功的规则：报文通用属性<ErrorCode>节点的值为“00”，且该投保单对应的<PolicyNo>节点值非空，<SaveResult>节点值为“00”，至此应该返回成功值
			respDto.setReturnInfo(rie);
			respDto.setRespCode(EnumInsureRespCodeType.SUCCESS.getCode());
			respDto.setRespMsg(EnumInsureRespCodeType.SUCCESS.getDescription());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return respDto;
	}

	public static void main(String[] args) {
		//拼装参数
		PICCInsureRequestParamDto dto = new PICCInsureRequestParamDto();
		PolicyInfos policyInfos = new PolicyInfos();
		ArrayList<PolicyInfo> policyInfoList = new ArrayList<PolicyInfo>();
		//设置保单信息
		PolicyInfo policyInfo = new PolicyInfo();
		policyInfo.setOperateTimes("2018-06-12 18:00:00");
		policyInfo.setStartDate("2018-07-01");
		policyInfo.setEndDate("2019-07-01");
		policyInfo.setStartHour("0");
		policyInfo.setEndHour("24");
		policyInfo.setSumPremium("387");
		policyInfo.setHouseAddress("北京市复兴门内大街1号");
		//设置投保方案
		InsuredPlan insuredPlan = new InsuredPlan();
		insuredPlan.setRationType("JEB110000c");
		policyInfo.setInsuredPlan(insuredPlan);
		//设置投保人信息
		Applicant applicant = new Applicant();
		applicant.setAppliName("小猪佩奇");
		applicant.setAppliIdType("01");
		applicant.setAppliIdNo("232126199110234014");
		applicant.setAppliIdMobile("18811680213");
		applicant.setAppliIdEmail("827377237@qq.com");
		applicant.setAppliIdentity("01");
		policyInfo.setApplicant(applicant);
		//设置被投保人信息
		Insureds insureds = new Insureds();
		ArrayList<Insured> insuredList = new ArrayList<Insured>();
		Insured insured = new Insured();
		insured.setInsuredName("小猪佩奇");
		insured.setInsuredIdType("01");
		insured.setInsuredIdNo("232126199110234014");
		insured.setInsuredEmail("827377237@qq.com");
		insuredList.add(insured);
		insureds.setInsuredList(insuredList);
		policyInfo.setInsureds(insureds);
		policyInfoList.add(policyInfo);
		policyInfos.setPolicyInfoList(policyInfoList);
		dto.setPolicyInfos(policyInfos);
		//调用投保接口
		PICCService piccService = new PICCService();
		PICCInsureResponseParamDto result = piccService.insureService(dto);
		String respCode = null;
		String respMsg = null;
		String piccNo = null;
		String piccUrl = null;
		if (result == null) {
			System.out.println("result from PICC is null");
		}else {
			respCode = result.getRespCode();
			respMsg = result.getRespMsg();
			System.out.println("调用人保投保接口返回的respCode:" + respCode + ", respMsg:" + respMsg);
			if (EnumInsureRespCodeType.SUCCESS.getCode().equals(respCode)) {
				ReturnInfo returnInfo = result.getReturnInfo();
				if (returnInfo != null) {
					PolicyInfoReturns policyInfoReturns = returnInfo.getPolicyInfoReturns();
					if (policyInfoReturns != null) {
						List<PolicyInfoReturn> pireList = policyInfoReturns.getPireList();
						if (CollectionUtils.isNotEmpty(pireList)) {
							piccNo = pireList.get(0).getPolicyNo();
							piccUrl = pireList.get(0).getDownloadUrl();
							System.out.println("piccNo:" + piccNo);
							System.out.println("piccUrl:" + piccUrl);
						}
					}
				}
			}
		}
	}

}
