package com.zq.test;

import com.zq.po.Role;
import com.zq.po.User;
import com.zq.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @author zq
 * @create 2018-10-24 0:29
 */
public class HibernateTest2 {

    @Test
    public void test1() {
        Session currentSession = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user1 = new User();
        user1.setUser_name("user1");
        User user2 = new User();
        user2.setUser_name("user2");

        Role role1 = new Role();
        role1.setRole_name("role1");
        Role role2 = new Role();
        role2.setRole_name("role2");
        Role role3 = new Role();
        role3.setRole_name("role3");

        //单项维护
        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        user2.getRoles().add(role3);

        role1.getUsers().add(user1);
        role2.getUsers().add(user1);
        role3.getUsers().add(user2);


        //保存,由于没有设置级联保存就全部保存一下吧
        currentSession.save(user1);
        currentSession.save(user2);//设置级联操作下面就可以注释掉了,不设置会报瞬时异常
//        currentSession.save(role1);
//        currentSession.save(role2);
//        currentSession.save(role3);

        transaction.commit();
    }
    @Test
    public void test3() {
        Session currentSession = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user1 = currentSession.get(User.class, 1l);
        Role role3 = currentSession.get(Role.class, 3l);
        user1.getRoles().add(role3);//单项维护,也可以使用双向维护,因为我们已经让角色放弃了外检维护



        transaction.commit();
    }

    /**
     * 修改角色
     */
    @Test
    public void test4() {
        Session currentSession = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        //将用户二的角色由三改成1
        User user = currentSession.get(User.class, 2l);
        Role role3 = currentSession.get(Role.class, 3l);
        Role role1 = currentSession.get(Role.class, 1l);
        //先删除角色3
        user.getRoles().remove(role3);
        //在添加角色1
        user.getRoles().add(role1);
        //多对多角色和用户之间的操作就是他们中的集合的几个操作



        transaction.commit();

    }
    @Test
    public void test5() {
        Session currentSession = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
//        User user = currentSession.get(User.class, 1l);
        User u = currentSession.load(User.class, 1l);
        System.out.println(u.getUser_id());

//        System.out.println(user);
        transaction.commit();


    }
}
