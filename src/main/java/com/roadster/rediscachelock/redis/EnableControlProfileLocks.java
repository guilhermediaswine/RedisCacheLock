package com.roadster.rediscachelock.redis;

import com.roadster.rediscachelock.config.RedissonSpringDataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnableControlProfileLocks implements TokenLocks {

    @Autowired
    private  RedisProfileLocks origin;

    public EnableControlProfileLock get(String token) {
        return new EnableControlProfileLock(this.origin.get(token));
    }
}
