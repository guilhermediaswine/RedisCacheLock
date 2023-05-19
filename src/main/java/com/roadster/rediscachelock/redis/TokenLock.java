package com.roadster.rediscachelock.redis;

public interface TokenLock {

    void release();

    boolean lock();

}
