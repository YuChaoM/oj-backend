package com.yuchao.oj.judge.codesandbox.impl;

import com.yuchao.oj.judge.codesandbox.model.JudgeInfo;

import java.util.List;

import com.yuchao.oj.judge.codesandbox.CodeSandBox;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yuchao.oj.model.enums.JudgeInfoMessageEnum;
import com.yuchao.oj.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 示例代码沙箱（仅为了跑通业务）
 *
 * @author 蒙宇潮
 * @create 2023-09-08  21:20
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandBox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutput(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.ACCEPT.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.Accepted.getValue());
        judgeInfo.setMemory(100l);
        judgeInfo.setTime(100l);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
