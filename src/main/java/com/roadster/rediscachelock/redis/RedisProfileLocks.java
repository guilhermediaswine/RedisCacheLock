package com.roadster.rediscachelock.redis;

import com.roadster.rediscachelock.config.RedissonSpringDataConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
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
