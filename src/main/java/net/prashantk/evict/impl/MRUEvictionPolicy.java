package net.prashantk.evict.impl;

import net.prashantk.evict.EvictionPolicy;
import net.prashantk.entity.CacheRecord;

import java.util.Comparator;

public class MRUEvictionPolicy<K, V> implements EvictionPolicy<K, V> {
    @Override
    public Comparator<CacheRecord<K, V>> get() {

        Comparator<CacheRecord<K, V>> orderByLastUsedAt = Comparator.comparing(CacheRecord::getLastUsedAt);
        return orderByLastUsedAt.reversed();
    }
}
