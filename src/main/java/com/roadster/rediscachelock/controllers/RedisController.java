package com.roadster.rediscachelock.controllers;

import com.roadster.rediscachelock.redis.ConcurrentProfileServiceImpl;
import com.roadster.rediscachelock.redis.DefaultRedisCache;
import com.roadster.rediscachelock.redis.TesteRedis;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/redis")
public class RedisController {

    TesteRedis redisCache;

    ConcurrentProfileServiceImpl service;

    @GetMapping
    public void redis() {
        redisCache.save("teste", "tteste");
        redisCache.update("teste", "testes");
        redisCache.update("teste2", "teste2");
        System.out.println(redisCache.get("teste") + " - " + redisCache.get("teste2"));
    }

    @GetMapping(value = "/lock")
    public void lock(){
        service.main("vamo");

    }
}
