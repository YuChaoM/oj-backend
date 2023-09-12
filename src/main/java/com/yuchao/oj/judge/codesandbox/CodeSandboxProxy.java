package com.yuchao.oj.judge.codesandbox;

import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 蒙宇潮
 * @create 2023-09-08  22:00
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandBox {
    private final CodeSandBox codeSandBox;

    public CodeSandboxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息：{}", executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息：{}", executeCodeResponse);
        return executeCodeResponse;
    }
}
