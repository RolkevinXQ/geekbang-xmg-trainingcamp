package com.rolkevin.cache.lettuce;

import com.rolkevin.cache.AbstractCacheManager;
import com.rolkevin.cache.codec.Codecs;
import com.rolkevin.cache.codec.GenericSerializer;
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

    private final Codecs codecs;
    public LettuceCacheManager(CachingProvider cachingProvider, URI uri, ClassLoader classLoader, Properties properties) {
        super(cachingProvider, uri, classLoader, properties);
        redisClient = RedisClient.create(RedisURI.create(uri));
        codecs = Codecs.newInstance();
    }

    @Override
    protected <K, V, C extends Configuration<K, V>> Cache doCreateCache(String cacheName, C configuration) {
        GenericSerializer keySerializer = codecs.targetSerializer(configuration.getKeyType());
        GenericSerializer valueSerializer = codecs.targetSerializer(configuration.getValueType());
        return new LettuceCache(this,cacheName,configuration,redisClient.connect(new RedisCodec<K, V>() {
            @Override
            public K decodeKey(ByteBuffer byteBuffer) {
                return (K)keySerializer.decode(byteBuffer);
            }

            @Override
            public V decodeValue(ByteBuffer byteBuffer) {
                return (V)valueSerializer.decode(byteBuffer);
            }

            @Override
            public ByteBuffer encodeKey(K k) {
                return keySerializer.encode(k);
            }

            @Override
            public ByteBuffer encodeValue(V v) {
                return valueSerializer.encode(v);
            }
        }));
    }
}
