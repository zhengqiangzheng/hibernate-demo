package com.zq.dao;

import com.zq.po.User;
import com.zq.providers.UserProvider;
import com.zq.querys.UserQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zq
 * @create 2018-10-29 23:01
 */
@Mapper
public interface UserDao {
    @Select("select id,username as userName,password as userPwd from user where id =#{userId}")
    User queryUserById(Integer userId);
    @Insert("insert into user(username,password) values(#{userName},#{userPwd})")
    Integer saveUser(User user);
    //多条件查询使用下面的方法查询
    @SelectProvider(type = UserProvider.class,method = "queryUsersByParams")
    List<User> queryUsersByParams(UserQuery query);

    @Delete("delete from user where id=#{userId}")
    Integer delete(Integer userId);

}
