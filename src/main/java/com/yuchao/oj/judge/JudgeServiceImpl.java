package com.yuchao.oj.judge;

import cn.hutool.json.JSONUtil;
import com.yuchao.oj.common.ErrorCode;
import com.yuchao.oj.exception.BusinessException;
import com.yuchao.oj.judge.codesandbox.CodeSandBox;
import com.yuchao.oj.judge.codesandbox.CodeSandboxFactory;
import com.yuchao.oj.judge.codesandbox.CodeSandboxProxy;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yuchao.oj.judge.strategy.JudgeContext;
import com.yuchao.oj.model.dto.question.JudgeCase;
import com.yuchao.oj.judge.codesandbox.model.JudgeInfo;
import com.yuchao.oj.model.entity.Question;
import com.yuchao.oj.model.entity.QuestionSubmit;
import com.yuchao.oj.model.enums.QuestionSubmitStatusEnum;
import com.yuchao.oj.service.QuestionService;
import com.yuchao.oj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 蒙宇潮
 * @create 2023-09-08  22:25
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionSubmitService questionSubmitService;
    @Resource
    private QuestionService questionService;

    @Resource
    private JudgeManager judgeManager;


    @Value("${codesandbox.type}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {

        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目信息不存在");
        }
        // 2)如果题目提交状态不为等待中，就不用重复执行了
        int status = questionSubmit.getStatus();
        if (QuestionSubmitStatusEnum.WAITING.getValue() != status) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "判题中");
        }
        // 更改判题（题目提交）的状态为“判题中”，防止重复执行，也能让用户即时看到状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean b = questionSubmitService.updateById(questionSubmitUpdate);// 这个方法不会更新为null的字段
        if (!b) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        // 调用沙箱，获取到执行结果
        CodeSandBox codeSandBox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandBox);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();
        // 代码沙箱执行结果
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutput();
        JudgeInfo judgeInfoRes = executeCodeResponse.getJudgeInfo();
        //  5)根据沙箱的执行结果，设置题目的判题状态和信息judgeInfo
        JudgeContext judgeContext = JudgeContext.builder()
                .outputList(outputList)
                .inputList(inputList)
                .judgeInfo(judgeInfoRes)
                .judgeCaseList(judgeCaseList)
                .question(question)
                .questionSubmit(questionSubmit)
                .build();
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 更新数据库的状态
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.ACCEPT.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        b = questionSubmitService.updateById(questionSubmitUpdate);// 这个方法不会更新为null的字段
        if (!b) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionId);
        return questionSubmitResult;
    }
}
