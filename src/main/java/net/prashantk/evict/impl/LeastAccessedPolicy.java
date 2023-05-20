package net.prashantk.evict.impl;

import net.prashantk.entity.CacheRecord;
import net.prashantk.evict.EvictionPolicy;

import java.util.Comparator;

public class LeastAccessedPolicy<K, V> implements EvictionPolicy<K, V> {
    @Override
    public Comparator<CacheRecord<K, V>> get() {
        return Comparator.comparing(CacheRecord::getAccessCount);
    }
}
