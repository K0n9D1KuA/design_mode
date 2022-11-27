package com.example.qyh;

//这里可以添加一个@Maaper注解 后面包扫描就可以将
//批量生成代理类
public interface IUserDao {

    @Select("select * from user where id = #{id}")
    String queryUserInfo(String id);
}
