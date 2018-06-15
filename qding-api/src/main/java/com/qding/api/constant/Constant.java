package com.qding.api.constant;

import com.google.common.collect.Lists;
import com.qding.api.util.APIPropertiesClient;
import com.qding.framework.common.api.struct.AppDevice;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.uuid.UUIDGenerator;
import com.qding.member.common.constant.MemberRole;
import com.qding.message.constant.MsgConstant;

import java.util.*;

public class Constant {

    //主键生成策略
    public static final UUIDGenerator API_UUID = new UUIDGenerator("api");
    public static final UUIDGenerator roomBindApplyUg = new UUIDGenerator("roomBindApplyUg");

    // 新手礼包类型
    public static final int GIFT_TYPE_SKU = 1;
    public static final int GIFT_TYPE_QUAN_NEW = 2;
    public static final int GIFT_TYPE_QUAN_OLD = 3;
    
    // 新手礼包领取类型
    public static final int GIFT_CANUSE_NO = 0;
    public static final int GIFT_CANUSE_YES = 1;
    public static final int GIFT_CANUSE_USED_CUR_PROJECT = 2;
    public static final int GIFT_CANUSE_NOBINGROOM = 3;
    public static final int GIFT_CANUSE_INCOMPLETE = 4;
    public static final int GIFT_CANUSE_USED_OTHER_PROJECT = 5;

    //新手礼包领取房屋身份范围
    public static final List<Integer> GIFT_ROLE_LIST = Arrays.asList(new Integer[]{MemberRole.ONWER.code,MemberRole.RELATIVE.code,MemberRole.RENTER.code});
    //物业费代扣房屋身份范围
    public static final List<Integer> AUTOPAY_ROLE_LIST = Arrays.asList(new Integer[]{MemberRole.ONWER.code,MemberRole.RELATIVE.code,MemberRole.RENTER.code});


    //token版本兼容阀值
    public static final Integer TOKEN_VERSION = 270000;
    //重庆龙湖报事评价抽奖活动，是否可参加校验
    public static final int LONGFOR_WHEEL_LIMIT_TYPE_PHONE = 1;
    
    // 门禁卡
    public static final int EFFECTIVE_DAYS = Integer.parseInt(APIPropertiesClient.getValue("entrance.card.effective.days"));
    public static final int BINDED_MAX = Integer.parseInt(APIPropertiesClient.getValue("entrance.card.binded.max"));
    public static final int SHOW_ROOM_NUM = Integer.parseInt(APIPropertiesClient.getValue("entrance.card.show.room.num"));
    public static final int EXPIRE_DAYS = Integer.parseInt(APIPropertiesClient.getValue("entrance.card.soon.expire.days"));
    public static final String CARD_NO_REGEX = "^04[0-9A-Fa-f]{12}$";//千丁门禁卡号规则,04开头，总长14位16进制字符串
    
    //订单已评价消息通知
    public static final String PINJIA_NOTICE_ORDER = APIPropertiesClient.getValue("pinjia_notice_order");
    //全部
    public static final Integer BRICKSOURCETYPE_ALL = 0;
    //微信
    public static final Integer BRICKSOURCETYPE_WX = 1;
    //app
    public static final Integer BRICKSOURCETYPE_APP = 2;
    //支付宝
    public static final Integer BRICKSOURCETYPE_ALI = 3;
    // PAD
    public static final Integer BRICKSOURCETYPE_PAD = 4;


    //商品 销售平台
    //微信
    public static final Integer SALEPLATFORM_WX = 2;
    //app
    public static final Integer SALEPLATFORM_APP = 1;


    //注册来源
    //微信
    public static final Integer REGISTER_SOURCE_WEIXIN = 61;
    //APP
    public static final Integer REGISTER_SOURCE_APP = 2;


    //报事保修source
    //app
    public static final Integer HK_PASTRELEASESOURCE_APP = 0;
    //后台
    public static final Integer HK_PASTRELEASESOURCE_BACK = 1;
    //门岗机
    public static final Integer HK_PASTRELEASESOURCE_MACHINE = 2;

    //平台 http://wiki.qdingnet.com/pages/viewpage.action?pageId=5407452
    public static final String QD_PLATFORM_IOS = "ios";
    public static final String QD_PLATFORM_ANDROID = "android";
    public static final String QD_PLATFORM_WEIXIN = "weixin";
    public static final String QD_PLATFORM_QDPAD = "pad";
    public static final String QD_PLATFORM_ALIPAY = "alipay";

    //话题时间维度状态--未开始
    public static final Integer TOPIC_STEP_0 = 0;
   //话题时间维度状态--进行中
    public static final Integer TOPIC_STEP_1 = 1;
   //话题时间维度状态--已结束
    public static final Integer TOPIC_STEP_2 = 2;
    //冻结会员冻结列表
    public static final String FREEZ_EMEMBER_READ_KEY = "freez:member";
    public static final String CHECK_MSG_VERIFY_FREQUENCY_KEY="verify:num:%s";

    //每日鲜markingCode
    public static final String MRX_MAKING_CODE ="ddf";
    //每日鲜配送费markingCode
    public static final String MRX_PSF_MAKING_CODE ="ddfps";
    //新零售配送费markingCode
    public static final String NEWSE_PSF_MAKING_CODE ="ns_freight";

    //3.2版本居家服务 营销位
    public static final String AH_NSF_PT = "AH_NSF_PT";
    //收银台支持待客下单提示语
    public static final String  VALET_ORDER_CONTENT="如无法使用以上方式，请到物业前台处理";
    // 商户融云前缀
    public static final String SUPPER_PREFIX = "SH_";
    //商户融云默认头像
    public static  final String DEFAULT_SUPPLIER_HEAD_IMG="https://img1.qdingnet.com/image-6737ae86-252d-4285-bc4e-f5edec5c9791.png";

    //订单高亮按钮色值
    public static  final  String ORDER_BTN_COLOR="#FDA413";
    // 生鲜品类
    public static final Integer ORDER_SX_CATEGORY = 41;
    // 管家服务默认身份权限
    public static final String DETAL_IDENTITY="0";
   // 首页新手礼包图
    public static final String GIFT_IMG="https://img1.qdingnet.com/image-0c872b45-660f-450e-bde3-731a5d077379.png";

