package com.zq.test;

import com.zq.po.User;
import com.zq.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.sql.Select;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author zq
 * @create 2018-10-24 9:47
 */
public class HibernateTest3 {
    /**
     * 查询所有降序查询
     */
    @Test
    public void test1() {
        Session currentSession = HibernateSessionFactory.openSession();
        Query users = currentSession.createQuery("from User order by user_id desc ");
        List<User> list = users.list();
        System.out.println(list);
//        for (User o : list) {
//            System.out.println(o);
//
//
//        }

    }

    /**
     * 普通的hql查询,好像不是很好用了,现在都用起别名查询
     */
    @Test
    public void test2() {
        Session session = HibernateSessionFactory.openSession();
        Query query = session.createQuery("from User where user_code=?0 and user_name=?1");
        query.setParameter(0, "user1");
        query.setParameter(1, "张三");
        List<User> list = query.list();

        System.out.println(list);
    }

    /**
     * 起别名查询
     */
    @Test
    public void test3() {
        Session session = HibernateSessionFactory.openSession();
        Query query = session.createQuery("from User where user_code=:aaa and user_name=:bbb");
        query.setParameter("aaa", "user1");
        query.setParameter("bbb", "李四");
        List list = query.list();
        System.out.println(list);

    }

    /**
     * 投影查询
     */
    @Test
    public void test4() {
        Session session = HibernateSessionFactory.openSession();
        //单个投影查询
        Query query = session.createQuery("select  u.user_name from User u");
        List<Object> list = query.list();
        System.out.println(list);
        //多个投影查询
        Query query3 = session.createQuery("select  u.user_name,u.user_code from User u");
        List<Object[]> list2 = query3.list();
        for (Object[] objects : list2) {
            System.out.println(Arrays.toString(objects));
        }

        /**
         * 查询多个属性封装到对象中,话说还不知道这用到那里去
         */
        Query query2 = session.createQuery("select new User(u.user_name,u.user_code)from User u");
        List<User> list1 = query2.list();
        for (User users : list1) {
            System.out.println(users);
        }


    }

    /**
     * 分页查询
     */
    @Test
    public void test7() {
        Session session = HibernateSessionFactory.openSession();
        Query from_user = session.createQuery("from User");
        from_user.setFirstResult(0);//从第零个开始查
        from_user.setMaxResults(2);//一次查两个
        List list = from_user.list();
        System.out.println(list);

    }

    /**
     * 统计查询
     */
    @Test
    public void test8() {
        Session session = HibernateSessionFactory.openSession();
        Query from_user = session.createQuery(" select count (*) from User");
        Object o = from_user.uniqueResult();
        System.out.println(o);
        /**
         * 分组查询
         */
        Query query = session.createQuery("select u.user_code,count(*) from User u group by u.user_code");
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }


    }

    /**
     *  多表查询
     */
    @Test
    public void test9() {
        //普通内连接
        Session session = HibernateSessionFactory.openSession();
        Query from_user_inner_join_role_ =session.createQuery("from User u inner join u.roles");

        List<Object[]> list = from_user_inner_join_role_.list();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

    }
    @Test
    public void test10() {
        //fetch迫切内连接,直接把查出来的role放到user对象中去
        Session session = HibernateSessionFactory.openSession();
        Query from_user_inner_join_role_ =session.createQuery("from User u inner join fetch u.roles");
        List<User> list = from_user_inner_join_role_.list();
        System.out.println(list);

    }
}
