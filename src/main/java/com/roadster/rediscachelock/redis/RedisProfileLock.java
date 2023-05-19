package com.roadster.rediscachelock.redis;

import com.roadster.rediscachelock.config.RedissonSpringDataConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;


@Getter
@Setter
public class RedisProfileLock implements TokenLock {

    private RedissonClient redissonClient;

    private String key;

    public RedisProfileLock(RedissonClient redissonClient, String token) {
        this.redissonClient = redissonClient;
        this.key = token;
    }

    public boolean lock() {
        try {
            return redisLock().tryLock(
                    0,
                    100L,
                    TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Um erro aconteceu ao tentar obter o lock ");
        }

        return true;
    }


    public void release() {
        try {
            redisLock().unlock();
        } catch (Exception e) {
            System.out.println("Um erro aconteceu ao tentar dar o release do lock ");
        }
    }


    private RLock redisLock() {
        return this.redissonClient.getLock(this.key);
    }

}
