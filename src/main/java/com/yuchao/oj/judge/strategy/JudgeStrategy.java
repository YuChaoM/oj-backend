package com.yuchao.oj.judge.strategy;

import com.yuchao.oj.judge.codesandbox.model.JudgeInfo;

/**
 * 判题策略
 * @author 蒙宇潮
 * @create 2023-09-09  10:09
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
