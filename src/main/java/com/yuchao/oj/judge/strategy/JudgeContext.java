package com.yuchao.oj.judge.strategy;

import com.yuchao.oj.model.dto.question.JudgeCase;
import com.yuchao.oj.judge.codesandbox.model.JudgeInfo;
import com.yuchao.oj.model.entity.Question;
import com.yuchao.oj.model.entity.QuestionSubmit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 判题上下文
 * @author 蒙宇潮
 * @create 2023-09-09  0:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeContext {

    /**
     * 判题返回的信息
     */
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    /**
     * 判题的所用测试用例
     */
    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
