package net.prashantk.evict.impl;

import net.prashantk.evict.EvictionPolicy;
import net.prashantk.entity.CacheRecord;

import java.util.Comparator;

public class LRUEvictionPolicy<K, V> implements EvictionPolicy<K, V> {
    @Override
    public Comparator<CacheRecord<K, V>> get() {
        return Comparator.comparing(CacheRecord::getLastUsedAt);
    }
}
