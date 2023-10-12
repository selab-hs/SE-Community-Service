package com.hs.selab.cache;

import com.hs.selab.common.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

@Service
@RequiredArgsConstructor
public class RedisCacheService implements CacheService {
    private final StringRedisTemplate redisTemplate;

    public <T> T getOrNull(Cache<T> cache) {
        var data = redisTemplate.opsForValue().get(cache.getKey());
        return (data != null) ? MapperUtil.readValue(data, cache.getType()) : null;
    }

    @SneakyThrows
    public <T> T get(Cache<T> cache, Callable<T> callable) {
        var data = redisTemplate.opsForValue().get(cache.getKey());

        if (data == null) {
            var dataObject = callable.call();

            asyncSet(cache, dataObject);

            return dataObject;
        } else {
            return MapperUtil.readValue(data, cache.getType());
        }
    }

    public <T> void set(Cache<T> cache, T data) {
        redisTemplate.opsForValue().set(
                cache.getKey(),
                MapperUtil.writeValueAsString(data),
                cache.getDuration()
        );
    }

    public <T> void delete(Cache<T> cache) {
        redisTemplate.delete(cache.getKey());
    }
    
    public <T> Long increment(Cache<T> cache) {
        return redisTemplate.opsForValue().increment(cache.getKey());
    }

    public <T> Long decrement(Cache<T> cache) {
        return redisTemplate.opsForValue().decrement(cache.getKey());
    }
}
