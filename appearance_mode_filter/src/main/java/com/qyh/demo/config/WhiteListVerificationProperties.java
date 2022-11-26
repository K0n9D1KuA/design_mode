package com.qyh.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 配置类
 * @date 2022/11/27 1:06
 */

@ConfigurationProperties("kongdekua.door")
public class WhiteListVerificationProperties {

    private String userStr;

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }

}
