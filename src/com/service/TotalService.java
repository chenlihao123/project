package com.service;

import com.entity.Total;

/**
 * @author chenlihao
 * @create 2020-12-23 22:11
 */
public interface TotalService {
    //获取Total
    Total queryTotal();
    //更新total
    boolean updateTotal(Total total);
}
