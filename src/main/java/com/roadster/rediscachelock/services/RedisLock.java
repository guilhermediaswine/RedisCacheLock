package com.roadster.rediscachelock.services;

import com.roadster.rediscachelock.redis.RedisProfileLocks;
import com.roadster.rediscachelock.redis.TokenLock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RedisLock {

    RedisProfileLocks locks;

    private TokenLock getLock(String key) {
        return this.locks.get(key);
    }

    public void doSomething(String key){
        TokenLock lock = getLock(key);
        if(lock.lock()){
            try{
                System.out.println("Entrou");
                Thread.sleep(10000L);
                System.out.println("ta saindo");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.release();
            }
        }else {
            System.out.println("espera caraio");
        }
    }

}
