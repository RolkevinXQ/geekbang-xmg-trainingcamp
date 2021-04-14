package com.rolkevin.cache.lettuce;

import com.rolkevin.cache.AbstractCache;
import com.rolkevin.cache.ExpirableEntry;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
import java.io.Serializable;
import java.util.Iterator;

public class LettuceCache<K extends Serializable,V extends Serializable> extends AbstractCache<K, V> {

    private final StatefulRedisConnection<K, V> connect;
    private final RedisCommands<K,V> redisCommands;
    protected LettuceCache(CacheManager cacheManager,
                           String cacheName,
                           Configuration configuration,
                           StatefulConnection<K,V> connection) {
        super(cacheManager, cacheName, configuration);
        connect = (StatefulRedisConnection<K, V>) connection;
        redisCommands = connect.sync();
        //redisCommands.auth("");
    }

    @Override
    protected boolean containsEntry(K key) throws CacheException, ClassCastException {
        return redisCommands.exists(key) > 0;
    }

    @Override
    protected ExpirableEntry<K, V> getEntry(K key) throws CacheException, ClassCastException {
        V value = redisCommands.get(key);
        return ExpirableEntry.of(key,value);
        //return redisCommands.get(key);
    }

    /*protected ExpirableEntry<K, V> getEntry(byte[] keyBytes) throws CacheException, ClassCastException {
        byte[] valueBytes = jedis.get(keyBytes);
        return ExpirableEntry.of(deserialize(keyBytes), deserialize(valueBytes));
    }*/

    /**
     * Put the {@link Entry} into cache.
     *
     * @param newEntry The new instance of {@link Entry<K,V>} is created by {@link Cache}
     * @throws CacheException
     * @throws ClassCastException
     */
    @Override
    protected void putEntry(ExpirableEntry<K, V> newEntry) throws CacheException, ClassCastException {
        redisCommands.set(newEntry.getKey(),newEntry.getValue());
    }

    @Override
    protected ExpirableEntry<K, V> removeEntry(K key) throws CacheException, ClassCastException {
        ExpirableEntry<K,V> oldEntry = getEntry(key);
        redisCommands.del(key);
        return oldEntry;
    }

    @Override
    protected void doClear() throws CacheException {

    }

    @Override
    protected Iterator<Entry<K, V>> newIterator() {
        return null;
    }
}
