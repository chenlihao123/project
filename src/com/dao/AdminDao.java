package com.dao;

import com.entity.Admin;

/**
 * @author chenlihao
 * @create 2020-12-23 22:56
 */
public interface AdminDao {
    //登录
    Admin QueryAdminByUsernameAndPassword(String username,String password);

}
