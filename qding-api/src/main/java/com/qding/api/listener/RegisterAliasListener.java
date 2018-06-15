package com.qding.api.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.qding.api.call.app.qding.v3_0_1.CallGroupon;
import com.qding.api.call.app.qding.v3_1_0.CallEntranceCard;
import com.qding.api.call.common.CallActivitySms;
import com.qding.api.call.common.CallCommonDataAnalysis;
import com.qding.api.call.common.CallCommonUpload;
import com.qding.api.process.pool.ProtocolPool;
import com.qding.api.process.pool.ServicePool;
import com.qding.api.process.print.JsonProtocolPrint;
import com.qding.api.process.print.XmlProtocolPrint;
import com.qding.api.smart.validate.match.MatchAccountValidate;
import com.qding.api.smart.validate.match.MatchFixLengthValidate;
import com.qding.api.smart.validate.match.MatchFullLengthValidate;
import com.qding.api.smart.validate.match.MatchMemberValidate;
import com.qding.api.smart.validate.match.MatchProjectValidate;
import com.qding.api.smart.validate.match.MatchRoomValidate;
import com.qding.api.smart.validate.match.MatchWalletPayPasswordRuleValidate;
import com.qding.api.smart.validate.match.MatchWalletStatusValidate;
import com.qding.api.smart.validate.rule.AccountValidate;
import com.qding.api.smart.validate.rule.FixLengthValidate;
import com.qding.api.smart.validate.rule.FullLengthValidate;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.ProjectValidate;
import com.qding.api.smart.validate.rule.RoomValidate;
import com.qding.api.smart.validate.rule.WalletPayPasswordRuleValidate;
import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.smart.validate.ValidateRulePool;

/**
 * @author lichao
 */
