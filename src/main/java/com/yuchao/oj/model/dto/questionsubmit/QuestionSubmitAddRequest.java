package com.yuchao.oj.model.dto.questionsubmit;

import lombok.Data;

/**
 * @author 蒙宇潮
 * @create 2023-09-06  9:48
 */
@Data
public class QuestionSubmitAddRequest {
    /**
     * 题目 id
     */
    private Long questionId;


    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;
}