    //长短连接转换地址字典key值
    public static final String URL_CONVERT_DICTNAME = "URL_CONVERT_ADR";
    //物流记录结尾话术
    public static final String markingDesc = "订单状态：您在%s购买的商品正准备出库";
    //周先生物流记录结尾话术
    public static final String ZXS_REMIND_CONTENT = "您所购买的商品中包含周鲜生商品，您可选择将周鲜生商品送货上门，或您自己前往上述地址取货。";
    //推广注册最晚天数
    public static final String LAST_LOGIN_DAY_DICTNAME = "last_reg_day";
    //邻聚小助手用户类型
    public static final String XZS = "1";
    //冻结停用账号返回code
    public static final Integer FREEZE_CODE = 409;
    //设置新零售商品数据流最大页码为20
    public static final Integer MAX_PAGE = 20;


    //v3 首页推荐板块
    public static final String AH_CD = "AH_CD";//日历
    public static final String AH_GL = "AH_GL";//品质生活
    public static final String AH_GS = "AH_GS";//福利铺
    public static final String AH_PA = "AH_PA";//整合营销
    public static final String AH_SF = "AH_SF";//居家服务
    public static final String AH_NT = "AH_NT";//公告
    public static final String AH_PT = "AH_PT";//社区动态
    public static final String AH_RC = "AH_RC";//提醒
    public static final String AH_RT = "AH_RT";//推荐工具
    public static final String AH_HS = "AH_HS";//热卖
    public static final String AH_FP = "AH_FP";//四营销入口
    public static final String AH_LV = "AH_LV";//出游玩乐
    public static final String AH_NG = "AH_NG";//商城板块
    
    public static final String AH_PBS_4 = "AH_PBS_4.0";//基础服务
    public static final String AH_PPS_4 = "AH_PPS_4.0";//业态服务
    public static final String AH_RI_4 = "AH_RI_4.0";//轮播
    public static final String CUSTOM_SECTION = "CUSTOM_SECTION";//自定义
    public static final String AH_LLHD_4 = "AH_LLHD_4.0";//邻里互动
    
    
    public static final List<String> noConfigBoardList = Arrays.asList(new String[]{"AH_NT", "AH_PT", "AH_RC", "AH_RT", "AH_HS","AH_PBS_4.0","AH_PPS_4.0","AH_LLHD_4.0"});


    //可申请入群的身份角色 (业主，业主亲戚，业主朋友)
    public static final List<Short> hk_indentity_apply_gcroom = Arrays.asList(new Short[]{1, 2, 3});
    //首页推荐位类型
    public static final Integer RECOMMEND_THEME_TYPE = 2;
    //不支持pos支付社区列表
    public static final List<String> posProject = Arrays.asList(new String[]{"105", "109", "121", "554", "557", "563", "569", "582", "588", "591", "600", "604", "615", "617", "620", "628", "632", "633", "635", "656", "659", "672", "673", "675", "676", "681", "682", "685", "1732", "1736", "1752", "1785", "1816", "1817", "1824", "1825", "1871", "1941", "21000000023004", "21000000047318"});
    //短信验证码频率校验
    public static final String SEND_MSG_DEFAULT_FLAG = "e2jkl1o0";
    //通用业态在社区首页推荐中的类型码
    public static final int CB_SUB_TYPE = 14;
    //亲情支付系统通知获取编号
    public static final String FAMILY_PAY_NOTICE_SKIP_NO = MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_FP;
    //积分系统消息获取编号
    public static final String APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_CHANGE = MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_CHANGE;
    public static final String APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_NOTICE = MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_NOTICE;
    public static final String APP_PUSH_MESSAGE_CENTER_TYPE_NEIGHBOR = MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_NEIGHBOR;
    public static final String APP_PUSH_MESSAGE_CENTER_TYPE_NEIGHBOR_DETAIL = MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_NEIGHBOR_DETAIL;
    public static final String APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM = MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM;
    public static final String APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_H5 =MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_H5;
    public static final String APP_PUSH_MESSAGE_CENTER_TYPE_XY= MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_XY; //洗衣
    public static final String APP_NOTIFY_MEIQIA = MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_MEIQIA; //美洽系统消息类型
    public static final String   APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_V3 = MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_V3;//推送3.0绑定房屋


    //推广模块常量列表
    //待审核
    public static Integer POPULARIZE_WAIT_AUDIT = 0;
    //已通过审核
    public static Integer POPULARIZE_PASS_AUDIT = 1;
    //未通过审核
    public static Integer POPULARIZE_NOT_PASS_AUDIT = 2;
    //冻结
    public static Integer POPULARIZE_FREEZE_AUDIT = 3;
    public static Integer POPULARIZE_NO_APPLY = -1;


    /*** 积分规则常量约定 ***/
    //0：积分回退操作类型
    public static final String BACK_OPT_TYPE = "0";
    //1：积分收入操作类型
    public static final String INCOME_OPT_TYPE = "1";
    //绑定房屋
    public static final String INTEGRAL_BIND_ROOM = "bind_room";
    //预存
    public static final String INTEGRAL_PREDEPOSIT = "predeposit";
    //购买商品（含业态）
    public static final String INTEGRAL_BUY = "buy";
    //通行证
    public static final String INTEGRAL_PASS_PORT = "pass_port";

    //登录
    public static final String INTEGRAL_LOGIN = "login";

    //为访客生成二维码访客通行证
    public static final String INTEGRAL_VISITOR_ACCESS = "visitor_access";
    //为当前业主自己生成二维码访客通行证
    public static final String INTEGRAL_VISITOR_ACCESS_M = "visitor_access_m";
    //邀请注册
    public static final String INTEGRAL_INVITE_REG = "invite_reg";
    //发帖
    public static final String INTEGRAL_PUBLISH_FEED = "publish_feed";
    //累积赞数
    public static final String INTEGRAL_PRAISE = "praise";
    //累积评论数
    public static final String INTEGRAL_COMMENT = "comment";
    //保修
    public static final String INTEGRAL_REPAIR = "repair";
    //保修评价
    public static final String INTEGRAL_REPAIR_EVALUATE = "repair_evaluate";
    //签到
    public static final String INTEGRAL_SIGN_IN = "sign_in";
    //物业费
    public static final String INTEGRAL_PROPERTY_MANAGE_FEE = "property_manage_fee";
    //停车费
    public static final String INTEGRAL_PARKING_FEE = "parking_fee";
    //评价商品（含业态）
    public static final String INTEGRAL_EVALUATE = "evaluate";


    //蓝牙门禁类型
    public static final Integer HK_PAST_BLUETOOH = 2;
    //二维码门禁类型
    public static final Integer HK_PAST_QRCODE = 1;
    //蓝牙及二维码通用门禁类型
    public static final Integer HK_PAST_COMMON = 3;
    //亲情支付-身份类型 1:开通者; 2:使用者
    public static final String FROMTOTYPE_FROM = "1";
    public static final String FROMTOTYPE_TO = "2";

