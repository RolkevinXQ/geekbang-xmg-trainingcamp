package com.rolkevin.cache.lettuce;

import com.rolkevin.cache.AbstractCacheManager;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.codec.RedisCodec;

import javax.cache.Cache;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Properties;

public class LettuceCacheManager extends AbstractCacheManager {

    private final RedisClient redisClient;

    public LettuceCacheManager(CachingProvider cachingProvider, URI uri, ClassLoader classLoader, Properties properties) {
        super(cachingProvider, uri, classLoader, properties);
        redisClient = RedisClient.create(RedisURI.create(uri));

    }

    @Override
    protected <K, V, C extends Configuration<K, V>> Cache doCreateCache(String cacheName, C configuration) {

        return new LettuceCache(this,cacheName,configuration,redisClient.connect(new RedisCodec<K, V>() {
            @Override
            public K decodeKey(ByteBuffer byteBuffer) {
                return null;
            }

            @Override
            public V decodeValue(ByteBuffer byteBuffer) {
                return null;
            }

            @Override
            public ByteBuffer encodeKey(K k) {
                return null;
            }

            @Override
            public ByteBuffer encodeValue(V v) {
                return null;
            }
        }));
    }
}
