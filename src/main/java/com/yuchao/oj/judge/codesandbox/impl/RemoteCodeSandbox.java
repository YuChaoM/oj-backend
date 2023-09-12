package com.yuchao.oj.judge.codesandbox.impl;

import com.yuchao.oj.judge.codesandbox.CodeSandBox;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yuchao.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 调用远程代码沙箱实现
 * @author 蒙宇潮
 * @create 2023-09-08  21:30
 */
public class RemoteCodeSandbox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
