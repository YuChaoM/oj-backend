package com.yuchao.oj.judge.codesandbox.impl;

import com.yuchao.oj.judge.codesandbox.CodeSandBox;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author 蒙宇潮
 * @create 2023-09-08  21:33
 */
public class ThirdPartyCodeSandbox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("调用远程代码沙箱");
        return null;
    }

}