    //业态订单跳转类型 0：不可跳转  2：可以跳转
    public static final String SKIP_OK = "1";
    public static final String SKIP_NO = "0";

    //千丁券分类
    public static final String GROUP_NAME_PRODUCT_NO = "product_no";
    //未知
    public static final String PRODUCT_NO_00 = "00";
    //乐购
    public static final String PRODUCT_NO_NG = "NG";
    //旅游
    public static final String PRODUCT_NO_LY = "LY";
    //洗衣
    public static final String PRODUCT_NO_XY = "XY";
    //新洗衣
    public static final String PRODUCT_NO_NY = "NY";
    //洗车
    public static final String PRODUCT_NO_XC = "XC";
    //充值
    public static final String PRODUCT_NO_CZ = "CZ";
    //保洁
    public static final String PRODUCT_NO_BJ = "BJ";
    //阶梯团购
    public static final String PRODUCT_NO_TG = "TG";
    //通用业态
    public static final String PRODUCT_NO_CB = "CB";
    //物业费
    public static final String PRODUCT_NO_WF = "WF";
    //团购
    public static final String PRODUCT_NO_SG = "SG";
    //积分
    public static final String PRODUCT_NO_JF = "JF";
    //新保洁
    public static final String PRODUCT_NO_NC = "NC";
    //旅游线路
    public static final String PRODUCT_NO_TL = "TL";
    //保姆
    public static final String PRODUCT_NO_BM = "AY";
    //旅游服务
    public static final String PRODUCT_NO_TS = "TS";
    //聚合业态
    public static final String PRODUCT_NO_CS= "CS";
    //车保养
    public static final String PRODUCT_NO_MN = "MN";
    //物业服务
    public static final String PRODUCT_NO_PS = "PS";
    //虚拟商城服务
    public static final String PRODUCT_NO_VM = "VM";
    //永辉超市
    public static final String PRODUCT_NO_MS = "MS";
    //龙湖房屋入住签约评价
    public static final String PRODUCT_NO_REALESTATE = "RT";
    //签约
    public static final String PRODUCT_NO_LHZY = "LHZY";
    //入住
    public static final String PRODUCT_NO_LHRZ = "LHRZ";
    //保险
    public static final String PRODUCT_NO_BX = "BX";

    //支付方式
    public static final Integer PAYMENT_TYPE_CASH = 11;
    public static final Integer PAYMENT_TYPE_POS = 21;
    public static final Integer PAYMENT_TYPE_ALIPAY = 31;
    public static final Integer PAYMENT_TYPE_WEIXIN = 41;
    public static final Integer PAYMENT_TYPE_WEIXINAPP = 51;
    public static final Integer PAYMENT_TYPE_WALLET_CARD = 61;
    public static final Integer PAYMENT_TYPE_WYHT = 141;//物业后台
    public static final Integer PAYMENT_ONE_NET=161;//一网通 招商银行
    public static final Integer PAYMENT_DIANYIN=93;//电银


    //订单列表 全部
    public static final Integer ORDER_LIST_ALL = 0;
    //订单列表 待付款
    public static final Integer ORDER_LIST_WAIT_PAY = 1;
    //订单列表 已取消
    public static final Integer ORDER_LIST_CANCEL = 2;
    //订单列表 待发货
    public static final Integer ORDER_LIST_WAIT_DELIVERY = 3;
    //订单列表 待签收
    public static final Integer ORDER_LIST_WAIT_SIGN = 4;
    //订单列表 退款中
    public static final Integer ORDER_LIST_REFUND = 5;


    /**
     * 订单状态
     */
    //101	活动订单
    public static final Integer ORDER_STATUS_101 = 101;
    //201	客户取消
    public static final Integer ORDER_STATUS_201 = 201;
    //203	订单超时
    public static final Integer ORDER_STATUS_203 = 203;
    //204	库存不足
    public static final Integer ORDER_STATUS_204 = 204;
    //202	客服取消
    public static final Integer ORDER_STATUS_202 = 202;
    //301	已分类
    public static final Integer ORDER_STATUS_301 = 301;
    //401	完成
    public static final Integer ORDER_STATUS_401 = 401;




    /**
     * 乐购子订单状态
     */

    //正常
    public static final Integer LEGOU_SUB_ORDER_STATUS_101 = 101;
    //退款中
    public static final Integer LEGOU_SUB_ORDER_STATUS_102 = 102;
    //已退款
    public static final Integer LEGOU_SUB_ORDER_STATUS_103 = 103;
    //已作废
    public static final Integer LEGOU_SUB_ORDER_STATUS_104 = 104;
    //退款失败
    public static final Integer LEGOU_SUB_ORDER_STATUS_105 = 105;


    /**
     * 支付状态
     */

    //101 	未支付
    public static final Integer PAYMENT_STATUS_101 = 101;
    //104 	部分支付
    public static final Integer PAYMENT_STATUS_104 = 104;
    //105 	已支付
    public static final Integer PAYMENT_STATUS_105 = 105;
    //102 	支付失败
    public static final Integer PAYMENT_STATUS_102 = 102;
    //103 	支付未完成
    public static final Integer PAYMENT_STATUS_103 = 103;
    //106 	退款中
    public static final Integer PAYMENT_STATUS_106 = 106;
    //107 	部分退款
    public static final Integer PAYMENT_STATUS_107 = 107;
    //108 	已退款
    public static final Integer PAYMENT_STATUS_108 = 108;



    //订单状态为完成状态（用于判断是否）
    public static final Integer ORDER_VOER_STATUS = 401;
    //订单支付完成状态
    public static final Integer ORDER_PAY_OVER_STATUS = 101;
    //订单已取消状态
    public static final Integer ORDER_CANCEL_STATUS = 101;
    //订单已发货状态
    public static final Integer ORDER_DELIVERYED_STATUS = 101;
    //订单已到货状态
    public static final Integer ORDER_ARRIVED_STATUS = 102;

    //订单支付完成状态 （101 和 105 的区别 廖望梅）
    public static final Integer ORDER_PAY_END_STATUS = 105;

    //新零售售后 相应发货状态 -100(未发货)101(已发货)102(已到货)103(已签收)104(已退货)105(部分发货)106（部分签收）107（部分退货）108（部分到货）999（未知状态）
    public static final  List<Integer> DELIVER_STATUS_FOR_AFTERSALE_STATUS_LIST = Lists.newArrayList(101, 102, 103, 105, 106, 107, 108);

