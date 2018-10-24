package com.zq.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author zq
 * @create 2018-10-19 6:50
 */
public class HibernateSessionFactory {
    public  static  final SessionFactory sessionFactory ;//线程安全可以作为成员变量
    static {
        Configuration configuration = new Configuration().configure();
        sessionFactory=configuration.buildSessionFactory();

    }

    public static Session getCurrentSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;//线程不安全不能作为成员变量

    }
    public static Session openSession(){
        Session session = sessionFactory.openSession();
        return  session;
    }

}
