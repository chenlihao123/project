package com.dao.impl;

import com.dao.AdminDao;
import com.dao.BaseDao;
import com.entity.Admin;

/**
 * @author chenlihao
 * @create 2020-12-23 22:55
 */
public class AdminDaoImpl extends BaseDao implements AdminDao {

    @Override
    public Admin QueryAdminByUsernameAndPassword(String username, String password) {
        String sql="select * from admin where username = ? and password = ?";
        return queryForOne(Admin.class,sql,username,password);
    }

}
