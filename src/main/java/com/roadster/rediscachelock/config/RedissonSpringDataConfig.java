package com.roadster.rediscachelock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class RedissonSpringDataConfig {

    Resource configFile;

    public RedissonSpringDataConfig(@Value("classpath:/redisson.yaml") Resource configFile) {
        this.configFile = configFile;
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        Config config = null;
        try {
            config = Config.fromYAML(this.configFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Redisson.create(config);
    }

}