public class RegisterAliasListener extends ContextLoaderListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.registerSupportService();
        this.registerSupportProtocol();
        this.registerExtendedValidate();
    }

    private void registerSupportService() {
        //挂载接口访问
        // #### common ####

        //common-upload
        ServicePool.mount("common-upload", "1.0.0", CallCommonUpload.class);
        //common-data-analysis
        ServicePool.mount("common-data-analysis", "1.0.0", CallCommonDataAnalysis.class);
        ServicePool.mount("common-activity-sms", "1.0.0", CallActivitySms.class);


        //             APP (sub version struct)

        //1.2.0
        ServicePool.mount("hotcycle", "1.2.0", com.qding.api.call.app.qding.v1_2_0.CallHotcycle.class);

        //1.2.1
        ServicePool.mount("hotcycle", "1.2.1", com.qding.api.call.app.qding.v1_2_1.CallHotcycle.class);
        ServicePool.mount("housekeeper", "1.2.1", com.qding.api.call.app.qding.v1_2_1.CallHouseKeeper.class);

        //1.2.2
        ServicePool.mount("hotcycle", "1.2.2", com.qding.api.call.app.qding.v1_2_2.CallHotcycle.class);

        //1.3.0
        ServicePool.mount("housekeeper", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallHouseKeeper.class);
        ServicePool.mount("brick", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallBrick.class);
        ServicePool.mount("legou-goods", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallLegouGoods.class);
        ServicePool.mount("legou-order", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallLegouOrder.class);
        ServicePool.mount("user", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallUser.class);
        ServicePool.mount("coupon", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallCoupon.class);
        ServicePool.mount("hotcycle", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallHotcycle.class);
        ServicePool.mount("payment", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallPayment.class);
        ServicePool.mount("wallet", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallWallet.class);
        ServicePool.mount("notify", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallNotify.class);
        ServicePool.mount("history-order", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallHistoryOrder.class);
        ServicePool.mount("activity", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallActivity.class);
        ServicePool.mount("platformOrder", "1.3.0", com.qding.api.call.app.qding.v1_3_0.CallPlateFormOrder.class);

        //1.3.1
        ServicePool.mount("project", "1.3.1", com.qding.api.call.app.qding.v1_3_1.CallProject.class);
        ServicePool.mount("wallet", "1.3.1", com.qding.api.call.app.qding.v1_3_1.CallWallet.class);
        ServicePool.mount("payment", "1.3.1", com.qding.api.call.app.qding.v1_3_1.CallPayment.class);
        ServicePool.mount("brick", "1.3.1", com.qding.api.call.app.qding.v1_3_1.CallBrick.class);

        //1.3.2
        ServicePool.mount("hotcycle", "1.3.2", com.qding.api.call.app.qding.v1_3_2.CallHotcycle.class);
        ServicePool.mount("im", "1.3.2", com.qding.api.call.app.qding.v1_3_2.CallIM.class);
        ServicePool.mount("popularize", "1.3.2", com.qding.api.call.app.qding.v1_3_2.CallPopularize.class);
        ServicePool.mount("poster", "1.3.2", com.qding.api.call.app.qding.v1_3_2.CallPoster.class);
        ServicePool.mount("user", "1.3.2", com.qding.api.call.app.qding.v1_3_2.CallUser.class);
        ServicePool.mount("project", "1.3.2", com.qding.api.call.app.qding.v1_3_2.CallProject.class);
        ServicePool.mount("points", "1.3.2", com.qding.api.call.app.qding.v1_3_2.CallPoints.class);

        //1.4.0
        ServicePool.mount("auction", "1.4.0", com.qding.api.call.app.qding.v1_4_0.CallAuction.class);
        ServicePool.mount("coupon", "1.4.0", com.qding.api.call.app.qding.v1_4_0.CallCoupon.class);
        ServicePool.mount("wallet", "1.4.0", com.qding.api.call.app.qding.v1_4_0.CallWallet.class);
        ServicePool.mount("legou-order", "1.4.0", com.qding.api.call.app.qding.v1_4_0.CallLegouOrder.class);
        ServicePool.mount("housekeeper", "1.4.0", com.qding.api.call.app.qding.v1_4_0.CallHouseKeeper.class);
        ServicePool.mount("popularize", "1.4.0", com.qding.api.call.app.qding.v1_4_0.CallPopularize.class);

        //1.4.1
        ServicePool.mount("hotcycle", "1.4.1", com.qding.api.call.app.qding.v1_4_1.CallHotcycle.class);
        ServicePool.mount("notify", "1.4.1", com.qding.api.call.app.qding.v1_4_1.CallNotify.class);
        ServicePool.mount("housekeeper", "1.4.1", com.qding.api.call.app.qding.v1_4_1.CallHouseKeeper.class);
        ServicePool.mount("project", "1.4.1", com.qding.api.call.app.qding.v1_4_1.CallProject.class);
        ServicePool.mount("legou-goods", "1.4.1", com.qding.api.call.app.qding.v1_4_1.CallLegouGoods.class);

        //2.0.0
        ServicePool.mount("project", "2.0.0", com.qding.api.call.app.qding.v2_0_0.CallProject.class);
        ServicePool.mount("housekeeper", "2.0.0", com.qding.api.call.app.qding.v2_0_0.CallHouseKeeper.class);
        ServicePool.mount("familypayment", "2.0.0", com.qding.api.call.app.qding.v2_0_0.CallFamilyPayment.class);
		ServicePool.mount("legou-goods", "2.0.0",com.qding.api.call.app.qding.v2_0_0.CallLegouGoods.class);
		ServicePool.mount("legou-order", "2.0.0",com.qding.api.call.app.qding.v2_0_0.CallLegouOrder.class);
        ServicePool.mount("payment", "2.0.0", com.qding.api.call.app.qding.v2_0_0.CallPayment.class);
        ServicePool.mount("brick", "2.0.0", com.qding.api.call.app.qding.v2_0_0.CallBrick.class);
        ServicePool.mount("platformOrder","2.0.0", com.qding.api.call.app.qding.v2_0_0.CallPlateFormOrder.class);

        //2.1.0
        ServicePool.mount("housekeeper", "2.1.0", com.qding.api.call.app.qding.v2_1_0.CallHouseKeeper.class);
        ServicePool.mount("memberTask", "2.1.0", com.qding.api.call.app.qding.v2_1_0.CallMemberTask.class);
        ServicePool.mount("hotcycle", "2.1.0", com.qding.api.call.app.qding.v2_1_0.CallHotcycle.class);
        ServicePool.mount("sysconfig", "2.1.0", com.qding.api.call.app.qding.v2_1_0.CallSysConfig.class);


        //2.3.0
        ServicePool.mount("watch", "2.3.0", com.qding.api.call.app.qding.v2_3_0.CallWatch.class);
        ServicePool.mount("legou-goods", "2.3.0", com.qding.api.call.app.qding.v2_3_0.CallLegouGoods.class);
        ServicePool.mount("legou-order", "2.3.0", com.qding.api.call.app.qding.v2_3_0.CallLegouOrder.class);
        ServicePool.mount("hotcycle", "2.3.0", com.qding.api.call.app.qding.v2_3_0.CallHotcycle.class);
        ServicePool.mount("payment", "2.3.0", com.qding.api.call.app.qding.v2_3_0.CallPayment.class);
        ServicePool.mount("brick", "2.3.0", com.qding.api.call.app.qding.v2_3_0.CallBrick.class);

        //2.4.0
        ServicePool.mount("housekeeper", "2.4.0", com.qding.api.call.app.qding.v2_4_0.CallHouseKeeper.class);
        ServicePool.mount("legou-goods", "2.4.0", com.qding.api.call.app.qding.v2_4_0.CallLegouGoods.class);
        ServicePool.mount("legou-order", "2.4.0", com.qding.api.call.app.qding.v2_4_0.CallLegouOrder.class);
        ServicePool.mount("hotcycle", "2.4.0", com.qding.api.call.app.qding.v2_4_0.CallHotcycle.class);
        ServicePool.mount("sysconfig", "2.4.0", com.qding.api.call.app.qding.v2_4_0.CallSysConfig.class);

        ServicePool.mount("memberTask", "2.4.1", com.qding.api.call.app.qding.v2_4_1.CallMemberTask.class);

        //2.5.0
        ServicePool.mount("housekeeper", "2.5.0", com.qding.api.call.app.qding.v2_5_0.CallHouseKeeper.class);
        ServicePool.mount("brick", "2.5.0", com.qding.api.call.app.qding.v2_5_0.CallBrick.class);
        ServicePool.mount("user", "2.5.0", com.qding.api.call.app.qding.v2_5_0.CallUser.class);
        ServicePool.mount("legou-order", "2.5.0", com.qding.api.call.app.qding.v2_5_0.CallLegouOrder.class);
        ServicePool.mount("legou-goods", "2.5.0", com.qding.api.call.app.qding.v2_5_0.CallLegouGoods.class);

        //2.6.0
        ServicePool.mount("neighbor", "2.6.0", com.qding.api.call.app.qding.v2_6_0.CallNeighbor.class);

        //2.7.0
        ServicePool.mount("legou-goods", "2.7.0", com.qding.api.call.app.qding.v2_7_0.CallLegouGoods.class);

        //2.8.0
        ServicePool.mount("neighbor", "2.8.0", com.qding.api.call.app.qding.v2_8_0.CallNeighbor.class);
        ServicePool.mount("project", "2.8.0", com.qding.api.call.app.qding.v2_8_0.CallProject.class);
        ServicePool.mount("user", "2.8.0", com.qding.api.call.app.qding.v2_8_0.CallUser.class);
        ServicePool.mount("brick", "2.8.0", com.qding.api.call.app.qding.v2_8_0.CallBrick.class);
        ServicePool.mount("legou-order", "2.8.0", com.qding.api.call.app.qding.v2_8_0.CallLegouOrder.class);

        //3.0.0
        ServicePool.mount("neighbor", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallNeighbor.class);
        ServicePool.mount("project", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallProject.class);
        ServicePool.mount("brick", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallBrick.class);
        ServicePool.mount("legou-goods", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallLegouGoods.class);
        ServicePool.mount("user", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallUser.class);
        ServicePool.mount("housekeeper", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallHouseKeeper.class);
        ServicePool.mount("memberTask", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallMemberTask.class);
        ServicePool.mount("popularize", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallPopularize.class);
        ServicePool.mount("legou-order", "3.0.0", com.qding.api.call.app.qding.v3_0_0.CallLegouOrder.class);

        //3.0.1
        ServicePool.mount("groupon", "3.0.1", CallGroupon.class);
        
        //3.1.0
        ServicePool.mount("entrance-card", "3.1.0", CallEntranceCard.class);
        ServicePool.mount("neighbor", "3.1.0", com.qding.api.call.app.qding.v3_1_0.CallNeighbor.class);
        ServicePool.mount("user", "3.1.0", com.qding.api.call.app.qding.v3_1_0.CallUser.class);
        ServicePool.mount("legou-order", "3.1.0", com.qding.api.call.app.qding.v3_1_0.CallLegouOrder.class);

        //3.1.1
        ServicePool.mount("legou-order", "3.1.1", com.qding.api.call.app.qding.v3_1_1.CallLegouOrder.class);
        
        //3.2.0
        ServicePool.mount("user", "3.2.0", com.qding.api.call.app.qding.v3_2_0.CallUser.class);
        ServicePool.mount("project", "3.2.0", com.qding.api.call.app.qding.v3_2_0.CallProject.class);
        ServicePool.mount("coupon", "3.2.0", com.qding.api.call.app.qding.v3_2_0.CallCoupon.class);
        ServicePool.mount("memberTask", "3.2.0", com.qding.api.call.app.qding.v3_2_0.CallMemberTask.class);
        ServicePool.mount("legou-order", "3.2.0", com.qding.api.call.app.qding.v3_2_0.CallLegouOrder.class);

        ServicePool.mount("search", "3.3.0", com.qding.api.call.app.qding.v3_3_0.CallSearchItems.class);
        ServicePool.mount("user", "3.3.0", com.qding.api.call.app.qding.v3_3_0.CallUser.class);
        ServicePool.mount("legou-order", "3.3.0", com.qding.api.call.app.qding.v3_3_0.CallLegouOrder.class);
        ServicePool.mount("legou-goods", "3.3.0", com.qding.api.call.app.qding.v3_3_0.CallLegouGoods.class);
        ServicePool.mount("neighbor", "3.3.0", com.qding.api.call.app.qding.v3_3_0.CallNeighbor.class);
        ServicePool.mount("realestate", "3.3.0", com.qding.api.call.app.qding.v3_3_0.CallRealestate.class);
        ServicePool.mount("newsell", "3.3.0", com.qding.api.call.app.qding.v3_3_0.CallNewSell.class);
        
        //4.0.0
        ServicePool.mount("chatRecord", "4.0.0", com.qding.api.call.app.qding.v4_0_0.CallChatRecord.class);
        ServicePool.mount("project", "4.0.0", com.qding.api.call.app.qding.v4_0_0.CallProject.class);
        ServicePool.mount("shareCoupon", "4.0.0", com.qding.api.call.app.qding.v4_0_0.CallShareCoupon.class);
        ServicePool.mount("stage", "4.0.0", com.qding.api.call.app.qding.v4_0_0.CallStage.class);
        
        //4.1.0
        ServicePool.mount("sysconfig", "4.1.0", com.qding.api.call.app.qding.v4_1_0.CallSysConfig.class);
        ServicePool.mount("insurance", "4.1.0", com.qding.api.call.app.qding.v4_1_0.CallInsurance.class);
        //挂载方法
        ServicePool.mountExecutorMethod();

    }

    private void registerExtendedValidate() {

        ValidateRulePool.mount(MemberValidate.class, new MatchMemberValidate());
        ValidateRulePool.mount(AccountValidate.class, new MatchAccountValidate());
        ValidateRulePool.mount(RoomValidate.class, new MatchRoomValidate());
        ValidateRulePool.mount(ProjectValidate.class, new MatchProjectValidate());
        ValidateRulePool.mount(WalletPayPasswordRuleValidate.class, new MatchWalletPayPasswordRuleValidate());
        ValidateRulePool.mount(WalletStatusValidate.class, new MatchWalletStatusValidate());
        ValidateRulePool.mount(FullLengthValidate.class, new MatchFullLengthValidate());
        ValidateRulePool.mount(FixLengthValidate.class, new MatchFixLengthValidate());


    }

    private void registerSupportProtocol() {
        ProtocolPool.mount("json", JsonProtocolPrint.class);
        ProtocolPool.mount("xml", XmlProtocolPrint.class);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
