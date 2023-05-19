package com.roadster.rediscachelock.services;

import com.roadster.rediscachelock.redis.DefaultRedisCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCache extends DefaultRedisCache {

    public RedisCache() {
        super();
        setLocalCacheMapName("CACHE_REDIS_GENERIC");
        setTIME_UNIT(TimeUnit.SECONDS);
        setTIME_TO_LIVE(60L);
    }
}
