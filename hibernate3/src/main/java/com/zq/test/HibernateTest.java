package com.zq.test;

import com.zq.po.Customer;
import com.zq.po.LinkMan;
import com.zq.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @author zq
 * @create 2018-10-22 0:36
 */
public class HibernateTest {
    @Test
    public void test1() {
        Session currentSession = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Customer customer1 = new Customer();
        customer1.setCust_name("customer1");
        Customer customer2 = new Customer();
        customer2.setCust_name("customer2");
        Customer customer3 = new Customer();
        customer3.setCust_name("customer3");

        LinkMan linkman1 = new LinkMan();
        linkman1.setLink_name("linkman1");
        LinkMan linkman2 = new LinkMan();
        linkman2.setLink_name("linkman2");
        LinkMan linkman3 = new LinkMan();
        linkman3.setLink_name("linkman3");

       linkman1.setCustomer(customer1);
       linkman2.setCustomer(customer1);
       linkman3.setCustomer(customer2);
//    customer1.getLinkmens().add(linkman1);
//   customer1.getLinkmens().add(linkman2);
//      customer2.getLinkmens().add(linkman3);

//       currentSession.save(customer1);
//       currentSession.save(customer2);
//       currentSession.save(customer3);
        currentSession.save(linkman1);
        currentSession.save(linkman2);
        currentSession.save(linkman3);

        transaction.commit();

    }
    @Test
    public void test2() {
        Session currentSession = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        LinkMan linkMan = currentSession.get(LinkMan.class, 9l);//hibernate中默认使用懒加载,这里不会把9号关联的顾客查出来,
        transaction.commit();
        System.out.println(linkMan.getCustomer().getCust_name());


    }

    @Test
    public void test3() {
        Session currentSession = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Customer customer = currentSession.get(Customer.class, 12l);
        currentSession.delete(customer);



        transaction.commit();
    }

    @Test
    public void test4() {
        Session currentSession = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        LinkMan linkMan = currentSession.get(LinkMan.class, 12l);
        Customer customer = currentSession.get(Customer.class, 15l);
        linkMan.setCustomer(customer);


        transaction.commit();

    }

}
