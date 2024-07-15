package com.upfor.grover.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.upfor.grover.entity.Config;
import com.upfor.grover.mapper.ConfigMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ConfigService extends ServiceImpl<ConfigMapper, Config> {

    /**
     * 不存在的配置，用此值在本地缓存中标记，避免缓存穿透
     */
    private static final String KEY_NOT_EXIST_VALUE = "|>-N-<|";

    /**
     * 配置值本地内存缓存
     */
    private final Cache<String, String> cacheByKey = CacheBuilder.newBuilder()
            .maximumSize(1000) // 最大缓存数量
            .expireAfterWrite(Duration.ofSeconds(60)) // 写入N秒后过期
            .build(new CacheLoader<String, String>() {
                @NotNull
                @Override
                public String load(@NotNull String key) {
                    Config config = getByKey(key);
                    return config == null ? KEY_NOT_EXIST_VALUE : config.getValue();
                }
            });

    /**
     * 根据key获取配置值（本地缓存优先）
     *
     * @param key 配置键
     * @return 配置值
     */
    public String getByKeyWithCache(String key) {
        // Key无效，返回null
        if (key == null) {
            return null;
        }

        // 从本地缓存获取配置
        String value = cacheByKey.getIfPresent(key);

        // 不存在的配置，返回null
        if (KEY_NOT_EXIST_VALUE.equals(value)) {
            return null;
        }

        return value;
    }

    /**
     * 根据key获取配置（从数据库获取）
     *
     * @param key 配置键
     * @return 配置
     */
    public Config getByKey(String key) {
        if (key == null) {
            return null;
        }

        return this.queryChain().eq(Config::getKey, key).one();
    }

}
