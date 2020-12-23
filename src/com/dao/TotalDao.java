package com.dao;

import com.entity.Total;

/**
 * @author chenlihao
 * @create 2020-12-23 22:04
 */
public interface TotalDao {
    //获取Total
    Total queryTotal();
    //更新total
    boolean updateTotal(Total total);
}
