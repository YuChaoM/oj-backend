package com.yuchao.oj.judge;

import com.yuchao.oj.judge.codesandbox.CodeSandBox;
import com.yuchao.oj.judge.codesandbox.CodeSandboxFactory;
import com.yuchao.oj.judge.codesandbox.CodeSandboxProxy;
import com.yuchao.oj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yuchao.oj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.util.Arrays;
import java.util.List;

/**
 * @author 蒙宇潮
 * @create 2023-09-08  21:36
 */
@SpringBootTest
public class CodeSandboxTest {

    @Value("${codesandbox.type}")
    private String type;

    @Test
    void executeCode() {
        CodeSandBox codeSandbox = new ExampleCodeSandbox();
        String code = "main {}";
        List<String> input = Arrays.asList("a", "b", "c");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(input)
                .language(QuestionSubmitLanguageEnum.JAVA.getValue())
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    void executeCodeByType() {
        CodeSandBox codeSandbox = CodeSandboxFactory.newInstance(type);
        String code = "main {}";
        List<String> input = Arrays.asList("a", "b", "c");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(input)
                .language(QuestionSubmitLanguageEnum.JAVA.getValue())
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    void executeCodeByProxy() {
        CodeSandBox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);
        String code = "main {}";
        List<String> input = Arrays.asList("a", "b", "c");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(input)
                .language(QuestionSubmitLanguageEnum.JAVA.getValue())
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }


}
