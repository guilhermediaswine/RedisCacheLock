package com.roadster.rediscachelock.redis;

import com.roadster.rediscachelock.config.RedissonSpringDataConfig;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RedisProfileLocks implements TokenLocks {

    private RedissonSpringDataConfig redissonClient;

    @Override
    public RedisProfileLock get(String token) {
        return new RedisProfileLock(
                this.redissonClient.redisson(),
                token);
    }

}
