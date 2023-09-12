package com.yuchao.oj.model.dto.questionsubmit;

import com.yuchao.oj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author 蒙宇潮
 * @create 2023-09-06  15:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 3766360709117384258L;
    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 判题状态(0-待判题 1-判题中 2-成功 3-失败)
     */
    private Integer status;

}
