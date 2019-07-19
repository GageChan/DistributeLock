package com.gagechan.service.Impl;

import com.gagechan.model.Goods;
import com.gagechan.repository.GoodsRepository;
import com.gagechan.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    private static final Integer MIN_STOCK_NUM = 0;

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * 秒杀
     *
     * @param id
     * @return
     */
    @Override
    public Boolean skill(Integer id) {

        Optional<Goods> goodsOptional = goodsRepository.findById(id);
        Goods goods = goodsOptional.orElse(null);
        if (goods == null) {
            log.error("秒杀商品{}不存在", id);
            return false;
        }
        if (goods.getStock().compareTo(MIN_STOCK_NUM) <= 0) {
            log.error("秒杀商品{}已售完", id);
            return false;
        }
        goods.setStock(goodsRepository.findById(id).orElse(null).getStock() - 1);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goodsRepository.save(goods);
        return true;

    }

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }
}
