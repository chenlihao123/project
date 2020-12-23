package com.dao.impl;

import com.dao.BaseDao;
import com.dao.TotalDao;
import com.entity.Total;

/**
 * @author chenlihao
 * @create 2020-12-23 22:06
 */
public class TotalDaoImpl extends BaseDao implements TotalDao {

    @Override
    public Total queryTotal() {
        String sql="select * from total";
        return queryForOne(Total.class,sql);
    }

    @Override
    public boolean updateTotal(Total total) {
        String sql="update total set visitTotal = ?,watchTotal=?";
        return update(sql,total.getVisitTotal(),total.getWatchTotal())>=0?true:false;
    }
}
