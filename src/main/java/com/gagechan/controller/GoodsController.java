package com.gagechan.controller;

import com.gagechan.comment.ServerConfig;
import com.gagechan.model.Goods;
import com.gagechan.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ServerConfig serverConfig;

    private int sellCount = 0;

    @GetMapping("/goods/{id}/skill")
    public Boolean skill(@PathVariable Integer id) {
        Boolean res = goodsService.skill(id);
        if (res) {
            sellCount+=1;
        }
//        log.info("当前端口{}秒杀{}",serverConfig.getCurrentListenPort(), res?"成功":"失败");
        return res;
    }

    @GetMapping("/goods")
    public Integer findAll() {
        return sellCount;
    }

}
