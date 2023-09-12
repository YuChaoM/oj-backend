package com.yuchao.oj.judge;

import com.yuchao.oj.judge.strategy.DefaultJudgeStrategy;
import com.yuchao.oj.judge.strategy.JavaLanguageJudgeStrategy;
import com.yuchao.oj.judge.strategy.JudgeContext;
import com.yuchao.oj.judge.strategy.JudgeStrategy;
import com.yuchao.oj.judge.codesandbox.model.JudgeInfo;
import com.yuchao.oj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理简化调用
 *
 * @author 蒙宇潮
 * @create 2023-09-09  10:47
 */
@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
