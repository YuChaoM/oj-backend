package com.yuchao.oj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.yuchao.oj.model.dto.question.JudgeCase;
import com.yuchao.oj.model.dto.question.JudgeConfig;
import com.yuchao.oj.judge.codesandbox.model.JudgeInfo;
import com.yuchao.oj.model.entity.Question;
import com.yuchao.oj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * java程序判题
 *
 * @author 蒙宇潮
 * @create 2023-09-09  10:43
 */
public class JavaLanguageJudgeStrategy implements JudgeStrategy {


    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        // 响应的judgeInfo
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);
        // 先判断沙箱执行的结果输出数量是否和预期输出数量相等
        if (inputList.size() != outputList.size()) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
            return judgeInfoResponse;
        }
        // 2.依次判断每一项输出和预期输出是否相等
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        for (int i = 0; i < outputList.size(); i++) {
            if (!outputList.get(i).equals(judgeCaseList.get(i).getOutput())) {
                judgeInfoResponse.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
                return judgeInfoResponse;
            }
        }
        // 3. 判题题目的限制是否符合要求
        Question question = judgeContext.getQuestion();
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Integer expectMemory = judgeConfig.getMemoryLimit();
        Integer expectTime = judgeConfig.getTimeLimit();
        if (memory > expectMemory) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.OUTPUT_LIMIT_EXCEEDED.getValue());
            return judgeInfoResponse;
        }
        // Java 程序本身需要额外执行 10 秒钟
        long JAVA_PROGRAM_TIME_COST = 10000L;
        if (time - JAVA_PROGRAM_TIME_COST > expectTime) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED.getValue());
            return judgeInfoResponse;
        }
        //  todo 可能还有其他的异常情况 ?
        judgeInfoResponse.setMessage(JudgeInfoMessageEnum.Accepted.getValue());
        return judgeInfoResponse;
    }
}
