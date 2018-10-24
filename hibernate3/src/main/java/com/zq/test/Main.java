package com.zq.test;

import org.junit.Test;

/**
 * @author zq
 * @create 2018-10-23 8:24
 */
public class Main {
   static int i=1;

    public static void main(String[] args) {
        test1();
        System.out.println(i);


    }

    public static void test1() {
        i++;
        i++;
        i=i++;
        i=i++;
        i=++i;
        int a =i;
        System.out.println(a);

    }

}
