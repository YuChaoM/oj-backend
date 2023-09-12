package com.yuchao.oj.model.dto.question;

import lombok.Data;

/**
 * @author 蒙宇潮
 * @create 2023-09-06  8:33
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制 (ms)
     */
    private Integer timeLimit;

    /**
     * 内存限制 （kb）
     */
    private Integer memoryLimit;

    /**
     * 堆栈限制（kb）
     */
    private Long stackLimit;
}
