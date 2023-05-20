package net.prashantk.evict;

import net.prashantk.entity.CacheRecord;

import java.util.Comparator;

public interface EvictionPolicy<K, V> {
    Comparator<CacheRecord<K, V>> get();
}
