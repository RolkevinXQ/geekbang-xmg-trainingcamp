package com.rolkevin.cache.lettuce;

import com.rolkevin.cache.AbstractCache;
import com.rolkevin.cache.ExpirableEntry;

import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
import java.util.Iterator;

public class LettuceCache extends AbstractCache {


    protected LettuceCache(CacheManager cacheManager, String cacheName, Configuration configuration) {
        super(cacheManager, cacheName, configuration);
    }

    @Override
    protected boolean containsEntry(Object key) throws CacheException, ClassCastException {
        return false;
    }

    @Override
    protected ExpirableEntry getEntry(Object key) throws CacheException, ClassCastException {
        return null;
    }

    /**
     * Put the {@link Entry} into cache.
     *
     * @param newEntry The new instance of {@link Entry<K,V>} is created by {@link Cache}
     * @throws CacheException
     * @throws ClassCastException
     */
    @Override
    protected void putEntry(ExpirableEntry newEntry) throws CacheException, ClassCastException {

    }

    @Override
    protected ExpirableEntry removeEntry(Object key) throws CacheException, ClassCastException {
        return null;
    }

    @Override
    protected void doClear() throws CacheException {

    }

    @Override
    protected Iterator<Entry> newIterator() {
        return null;
    }
}
