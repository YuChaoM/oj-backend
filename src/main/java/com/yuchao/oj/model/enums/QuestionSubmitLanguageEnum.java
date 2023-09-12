package com.yuchao.oj.model.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author 蒙宇潮
 * @create 2023-09-06  11:25
 */
public enum QuestionSubmitLanguageEnum {

    JAVA("java", "java"),
    CPLUSPLUS("cpp", "cpp"),
    GOLANG("go", "go");

    private final String text;
    private final String value;

    QuestionSubmitLanguageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据value获取枚举
     * @param value
     * @return
     */

    public static QuestionSubmitLanguageEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (QuestionSubmitLanguageEnum anEnum : QuestionSubmitLanguageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public final String getText() {
        return text;
    }

    public final String getValue() {
        return value;
    }
}
