package net.prashantk.entity;

import java.time.Instant;

public class CacheRecord<K, V> {

    private final K key;
    private V value;

    private final Instant createdAt;
    private Instant lastUsedAt;

    public int getAccessCount() {
        return accessCount;
    }

    private  int accessCount;

    public CacheRecord(K key, V value) {
        this.key = key;
        this.value = value;
        this.createdAt = Instant.now();
        this.lastUsedAt = Instant.now();
        this.accessCount = 0;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getLastUsedAt() {
        return lastUsedAt;
    }

    public void updateLastUsed() {
        lastUsedAt = Instant.now();
    }

    public void incrementAccessCounter() {
        accessCount++;
    }

}
