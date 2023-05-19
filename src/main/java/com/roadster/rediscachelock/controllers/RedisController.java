package com.roadster.rediscachelock.controllers;

import com.roadster.rediscachelock.services.RedisCache;
import com.roadster.rediscachelock.services.RedisLock;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping
public class RedisController {

    RedisCache redisCache;

    RedisLock redisLock;

    @GetMapping(value = "/redis")
    public void redis() {
        redisCache.save("key", "testing");
        redisCache.update("key", "testing2");
        redisCache.update("key2", "testing22");
        System.out.println(redisCache.get("key") + " - " + redisCache.get("key2"));
    }

    //to test this functionality, make two requests to the same route at the same time
    @GetMapping(value = "/lock")
    public void lock() {
        redisLock.doSomething("key");//the lock is based on a key, it can be customer id, email, any string key

    }
}
