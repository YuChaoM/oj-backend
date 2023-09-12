package com.yuchao.oj.judge;

/**
 * @author 蒙宇潮
 * @create 2023-09-08  22:23
 */

import com.yuchao.oj.model.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {

    QuestionSubmit doJudge(long questionId);
}
