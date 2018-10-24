package com.zq.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zq
 * @create 2018-10-21 20:46
 */
@Getter
@Setter
public class LinkMan {
    private Long   link_id;
    private String link_name;
    private String link_gender;
    private String link_phone;
    private String link_mobile;
    private String link_email;
    private String link_qq;
    private String link_position;
    private String link_memo;
    private String link_cust_id;
    //一个联系人只对应一个客户
    private Customer customer;

    @Override
    public String toString() {
        return "Linkman{" +
                "link_id=" + link_id +
                ", link_name='" + link_name + '\'' +
                ", link_gender='" + link_gender + '\'' +
                ", link_phone='" + link_phone + '\'' +
                ", link_mobile='" + link_mobile + '\'' +
                ", link_email='" + link_email + '\'' +
                ", link_qq='" + link_qq + '\'' +
                ", link_position='" + link_position + '\'' +
                ", link_memo='" + link_memo + '\'' +
                ", link_cust_id='" + link_cust_id + '\'' +
                ", customer=" + customer +
                '}';
    }
}
