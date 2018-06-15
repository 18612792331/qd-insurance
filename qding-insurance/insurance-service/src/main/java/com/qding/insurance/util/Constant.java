package com.qding.insurance.util;

import com.qding.framework.common.uuid.UUIDGenerator;

public final class Constant {

	public static final UUIDGenerator ID_GENERATOR = new UUIDGenerator("qding_insurance");

	public static final Integer YES = 1;
	public static final Integer NO = 0;
	public static final String YES_STRING = "1";
	public static final String NO_STRING = "0";

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	//保险业态
    public static final String PRODUCT_NO_BX = "BX";
    public static final String PRODUCT_NO_BX_NAME = "保险业态";

	/**
	 * 保险产品状态
	 */
	public static final Integer WARE_STATUS_UNAUDIT = 1; // 未审核
	public static final Integer WARE_STATUS_TOAUDIT = 2; // 审核中
	public static final Integer WARE_STATUS_AUDIT_FAIL = 3; // 审核未通过
	public static final Integer WARE_STATUS_AUDIT_OK = 4; // 审核通过
	public static final Integer WARE_STATUS_ONLINE = 5; // 已上架
	public static final Integer WARE_STATUS_OFFLINE = 6; // 已下架

	/**
	 * 保险订单状态
	 */
	public static final Integer ORDER_STATUS_UNAPAID = 1; // 未支付
	public static final Integer ORDER_STATUS_PAID = 2; // 已支付
	public static final Integer ORDER_STATUS_FINISHED = 3; // 已完成
	public static final Integer ORDER_STATUS_CANCELED = 4; // 已取消

	/**
	 * 社区类型
	 */
	public static final String PROJECT_TYPE_LONGFOR = "project_longfor"; // 龙湖社区
	public static final String PROJECT_TYPE_NOT_LONGFOR = "project_notlongfor"; // 龙湖社区

	/**
	 * 款式类型
	 */
	public static final String STYLE_TYPE_ONE = "style_1";
	public static final String STYLE_TYPE_TWO = "style_2";
	public static final String STYLE_TYPE_THREE = "style_3";

	/**
	 * 保障期限类型
	 */
	public static final String TIME_TYPE_ONE_YEAR = "time_1Y";
	public static final String TIME_TYPE_TWO_YEAR = "time_2Y";
	public static final String TIME_TYPE_THREE_YEAR = "time_3Y";
	/**
	 * 理赔记录状态 1：已锁定，2：已完成，3：已撤销 4.待授权
	 */
	public static final Integer COMPENSATERECORD_STATUS_LOCKED = 1; // 已锁定
	public static final Integer COMPENSATERECORD_STATUS_FINISHED = 2; // 已完成
	public static final Integer COMPENSATERECORD_STATUS_CANCELED = 3; // 已撤销
	public static final Integer COMPENSATERECORD_STATUS_UNAUTHOR = 4; // 待授权
	public static final Integer COMPENSATE_NEED_AUTHOR_MONEY=10000;//需授权的理赔金额
	
	/**
	 * 理赔方式
	 */
	public static final Integer COMPENSATERECORD_COMPENSATETYPE_MONEY = 1; // 按金钱
	public static final Integer COMPENSATERECORD_COMPENSATETYPE_TIME = 2; // 按次数

	/**
	 * 无限次数理赔
	 */
	public static final Integer COMPENSATERECORD_INFINITETIME = -1; // 无限次数
	/**
	 * 是否可以理赔
	 */
	public static final Boolean COMPENSATERECORD_CAN_CLAIM = true; // 可理赔
	public static final Boolean COMPENSATERECORD_CANT_CLAIM = false; // 不可理赔

	/**
	 * 保险单证状态 1：投保中，2：未生效，3：投保失败，4：已生效，5：已到期，6：已终止
	 */
	public static final Integer POLICY_STATUS_TOINSURE = 1; //投保中
	public static final Integer POLICY_STATUS_UNENFORCED = 2; //未生效
	public static final Integer POLICY_STATUS_FAILEDINSURE = 3; //投保失败
	public static final Integer POLICY_STATUS_ENFORCED = 4; //已生效
	public static final Integer POLICY_STATUS_WASDUE = 5; //已到期
	public static final Integer POLICY_STATUS_TERMINATED =6; //已终止
	
	/**
	 * 保险订单支付状态
	 */
	public static final Integer ORDER_PAY_STATUS_UNAPAID = 1; // 未支付
	public static final Integer ORDER_PAY_STATUS_PAID = 2; // 已支付
	public static final Integer ORDER_PAY_STATUS_FINISHED = 3; // 已退款
	/**
	 * 理赔记录同步状态
	 */
	public static final Integer COMPENSATERECORD_DATASTATUS_UNSYNC=1;//未同步
	public static final Integer COMPENSATERECORD_DATASTATUS_SYNCED=2;//已经同步
	/**
	 * 理赔记录-理赔性质
	 */
	public static final Integer COMPENSATE_PAID_TYPE_SELF=1;//自授权
	public static final Integer COMPENSATE_PAID_TYPE_PICC=2;//PICC授权
	public static final Integer COMPENSATE_PAID_TYPE_OUT=3;//外部理赔
	
	
    public static final String GUARANTEE_PLAN_UNLIMIT = "-1"; //无限权益标记
    
    /**
     * 理赔申请状态码
     */
    public static final String COMPENSATE_APPLY_RESULT_OK = "OK"; //可理赔
    public static final String COMPENSATE_APPLY_RESULT_REFUSE = "REFUSE"; //拒绝理赔
    public static final String COMPENSATE_APPLY_RESULT_WAITING_AUTH = "WAITING-AUTH"; //需授权

    /**
     * 保障对象属性
     */
    public static final Integer GUARANTEEITEM_ITEM_TYPE_SHOP = 1; //按商品
    public static final Integer GUARANTEEITEM_ITEM_TYPE_OUT = 2; //外部理赔



}