    public enum PayTypeEnum {
        PAYMENT_TYPE_CASH (11,"现金"),
        PAYMENT_TYPE_POS (21,"pos机"),
        PAYMENT_TYPE_ALIPAY(31,"支付宝"),
        PAYMENT_TYPE_WEIXIN(41,"微信"),
        PAYMENT_TYPE_WEIXINAPP(51,"微信"),
        PAYMENT_TYPE_WALLET_CARD(61,"余额"),
        PAYMENT_TYPE_WYHT(141,"现金"),
        PAYMENT_ONE_NET(161,"一网通"),
        PAYMENT_DIANYIN(93,"电银快捷支付"),
        PAYMENT_ZUHE(91,"组合支付");

        private  Integer code;

        private  String description;

        // 构造函数，枚举类型只能为私有
        private PayTypeEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }


        public static PayTypeEnum getByCode(Integer code){
            for (PayTypeEnum payTypeEnum : values()) {
                if (payTypeEnum.getCode().intValue() == code.intValue()) {
                    return payTypeEnum;
                }
            }
            return null;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    //支付状态枚举
    public enum PayStatusEnum {

        PAYMENT_STATUS_101(101, "未支付"),
        PAYMENT_STATUS_102(102, "支付失败"),
        PAYMENT_STATUS_104(104, "部分支付"),
        PAYMENT_STATUS_103(103, "支付未完成"),
        PAYMENT_STATUS_105(105, "已支付"),
        PAYMENT_STATUS_106(106,"退款中"),
        PAYMENT_STATUS_107(107,"部分退款"),
        PAYMENT_STATUS_108(108,"已退款");

        private  Integer code;

        private  String description;

        // 构造函数，枚举类型只能为私有
        private PayStatusEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


    //支付状态枚举
    public enum OrderStatusEnum {

        ORDER_STATUS_101(101, "活动订单"),
        ORDER_STATUS_201(201, "客户取消"),
        ORDER_STATUS_203(203, "订单超时"),
        ORDER_STATUS_204(204, "库存不足"),
        ORDER_STATUS_202(202, "客服取消"),
        ORDER_STATUS_301(301,"已分类"),
        ORDER_STATUS_401(401,"完成");

        private  Integer code;

        private  String description;

        // 构造函数，枚举类型只能为私有
        private OrderStatusEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }




    /**
     * 乐购商品状态
     */

    // 已退货
    public static final Integer LEGOU_PRODUCT_STATUS_100 = 100;
    //已发货
    public static final Integer LEGOU_PRODUCT_STATUS_101 = 101;
    //已到货
    public static final Integer LEGOU_PRODUCT_STATUS_102 = 102;
    //已签收
    public static final Integer LEGOU_PRODUCT_STATUS_103 = 103;
    //已退货
    public static final Integer LEGOU_PRODUCT_STATUS_104 = 104;


    // 会员状态 启用
    public static final Integer MEMBER_STATUS_ENABLE = 1;

    /**
     * 我的钱包状态
     */
    //未开通
    public static final Integer WALLET_STATUS_NOTSET = 2;
    //正常
    public static final Integer WALLET_STATUS_NORMAL = 1;
    //冻结
    public static final Integer WALLET_STATUS_FORZEN = 0;


    /**
     * 我的钱包
     */

    //业态app形式跳转订单详情
    public static final String PRODUCT_SKIP_APP = "1";
    //业态html5形式跳转订单详情
    public static final String PRODUCT_SKIP_HTML = "2";


    /**
     * 个性化配置
     */
    //首页
    public static final String SERVICE_ITEM_HOME = "home";
    //消息
    public static final String SERVICE_ITEM_NOTIFY = "notify";
    //订单
    public static final String SERVICE_ITEM_ORDER = "order";
    //管家
    public static final String SERVICE_ITEM_MANAGER = "manager";
    //PAD首页
    public static final String SERVICE_ITEM_PAD_HOME = "PAD_HOME";

    public static final String SERVICE_ITEM_PAD_ORDER = "PAD_ORDER";



    public static Map<String, Integer> salePlatformMap = new HashMap<String, Integer>();

    public static Map<String, Integer> brickSourceTypeMap = new HashMap<String, Integer>();

    public static Map<String, Integer> brickSourceTypeMap2 = new HashMap<String, Integer>();

    public static Map<String, Integer> hkPastReleaseSourceMap = new HashMap<String, Integer>();

    public static Map<String, Integer> feeOrderReleaseSourceMap = new HashMap<String, Integer>();

    public static Map<String, Integer> registerSourcreMap = new HashMap<String, Integer>();

    static {
        registerSourcreMap.put(QD_PLATFORM_IOS, REGISTER_SOURCE_APP);
        registerSourcreMap.put(QD_PLATFORM_ANDROID, REGISTER_SOURCE_APP);
        registerSourcreMap.put(QD_PLATFORM_WEIXIN, REGISTER_SOURCE_WEIXIN);
    }

    static {
        salePlatformMap.put(QD_PLATFORM_IOS, SALEPLATFORM_APP);
        salePlatformMap.put(QD_PLATFORM_ANDROID, SALEPLATFORM_APP);
        salePlatformMap.put(QD_PLATFORM_WEIXIN, SALEPLATFORM_WX);
        salePlatformMap.put(QD_PLATFORM_QDPAD, SALEPLATFORM_APP);
        salePlatformMap.put(QD_PLATFORM_ALIPAY, SALEPLATFORM_WX);
    }


    static {
        brickSourceTypeMap.put(QD_PLATFORM_IOS, BRICKSOURCETYPE_APP);
        brickSourceTypeMap.put(QD_PLATFORM_ANDROID, BRICKSOURCETYPE_APP);
        brickSourceTypeMap.put(QD_PLATFORM_WEIXIN, BRICKSOURCETYPE_WX);
        brickSourceTypeMap.put(QD_PLATFORM_ALIPAY, BRICKSOURCETYPE_WX);
        brickSourceTypeMap.put(QD_PLATFORM_QDPAD, BRICKSOURCETYPE_APP);
    }

    static {
        brickSourceTypeMap2.put(QD_PLATFORM_IOS, BRICKSOURCETYPE_APP);
        brickSourceTypeMap2.put(QD_PLATFORM_ANDROID, BRICKSOURCETYPE_APP);
        brickSourceTypeMap2.put(QD_PLATFORM_WEIXIN, BRICKSOURCETYPE_WX);
        brickSourceTypeMap2.put(QD_PLATFORM_QDPAD, BRICKSOURCETYPE_PAD);
        brickSourceTypeMap2.put(QD_PLATFORM_ALIPAY, BRICKSOURCETYPE_ALI);
    }

    static {

        hkPastReleaseSourceMap.put(QD_PLATFORM_IOS, HK_PASTRELEASESOURCE_APP);
        hkPastReleaseSourceMap.put(QD_PLATFORM_ANDROID, HK_PASTRELEASESOURCE_APP);

    }


    static {
        feeOrderReleaseSourceMap.put(QD_PLATFORM_IOS, 0);
        feeOrderReleaseSourceMap.put(QD_PLATFORM_ANDROID, 0);
        feeOrderReleaseSourceMap.put(QD_PLATFORM_WEIXIN, 2);
        feeOrderReleaseSourceMap.put(QD_PLATFORM_QDPAD, 0);
        feeOrderReleaseSourceMap.put(QD_PLATFORM_ALIPAY, 4);
    }

    /**
     * AppDevice.qdPlatform 转换成相应的字典数据
     *
     * @param map
     * @param device
     * @return
     * @throws ServiceException
     */
    public static Integer transforPlatformByAppDevice(Map<String, Integer> map, AppDevice device) throws ServiceException {

        if (device == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "appDevice为必填项");
        }

        String qdPlatform = device.getQdPlatform();

        if (qdPlatform == null) {

            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "未知的qdPlatform");

        }

        Integer value = map.get(qdPlatform.toLowerCase());

        if (value == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "未知的qdPlatform");
        }

