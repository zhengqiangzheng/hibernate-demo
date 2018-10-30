package com.zq.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zq.dao.UserDao;
import com.zq.po.User;
import com.zq.querys.UserQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zq
 * @create 2018-10-29 23:07
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User queryUserById(Integer userId) {
        return userDao.queryUserById(userId);

    }

    public Integer saveUser(User user) {
        return userDao.saveUser(user);
    }

    /**
     * 分页查询
     */
    public PageInfo<User> queryUsersByParams(UserQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());//启动分页
        List<User> list = userDao.queryUsersByParams(query);
        return new PageInfo<>(list);

    }

    public Integer delete(Integer userId) {
        return userDao.delete(userId);
    }

}
