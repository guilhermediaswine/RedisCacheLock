package com.roadster.rediscachelock.redis;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TesteRedis extends DefaultRedisCache {

    public TesteRedis() {
        super();
        setLocalCacheMapName("TESTE");
        setTIME_UNIT(TimeUnit.SECONDS);
        setTIME_TO_LIVE(60L);
    }
}