        return value;
    }


    /**
     * 业态转换成相应的字典数据
     *
     * @param map
     * @param device
     * @return
     * @throws ServiceException
     */
    public static String transforServiceByAppDevice(Map<String, String> map, AppDevice device) throws ServiceException {

        if (device == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "appDevice为必填项");
        }

        String qdPlatform = device.getQdPlatform();

        if (qdPlatform == null) {

            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "未知的qdPlatform");

        }

        String value = map.get(qdPlatform.toLowerCase());

        if (value == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "未知的qdPlatform");
        }

        return value;
    }


    //需要异步调用各业态订单状态
    public static final List<String> ProductNoForOrderStatusList = new ArrayList<String>() {{
        add(Constant.PRODUCT_NO_XY);
        add(Constant.PRODUCT_NO_BJ);
        add(Constant.PRODUCT_NO_LY);
        add(Constant.PRODUCT_NO_XC);
        add(Constant.PRODUCT_NO_TG);
        add(Constant.PRODUCT_NO_CB);
        add(Constant.PRODUCT_NO_SG);
        add(Constant.PRODUCT_NO_JF);
        add(Constant.PRODUCT_NO_NC);
        add(Constant.PRODUCT_NO_TL);
        add(Constant.PRODUCT_NO_BM);
        add(Constant.PRODUCT_NO_TS);
        add(Constant.PRODUCT_NO_CS);
        add(Constant.PRODUCT_NO_MN);
        add(Constant.PRODUCT_NO_PS);
        add(Constant.PRODUCT_NO_VM);
        add(Constant.PRODUCT_NO_MS);
        add(Constant.PRODUCT_NO_BX);
    }};

    //需要通过solr查询商品详情的业态
    public static final List<String> GetGoodsDetailFromSolrList = new ArrayList<String>() {{
        add(Constant.PRODUCT_NO_NG);
        add(Constant.PRODUCT_NO_XC);
        add(Constant.PRODUCT_NO_XY);
        add(Constant.PRODUCT_NO_BJ);
    }};

    public static Map<String, String> serviceItemHomeMap = new HashMap<String, String>();

    static {
        serviceItemHomeMap.put(QD_PLATFORM_IOS, SERVICE_ITEM_HOME);
        serviceItemHomeMap.put(QD_PLATFORM_ANDROID, SERVICE_ITEM_HOME);
        serviceItemHomeMap.put(QD_PLATFORM_WEIXIN, SERVICE_ITEM_HOME);
        serviceItemHomeMap.put(QD_PLATFORM_QDPAD, SERVICE_ITEM_PAD_HOME);
        serviceItemHomeMap.put(QD_PLATFORM_ALIPAY, SERVICE_ITEM_HOME);
    }

    public static Map<String, String> serviceItemOrderMap = new HashMap<String, String>();

    static {
        serviceItemOrderMap.put(QD_PLATFORM_IOS, SERVICE_ITEM_ORDER);
        serviceItemOrderMap.put(QD_PLATFORM_ANDROID, SERVICE_ITEM_ORDER);
        serviceItemOrderMap.put(QD_PLATFORM_WEIXIN, SERVICE_ITEM_ORDER);
        serviceItemOrderMap.put(QD_PLATFORM_QDPAD, SERVICE_ITEM_PAD_ORDER);
        serviceItemOrderMap.put(QD_PLATFORM_ALIPAY, SERVICE_ITEM_ORDER);
    }


    private static Map<String, Integer> releaseMap = new HashMap<String, Integer>();
    static {
        releaseMap.put("0", -1);//朋友来访
        releaseMap.put("1", 1);//家政服务
        releaseMap.put("2", -1);//装修放行
        releaseMap.put("3", 1);//送货上门
        releaseMap.put("4", 1);//搬家放行
        releaseMap.put("5", -1);//我的钥匙
        releaseMap.put("6", 1);//中介看房
    }
    public static Integer getReleaseNum(String key) {
        return releaseMap.get(key);
    }


    private static Map<String, Integer[]> validateTimeMap = new HashMap<String, Integer[]>();
    static {
        validateTimeMap.put("1", new Integer[]{-1});//业主
        validateTimeMap.put("2", new Integer[]{-1});//业主亲戚
        validateTimeMap.put("3", new Integer[]{-1});//业主朋友
        validateTimeMap.put("4", new Integer[]{3, 6, 12});//租客
        validateTimeMap.put("5", new Integer[]{1, 3, 6});//装修负责人
    }

    public static Integer[] getValidatyTimeKey(String key) {
        return validateTimeMap.get(key);
    }



    //业态订单跳转类型 1：APP跳转  2：html5跳转
    private static Map<String, String> productSkipTypeMap = new HashMap<String, String>();
    static {
        productSkipTypeMap.put(PRODUCT_NO_LY, PRODUCT_SKIP_APP);//旅游
        productSkipTypeMap.put(PRODUCT_NO_XY, PRODUCT_SKIP_APP);//洗衣
        productSkipTypeMap.put(PRODUCT_NO_NG, PRODUCT_SKIP_APP);//新乐购
        productSkipTypeMap.put(PRODUCT_NO_NY, PRODUCT_SKIP_APP);//新洗衣
        productSkipTypeMap.put(PRODUCT_NO_XC, PRODUCT_SKIP_HTML);//洗车
        productSkipTypeMap.put(PRODUCT_NO_CZ, PRODUCT_SKIP_APP);//充值
        productSkipTypeMap.put(PRODUCT_NO_BJ, PRODUCT_SKIP_HTML);//保洁
        productSkipTypeMap.put(PRODUCT_NO_00, PRODUCT_SKIP_APP);//未知
    }
    public static String getproductSkipType(String key) {
        return productSkipTypeMap.get(key);
    }


    public static Map<Integer, Integer> hkReportScoreMap = new HashMap<Integer, Integer>();

    static {
        hkReportScoreMap.put(0, 5);
        hkReportScoreMap.put(1, 3);
        hkReportScoreMap.put(2, 1);
    }



    public enum SkipType {
        NOTICE_DETAIL("notice");
        private String _skip;
        private SkipType(String _skip) {
            this._skip = _skip;
        }
        public String toString() {
            return this._skip;
        }
    }


    public enum Section {

        QIN_ZI(1),//亲子板块
        LV_YOU(2),//旅游版块
        SHENG_XIAN(3),//生鲜版块
        TE_SE_SHANG_PIN(4),//特色商品版块
        HUO_DONG(5),//活动版块
        LOU_CENG(6),//运营楼层版块
        LG_CENG(9);//乐购版块

        // 定义私有变量
        private int sectionId;

        // 构造函数，枚举类型只能为私有
        private Section(int _sectionId) {
            this.sectionId = _sectionId;
        }

        public Integer toInteger() {
            return Integer.valueOf(this.sectionId);
        }

    }


    public enum SkipNo {

        // 利用构造函数传参
        URL_7000(7000), // url
        URL_7001(7001), // 分享url
        SPLB_5000(5000), //商品列表
        SPPL_5003(5003),//商品品类
        SPXQ_5004(5004),//商品详情
        TSSP_5005(5005),//首页特色商品
        TXZ_1001(1001),//通行证
        SQGG_1002(1002),//社区公告列表
        QZGGXQ_3010(3010),//邻聚群公告详情点击模型
        HDXQ_3003(3003),//通过活动ID获取活动详情
        DDXQ_4008(4008),//乐购订单详情点击模型
        DDQKJ_4009(4009),//通过订单ID去往订单评价页模型
        LGSY_5006(5006),//通过品类ID去往乐购首页模型

        APPHOME_1000(1000),//APP首页
        HKHOME_2000(2000),//管家首页
        LJHD_3003(3003),// 邻聚活动详情
        JFMX_4106(4106),//积分明细，现改为HTML5,程序处理需要给前端URL模式即skipNo:7000
        MEIQI_4206(4206), //美洽页面
        ROBOT_4208(4208), //机器人页面
        BINDMEMBERLIST_4207(4207), //房屋绑定人列表
        PERSONAL_HOME_4000(4000),//个人主页



        LJGC_3012(3012),//邻聚广场（2.6）
        LJQL_3013(3013),//邻聚-群聊页（2.6）
        TOPICXQ_3014(3014),//话题详情（2.6）
        LJSY_3015(3015),//2.6邻聚个人中心首页（邻聚）
        LJTHEME_3017(3017),//2.6邻聚主题站话题列表
        LJBANNER_3019(3019),//2.8邻聚banner活动跳转
        LJBANNER_3020(3020),//2.8邻聚banner话题列表跳转

        //v3.0 新增
        HK_SERVICE_2000(2000),//管家首页
        HK_SERVICE_2001(2001),//管家电话
        HK_SERVICE_2111(2111),//咨询沟通
        HK_SERVICE_2112(2112),//工程维修
        HK_SERVICE_2113(2113),//秩序维护
        HK_SERVICE_2114(2114),//园区环境
        HK_SERVICE_2115(2115),//开水开电
        HK_SERVICE_2116(2116),//报事报修-户内维修
        HK_SERVICE_2117(2117),//房屋报事
        HK_SERVICE_2118(2118),//管家沟通
        HK_SERVICE_2119(2119),//物业报事

        HK_SERVICE_2100(2100),//报事报修-首页
        HK_SERVICE_2101(2101),//报事报修-新建
        HK_SERVICE_2102(2102),//报事报修-代收包裹页
        HK_SERVICE_2103(2103),//报事报修-更换灯泡页
        HK_SERVICE_2104(2104),//报事报修-更换高压软管页
        HK_SERVICE_2105(2105),// 报事报修-挂画服务页
        HK_SERVICE_2106(2106),//报事报修-线路安装页
        HK_SERVICE_2107(2107),//报事报修-疏通页
        HK_SERVICE_2108(2108),//报事报修-点锅炉页
        HK_SERVICE_2109(2109),//报事报修-家电维修页
        HK_SERVICE_2110(2110),//报事报修-家电清洗页
        HK_SERVICE_2200(2200),//访客通行
        HK_SERVICE_2201(2201),//访客通行-历史记录
        HK_SERVICE_2300(2300),//物业账单
        HK_SERVICE_2400(2400),//邀请加入
        HK_SERVICE_2402(2402),//加入千丁

        HK_SERVICE_8001(8001),//车行管理

        P_NEWS_LIST_3021(3021),//社区-社区新闻列表
        P_NEWS_DETAIL_3022(3022),//社区-社区新闻详情
        P_INTERACT_LIST_3023(3023),//社区-邻里互动帖子列表页
        P_INTERACT_TAG_LIST_3024(3024),//社区-邻里互动标签列表页
        P_INTERACT_DETAIL_3025(3025),//社区-邻里互动帖子详情或跟帖详情
        P_ACTIVITY_LIST_3026(3026),//社区-社区活动列表页
        P_ACTIVITY_PERSON_LIST_3027(3027),//社区-个人中心参与的社区活动列表页
        P_ACTIVITY_DETAIL_3028(3028),//社区-社区活动详情
        P_TOPIC_LIST_3029(3029),//社区-话题列表
        P_TOPIC_PERSON_LIST_3030(3030),//社区-个人参与的话题列表
        P_TOPIC_DETAIL_3031(3031),//社区-话题详情
        P_ENCYCLOPEDIA_DETAIL_3032(3032),//社区-生活百科详情
        P_VOTE_DETAIL_3033(3033),//社区-投票详情


        //v3.2.0新增
        CALENDAR_ACTIVITY_1004(1004); //首页日历活动跳转

        // 定义私有变量
        private int nCode;

        // 构造函数，枚举类型只能为私有
        private SkipNo(int _nCode) {
            this.nCode = _nCode;
        }

        public Integer toInteger() {
            return Integer.valueOf(this.nCode);
        }

    }

    //乐购订单状态与描述映射
    public static Map<Integer,String> orderStatusMap = new HashMap<>();

    static {
        orderStatusMap.put(ORDER_STATUS_401.intValue(),"已完成");
        orderStatusMap.put(ORDER_STATUS_201.intValue(),"已取消");
        orderStatusMap.put(ORDER_STATUS_202.intValue(),"已取消");
        orderStatusMap.put(ORDER_STATUS_203.intValue(),"已取消");
    }


    //订单列表默认图片
    public static Map<String, String[]> defaultPicForOrderListMap = new HashMap<String, String[]>();

    static {
        defaultPicForOrderListMap.put(PRODUCT_NO_XY, new String[]{"https://img1.qdingnet.com/image-89c71de3-caaf-4994-9281-477de6f004d0.png"});
        defaultPicForOrderListMap.put(PRODUCT_NO_XC, new String[]{"https://img1.qdingnet.com/image-e53003aa-42a8-4b7b-857f-a66a129bccd3.png"});
        defaultPicForOrderListMap.put(PRODUCT_NO_NG, new String[]{"https://img1.qdingnet.com/image-309ebcbe-d581-4c46-8903-2c7aaf109fba.png"});
        defaultPicForOrderListMap.put(PRODUCT_NO_BJ, new String[]{"https://img1.qdingnet.com/image-99546d1f-c394-4179-80ef-07a853cc8858.png"});
        defaultPicForOrderListMap.put(PRODUCT_NO_CB, new String[]{"https://img1.qdingnet.com/image-49fdc9ca-fcbf-4714-b4d7-1252fd7585e2.png"});
        defaultPicForOrderListMap.put(PRODUCT_NO_TG, new String[]{"https://img1.qdingnet.com/image-99546d1f-c394-4179-80ef-07a853cc8858.png"});
        defaultPicForOrderListMap.put(PRODUCT_NO_LY, new String[]{"https://img1.qdingnet.com/image-c15577e0-8b0f-471c-b3a8-2a7f10ad027f.png"});

    }

    //订单列表默认图片
    public static Map<String, String> integralToastMap = new HashMap<String, String>();

    static {
        integralToastMap.put(INTEGRAL_EVALUATE, "评价成功！");
        integralToastMap.put(INTEGRAL_REPAIR_EVALUATE, "评价成功！");

    }

    public enum WatchRelationType {
        //MOTHER:妈妈; FATHER:爸爸; GRANDPA:爷爷; GRANDMA:奶奶; GRANDPA2:外公; GRANDMA2:外婆; other:其他亲属
        MOTHER("MOTHER", "妈妈"),
        FATHER("FATHER", "爸爸"),
        GRANDPA("GRANDPA", "爷爷"),
        GRANDMA("GRANDMA", "奶奶"),
        GRANDPA2("GRANDPA2", "外公"),
        GRANDMA2("GRANDMA2", "外婆"),
        OTHER("OTHER", "其他亲属");

        private String key;

        private String value;

        WatchRelationType(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

    }


    //配送方式
    public enum DeliveryType {
        PropertySelf("Property Self", 2),
        Logistics("Logistics", 1);

        private String key;
        private Integer value;

        DeliveryType(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }


    //配送方式名称
    public enum DeliveryTypeName {

        PropertySelf("Property Self", "物业自提"),
        Logistics("Logistics", "运费规则为订单金额<49元收10元，49≤订单金额<99元收8元， 订单金额≥99元免运费。");

        private String key;
        private String value;

        DeliveryTypeName(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }


    //话题类型
    public enum TopicType {

        Common("common", 1),
        Vote("vote", 2),
        Activity("activity", 3),
        MarketingBanner("marketingBanner", 4),
        MarketingUrl("marketingUrl", 5);

        private String key;
        private Integer value;

        TopicType(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }


    public enum ResultCodeEnum {

        // 利用构造函数传参
        VOICE_412(412), // 需要发送语音验证码
        SMS_411(411), // 需要发送短信验证码
        DEVICE_409(409), // 无效的deviceId
        INVALID_MOBILE_205(205);//无效手机号

        private int nCode;

        private ResultCodeEnum(int _nCode) {
            this.nCode = _nCode;
        }

        public int toInteger() {
            return this.nCode;
        }

    }

    public enum SubTopicTypeEnum {

        // 利用构造函数传参
        IMG_1(1,"晒图话题"), // 晒图
        DISCUSS_2(2,"晒图话题"), // 讨论
        VOTE_3(3,"投票型话题"),//投票
        ACTIVITY_4(4,"报名"),//报名
        COMMON_5(5,"邻里互动"), //邻里互动
        NEWS_6(6,"新闻"), //新闻
        ENCYCOLPEDIA_7(7,"百科");//百科

        // 定义私有变量
        public final int nCode;

        private String value;
        // 构造函数，枚举类型只能为私有
        private SubTopicTypeEnum(int _nCode,String value) {
            this.nCode = _nCode;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getnCode() {
            return nCode;
        }

        public Integer toInteger() {
            return Integer.valueOf(this.nCode);
        }

        public static SubTopicTypeEnum getSubTopicType(int  _nCode)
        {
            for(SubTopicTypeEnum subTopicType: SubTopicTypeEnum.values())
            {
                if(subTopicType.getnCode() ==  _nCode)
                {
                    return subTopicType;
                }
            }
            return null;
        }

    }


    //手机检测消息内容
    public enum CheckMobileMsgEnum {

        INVALID_MOBILE("当前手机号无效"),
        NO_DEVICEID_REGISTER("系统检测到您当前在无效设备上进行注册，无法完成注册"),
        NO_DEVICEID_LOGIN("系统检测到您当前在无效设备上进行登录，无法正常登录千丁"),
        INVALID_DEVICEID_LGOIN("系统检测到您的登录存在异常，需要通过语音验证码验证您的登录"), //设备ID无效
        OUT_DISTANCE_VOICE_LGOIN("系统检测到您当前登录存在异常或尝试在新设备上登录，需要通过语音验证码验证您的登录"),
        OUT_DISTANCE_SMS_LGOIN("系统检测到您当前登录存在异常或尝试在新设备上登录，需要通过短信验证码验证您的登录");

        // 定义私有变量
        public final String msg;

        // 构造函数，枚举类型只能为私有
        private CheckMobileMsgEnum(String _msg) {
            this.msg = _msg;
        }

    }

    public enum RobotIndexContnetEnum {
        ROBOT_INDEX_CONTENT_1("您好，我是小丁智能机器人，现在由我为您服务。找服务、找商品、咨询问题、可以这样直接对我说，请尽量使用普通话哦："),
        ROBOT_INDEX_CONTENT_2("我想买苹果\n我要做保洁\n明天天气怎么样");

        // 定义私有变量
        public final String content;

        // 构造函数，枚举类型只能为私有
        private RobotIndexContnetEnum(String _content) {
            this.content = _content;
        }
    }


    /**
     * 促销类型
     */
    public enum PromotionTypeEnum{

        TYPE_6(6, "阶梯团购");

        private  Integer code;

        private  String description;

        // 构造函数，枚举类型只能为私有
        private PromotionTypeEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


    //需要配置权限属性的服务项 用于skipmodel组装
    public static final List<Integer> SERVICE_SKIPNO_LIST = new ArrayList<Integer>() {{
        add(SkipNo.TXZ_1001.toInteger());
        add(SkipNo.HK_SERVICE_2001.toInteger());
        add(SkipNo.HK_SERVICE_2111.toInteger());
        add(SkipNo.HK_SERVICE_2112.toInteger());
        add(SkipNo.HK_SERVICE_2113.toInteger());
        add(SkipNo.HK_SERVICE_2114.toInteger());
        add(SkipNo.HK_SERVICE_2115.toInteger());
        add(SkipNo.HK_SERVICE_2100.toInteger());
        add(SkipNo.HK_SERVICE_2101.toInteger());
        add(SkipNo.HK_SERVICE_2102.toInteger());
        add(SkipNo.HK_SERVICE_2103.toInteger());
        add(SkipNo.HK_SERVICE_2104.toInteger());
        add(SkipNo.HK_SERVICE_2105.toInteger());
        add(SkipNo.HK_SERVICE_2106.toInteger());
        add(SkipNo.HK_SERVICE_2107.toInteger());
        add(SkipNo.HK_SERVICE_2108.toInteger());
        add(SkipNo.HK_SERVICE_2109.toInteger());
        add(SkipNo.HK_SERVICE_2111.toInteger());
        add(SkipNo.HK_SERVICE_2200.toInteger());
        add(SkipNo.HK_SERVICE_2201.toInteger());
        add(SkipNo.HK_SERVICE_2300.toInteger());
        add(SkipNo.HK_SERVICE_2400.toInteger());
        add(SkipNo.HK_SERVICE_2402.toInteger());
        add(SkipNo.HK_SERVICE_2116.toInteger());
        add(SkipNo.HK_SERVICE_2117.toInteger());
        add(SkipNo.HK_SERVICE_2118.toInteger());
        add(SkipNo.HK_SERVICE_2119.toInteger());
    }};


    //字典k-v映射关系 （所有字典类查询都统一在此管理）
    public enum Dict_K_V_Enum {
        DICT_CHECK_DISTANCE("check_distance","maxValue"),
        DICT_APP_MENU("app_menu","app_menu_hk_shenpi_id"),
        DICT_ONLINE_CUSTOMER_SERVICE("online_customer_service","online_customer_service_status"),
        DICT_API_VERSION("API-VERSION","min-version"),
        DICT_MSG_CHEAT_1("msg_cheat","use_action"),
        DICT_MSG_CHEAT_2("msg_cheat","msg_cheat_day"),
        DICT_MSG_CHEAT_3("msg_cheat","msg_cheat_minute"),
        DICT_MSG_CHEAT_4("msg_cheat","msg_cheat_rule"),
        DICT_MSG_CHEAT_5("msg_cheat","msg_verification_rule"),
        DICT_TIMEOUTINTERVAL("timeoutInterval","timeoutInterval"),
        DICT_LAST_REG_DAY("last_reg_day","1"),
        DICT_ROBOT("robot","robot_heart_beat_num"),
        DICT_APPCACHE_1("appCache","appCacheExpirAt"),
        DICT_APPCACHE_2("appCache","appSkipCacheExpirAt"),
        DICT_APPCACHE_3("appCache","areaCache"),
        DICT_SWITCH_1("switch","netty_switch"),
        DICT_SWITCH_2("switch","netty_sso_switch"),
        DICT_SWITCH_3("switch","gift_switch"),
        DICT_SWITCH_4("switch","show_sign_in_switch"),
        DICT_SWITCH_5("switch","solr_switch_for_hotcycle"),
        DICT_SWITCH_6("switch","clearAndroidPackSwitch"),
        DICT_SWITCH_7("switch","autoPay_propertyFee_switch"),
        DICT_SWITCH_8("switch","payment_one_net_switch"),//close:关闭除北京市意外其他城市一网通，open：放开所有城市 （临时开关）
        DICT_SWITCH_9("switch","collect_log_switch"),//日志采集开关
        DICT_NOTIFY_TYPES("notify_types","notify_types"),
        DICT_PROJECT("project","project_notice_title"),
        DICT_NEIGHBOR_PUBLISH_TITLE("neighbor_publish_title","neighbor_publish_title"),
        DICT_WATER_MARK_CONFIG_1("water_mark_config","water_mark_goods_switch"),
        DICT_WATER_MARK_CONFIG_2("water_mark_config","water_mark_goods_list"),
        DICT_WATER_MARK_CONFIG_3("water_mark_config","water_mark_goods_detail"),
        DICT_PROJECT_INDEX("project_index","project_index_notic_img"),
        DICT_APP_TEST("app_test","app_test_status"),
        DICT_FAMILY_STATUS("family_status",""),
        DICT_PRODUCT_NO("product_no",""),
        DICT_WORKING_TIME("working_time",""),
        DICT_NOTIFY_IMG("notify_img",""),
        DICT_NOTIFY_USER("notify_user",""),
        DICT_HK_INDENTITY("hk_indentity",""),
        DICT_PROJECT_DELIVERY("project_delivery",""),
        DICT_AFTER_SALES_TYPE("newsell_aftersales_type",""),
        DICT_AFTER_SALES_REASON("newsell_aftersales_reason",""),
        DICT_NEWSELL_INDEX_URL("newsell_urls","index_url");

        private  String groupName;

        private  String dictKey;

        // 构造函数，枚举类型只能为私有
        private Dict_K_V_Enum(String groupName, String dictKey) {
            this.groupName = groupName;
            this.dictKey = dictKey;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getDictKey() {
            return dictKey;
        }

        public void setDictKey(String dictKey) {
            this.dictKey = dictKey;
        }
    }


    //千丁搜索类型
    public static enum SearchTypeEnum {

        SEARCH_ALL(1, "全部"),
        SEARCH_LG(2, "乐购商品"),
        SEARCH_SERVICE(3, "服务"),
        SEARCH_LV(4, "旅游"),
        SEARCH_OTHER(5, "旅游");

        private  Integer code;

        private  String description;

        // 构造函数，枚举类型只能为私有
        private SearchTypeEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}