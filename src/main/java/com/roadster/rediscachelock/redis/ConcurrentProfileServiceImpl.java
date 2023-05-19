package com.roadster.rediscachelock.redis;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConcurrentProfileServiceImpl {

    EnableControlProfileLocks locks;

    private TokenLock getLock(String token) {
        return this.locks.get(token);
    }

    public void main(String key){
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
