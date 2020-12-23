package com.service.impl;

import com.dao.impl.TeacherDaoImpl;
import com.dao.impl.TotalDaoImpl;
import com.entity.Total;
import com.service.TotalService;

/**
 * @author chenlihao
 * @create 2020-12-23 22:11
 */
public class TotalServiceImpl implements TotalService {
    private TotalDaoImpl totalDao=new TotalDaoImpl();
    @Override
    public Total queryTotal() {
        return totalDao.queryTotal();
    }

    @Override
    public boolean updateTotal(Total total) {
        return totalDao.updateTotal(total);
    }
}
