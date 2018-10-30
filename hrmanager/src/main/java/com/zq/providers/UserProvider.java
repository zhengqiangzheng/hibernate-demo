package com.zq.providers;

import com.zq.querys.UserQuery;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author zq
 * @create 2018-10-30 0:02
 * 用来返回多条件查询语句
 */

public class UserProvider {
    /**
     *
     *select id,user_name as userName,user_pwd as userPwd  from t_user
     * where user_name like concat('%',#{userName},'%') and user_pwd=#{userPwd}
     *
     * @return
     */
    public String queryUsersByParams(UserQuery query) {
        return new SQL(){{
            SELECT("id,username as userName,password as userPwd");
            FROM("user");
            //LEFT_OUTER_JOIN("t_account on ")
            WHERE("1=1");
            if(null !=query.getUserName() && !("".equals(query.getUserName()))){
                WHERE("  username like concat('%',#{userName},'%') ");
            }
            if(null !=query.getUserPwd() && !("".equals(query.getUserPwd()))){
                WHERE(" password like concat('%',#{password},'%') ");
            }
        }}.toString();
    }



}
