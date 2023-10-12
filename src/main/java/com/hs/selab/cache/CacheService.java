package com.hs.selab.cache;

import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public interface CacheService {
    @Nullable
    <T> T getOrNull(Cache<T> cache);

    <T> T get(Cache<T> cache, Callable<T> callable);

    <T> void set(Cache<T> cache, T data);

    <T> Long increment(Cache<T> cache);

    <T> Long decrement(Cache<T> cache);

    <T> void delete(Cache<T> cache);

    default <T> void asyncSet(Cache<T> cache, T data) {
        CompletableFuture.runAsync(() -> set(cache, data));
    }

    default <T> void asyncDelete(Cache<T> cache) {
        CompletableFuture.runAsync(() -> delete(cache));
    }
}
