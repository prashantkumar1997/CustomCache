package net.prashantk.cache;

import net.prashantk.evict.EvictionPolicy;
import net.prashantk.entity.CacheRecord;

import java.util.*;

public class CacheImpl<K, V> implements Cache<K, V> {

    private final Long size;
    private final Map<K, CacheRecord<K, V>> cachedMap;
    private final PriorityQueue<CacheRecord<K, V>> cacheRecords;

    public CacheImpl(EvictionPolicy<K, V> evictionPolicy, Long size) {

        this.size = size;
        Comparator<CacheRecord<K, V>> evict = evictionPolicy.get();

        cachedMap = new HashMap<>();
        cacheRecords = new PriorityQueue<>(evict);
    }

    @Override
    public Optional<V> get(K key) {
        CacheRecord<K, V> cacheRecord = cachedMap.get(key);

        if (cacheRecord != null) {

            cacheRecords.remove(cacheRecord);
            cacheRecord.updateLastUsed();
            cacheRecord.incrementAccessCounter();
            cacheRecords.add(cacheRecord);
        }

        return Optional.ofNullable(cacheRecord)
                .map(CacheRecord::getValue);
    }

    @Override
    public void put(K key, V value) {

        CacheRecord<K, V> topElement = cacheRecords.peek();
        if (cacheRecords.size() != 0 && cachedMap.size() == size && !cachedMap.containsKey(key)) {
            cacheRecords.remove(topElement);
            cachedMap.remove(topElement.getKey());
        }

        if (cachedMap.containsKey(key)) {
            CacheRecord<K, V> cacheRecord = cachedMap.get(key);
            cacheRecord.setValue(value);
            cacheRecord.updateLastUsed();
        } else {
            CacheRecord<K, V> newRecord = new CacheRecord<>(key, value);
            cachedMap.put(key, newRecord);
            cacheRecords.add(newRecord);
        }
    }

    @Override
    public boolean contains(K key) {
        return cachedMap.containsKey(key);
    }
}
