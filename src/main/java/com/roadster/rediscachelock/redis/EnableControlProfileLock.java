package com.roadster.rediscachelock.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class EnableControlProfileLock implements TokenLock {

    private final TokenLock origin;

    public EnableControlProfileLock(TokenLock tokenLock) {
        this.origin = tokenLock;
    }

    @Override
    public void release() {
        this.origin.release();
    }

    @Override
    public boolean lock() {
        return this.origin.lock();

    }
}
