package com.upfor.grover.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.upfor.grover.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CaffeineCacheConfig {

    /**
     * 一个缓存池的配置与实例初始化
     * <p>
     * 引入实例:
     * <pre>@Resource</pre>
     * <pre>Cache<Long, String> userCacheById</pre>
     */
    @Bean
    public Cache<Long, UserEntity> userCacheById() {
        return Caffeine.newBuilder()
                // 写入N秒后强制过期
                .expireAfterWrite(Duration.ofSeconds(300))
                // 最多存N个KEY
                .maximumSize(1000)
                .build();
    }

}
