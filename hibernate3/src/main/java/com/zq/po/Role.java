package com.zq.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zq
 * @create 2018-10-24 0:09
 */
@Getter@Setter
public class Role {
    private Long role_id;
    private String role_name;
    private String role_memo;
    private Set<User> users = new HashSet<>();

}
