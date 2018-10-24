package com.zq.test;

import com.zq.po.User;
import com.zq.utils.HibernateSessionFactory;
import javafx.scene.shape.Circle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import javax.xml.ws.Dispatch;
import java.util.List;

/**
 * @author zq
 * @create 2018-10-24 15:40
 */
public class HibernateTest4Qbc {
    /**
     * 基本的qbc查询
     */
    @Test
    public void test1() {
        Session session = HibernateSessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
         criteria.addOrder(Order.desc("user_id"));//排序
        criteria.setFirstResult(0);
        criteria.setMaxResults(3);
        List<User> list = criteria.list();
        for (User user : list) {
            System.out.println(user);
        }

    }
    /**
     * qbc条件查询
     */
    @Test
    public void test2() {
        Session session = HibernateSessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("user_code", "user1"));//查询usercode为user1
        criteria.add(Restrictions.like("user_name", "张%"));//查询用户名以张开头的用户
        List<User> list = criteria.list();
        for (User user : list) {
            System.out.println(user);
        }

    }

    /**
     * 统计查询,criteria.setProjection(Projections.*());中可以写很多聚合函数
     */
    @Test
    public void test3() {
        Session session = HibernateSessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.setProjection(Projections.rowCount());
        Object o = criteria.uniqueResult();
        System.out.println(o);

    }
    /**
     * 离线查询
     */
    @Test
    public void test4() {
        //可以将条件在service层写好,作为参数传进来在绑定在session中这样成为离线
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.like("user_name","张%"));

        Session session = HibernateSessionFactory.openSession();
        Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
        List<User> list = executableCriteria.list();
        System.out.println(list);


    }


}
