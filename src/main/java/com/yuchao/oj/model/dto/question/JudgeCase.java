package com.yuchao.oj.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 * @author 蒙宇潮
 * @create 2023-09-06  8:32
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private  String output;

}
