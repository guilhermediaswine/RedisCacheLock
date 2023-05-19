package com.roadster.rediscachelock.redis;

import com.roadster.rediscachelock.config.RedissonSpringDataConfig;
import lombok.Getter;
import lombok.Setter;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RMapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
@Getter
@Setter
public abstract class DefaultRedisCache {

    private TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private long TIME_TO_LIVE = 30;
    private String localCacheMapName = "DEFAULT";

    @Autowired
    private RedissonSpringDataConfig redissonClient;

    private RMapCache<String, Object> rLocalCachedMap;


    protected RMapCache<String, Object> getRLocalCachedMap() {
        if (this.rLocalCachedMap == null) {
            return redissonClient.redisson().getMapCache(getLocalCacheMapName(), getLocalCachedMapOptions());
        }
        return this.rLocalCachedMap;
    }

    protected LocalCachedMapOptions<String, Object> getLocalCachedMapOptions() {
        return LocalCachedMapOptions.<String, Object>defaults().evictionPolicy(LocalCachedMapOptions.EvictionPolicy.LFU).cacheSize(0).timeToLive(0, TimeUnit.MINUTES);
    }

    public boolean save(String key, Object value) {
        return getRLocalCachedMap().fastPutIfAbsent(key, value, TIME_TO_LIVE, TIME_UNIT);
    }

    public boolean update(String key, Object value) {
        return getRLocalCachedMap().fastPut(key, value, TIME_TO_LIVE, TIME_UNIT);
    }

    public Object get(String key) {
        return getRLocalCachedMap().get(key);
    }

}
