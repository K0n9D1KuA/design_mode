package com.qyh.demo.config;

/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 服务类  可以将 1111,2222,aaa  多个用户名通过,分开得到每个userId
 * @date 2022/11/27 1:05
 */

public class WhiteListVerificationService {

    private String userStr;

    public WhiteListVerificationService(String userStr) {
        this.userStr = userStr;
    }
    //可以将 1111,2222,aaa  多个用户名通过,分开得到每个userId
    public String[] split(String separatorChar) {
        return this.userStr.split(separatorChar);
    }

}
