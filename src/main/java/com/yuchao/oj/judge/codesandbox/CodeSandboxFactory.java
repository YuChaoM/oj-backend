package com.yuchao.oj.judge.codesandbox;

import com.yuchao.oj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.yuchao.oj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.yuchao.oj.judge.codesandbox.impl.ThirdPartyCodeSandbox;
import org.dom4j.Branch;

/**
 * 代码沙箱工厂（根据传入的参数使用哪个代码沙箱实现）
 *
 * @author 蒙宇潮
 * @create 2023-09-08  21:46
 */
public class CodeSandboxFactory {

    /**
     * 创建代码沙箱实例
     *
     * @param type 沙箱类型
     * @return
     */
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
