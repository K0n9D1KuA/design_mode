package com.qyh.demo.controller;

import com.qyh.demo.annotation.MyDoor;
import com.qyh.demo.domain.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class testController {
    @Value("${server.port}")
    private int port;

    /**
     * @DoDoor 自定义注解
     * key：需要从入参取值的属性字段，如果是对象则从对象中取值，如果是单个值则直接使用
     * returnJson：预设拦截时返回值，是返回对象的Json
     * <p>
     * http://localhost:8080/api/queryUserInfo?userId=1001
     * http://localhost:8080/api/queryUserInfo?userId=小团团
     */
    @MyDoor(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"" +
            ",\"age\":\"99999999\"}")
    @RequestMapping(path = "/api/queryUserInfo/{qqNumber}", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId, @PathVariable String qqNumber) {
        return new UserInfo("kongdekua:" + userId, 19, "浙江省杭州市");
    }

}
