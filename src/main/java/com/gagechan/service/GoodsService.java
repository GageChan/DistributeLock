package com.gagechan.service;

import com.gagechan.model.Goods;

import java.util.List;

public interface GoodsService {

    /**
     * 秒杀
     * @param id
     * @return
     */
    Boolean skill(Integer id);

    List<Goods> findAll();

}
