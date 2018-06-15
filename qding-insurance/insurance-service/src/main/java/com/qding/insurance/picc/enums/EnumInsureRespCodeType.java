package com.qding.insurance.picc.enums;

public enum EnumInsureRespCodeType {

	SUCCESS("200", "成功"), FAIL_WRONG_ERRORCODE("201", "报文通用属性ErrorCode的值不为00"), FAIL_WITHOUT_POLICYNO(
			"202", "报文节点PolicyNo为空"), FAIL_WRONG_SAVERESULT("203",
			"报文节点SaveResult的值不为00"), FAIL_NULL_RESULT("204", "调用第三方接口返回值为空");

    private String code;

    private String description;

    EnumInsureRespCodeType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static EnumInsureRespCodeType getEnum(String code) {
        for (EnumInsureRespCodeType enumRequestMessageType : EnumInsureRespCodeType.values()) {
            if (code == enumRequestMessageType.getCode()) {
                return enumRequestMessageType;
            }
        }
        return null;
    }
}




