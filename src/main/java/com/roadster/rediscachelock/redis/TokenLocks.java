package com.roadster.rediscachelock.redis;

public interface TokenLocks {
    TokenLock get(String token);
}

