import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qding.insurance.picc.bean.Applicant;
import com.qding.insurance.picc.bean.Insured;
import com.qding.insurance.picc.bean.InsuredPlan;
import com.qding.insurance.picc.bean.Insureds;
import com.qding.insurance.picc.bean.PolicyInfo;
import com.qding.insurance.picc.bean.PolicyInfoReturn;
import com.qding.insurance.picc.bean.PolicyInfoReturns;
import com.qding.insurance.picc.bean.PolicyInfos;
import com.qding.insurance.picc.bean.ReturnInfo;
import com.qding.insurance.picc.dto.PICCInsureRequestParamDto;
import com.qding.insurance.picc.dto.PICCInsureResponseParamDto;
import com.qding.insurance.picc.enums.EnumInsureRespCodeType;
import com.qding.insurance.service.PICCService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-service.xml" })
public class TestPICCService {

	@Resource
	private PICCService piccService;
	
	@Test
	public void insureTest() {
		// 拼装参数
		PICCInsureRequestParamDto dto = new PICCInsureRequestParamDto();
		PolicyInfos policyInfos = new PolicyInfos();
		ArrayList<PolicyInfo> policyInfoList = new ArrayList<PolicyInfo>();
		// 设置保单信息
		PolicyInfo policyInfo = new PolicyInfo();
		policyInfo.setOperateTimes("2018-06-12 18:00:00");
		policyInfo.setStartDate("2018-07-01");
		policyInfo.setEndDate("2019-07-01");
		policyInfo.setStartHour("0");
		policyInfo.setEndHour("24");
		policyInfo.setSumPremium("387");
		policyInfo.setHouseAddress("北京市复兴门内大街1号");
		// 设置投保方案
		InsuredPlan insuredPlan = new InsuredPlan();
		insuredPlan.setRationType("JEB110000c");
		policyInfo.setInsuredPlan(insuredPlan);
		// 设置投保人信息
		Applicant applicant = new Applicant();
		applicant.setAppliName("小猪佩奇");
		applicant.setAppliIdType("01");
		applicant.setAppliIdNo("232126199110234014");
		applicant.setAppliIdMobile("18811680213");
		applicant.setAppliIdEmail("827377237@qq.com");
		applicant.setAppliIdentity("01");
		policyInfo.setApplicant(applicant);
		// 设置被投保人信息
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
		
		// 调用投保接口
//		PICCService piccService = new PICCService();
		PICCInsureResponseParamDto result = piccService.insureService(dto);
		
		String respCode = null;
		String respMsg = null;
		String piccNo = null;
		String piccUrl = null;
		if (result == null) {
			System.out.println("result from PICC is null");
		} else {
			respCode = result.getRespCode();
			respMsg = result.getRespMsg();
			System.out.println("调用人保投保接口返回的respCode:" + respCode + ", respMsg:"
					+ respMsg);
			if (EnumInsureRespCodeType.SUCCESS.getCode().equals(respCode)) {
				ReturnInfo returnInfo = result.getReturnInfo();
				if (returnInfo != null) {
					PolicyInfoReturns policyInfoReturns = returnInfo
							.getPolicyInfoReturns();
					if (policyInfoReturns != null) {
						List<PolicyInfoReturn> pireList = policyInfoReturns
								.getPireList();
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
