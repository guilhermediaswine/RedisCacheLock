package com.roadster.rediscachelock.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;


@Getter
@Setter
@AllArgsConstructor
public class RedisProfileLock implements TokenLock {

    private RedissonClient redissonClient;

    private String key;

    public boolean lock() {
        try {
            return redisLock().tryLock(
                    0,
                    100L,//could come from a property
                    TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("An error occurred while trying to get the lock!");
        }
        return true;
    }

    public void release() {
        try {
            redisLock().unlock();
        } catch (Exception e) {
            System.out.println("An error occurred when trying to release the lock!");
        }
    }


    private RLock redisLock() {
        return this.redissonClient.getLock(this.key);
    }

}
