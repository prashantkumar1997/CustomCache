package net.prashantk.cache;

import net.prashantk.evict.EvictionPolicy;
import net.prashantk.evict.impl.LRUEvictionPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LRUCacheTest {
    @Test
    public void lruCache() {

        EvictionPolicy<Integer, Integer> lruEvictionPolicy = new LRUEvictionPolicy<>();

        Cache<Integer, Integer> myCache = new CacheImpl<>(lruEvictionPolicy, 3L);


        myCache.put(10, 10);
        myCache.put(9, 9);
        myCache.put(8, 9);

        Assertions.assertEquals(10, myCache.get(10).orElseGet(()-> Integer.MIN_VALUE));

        myCache.put(7, 7);

        Assertions.assertEquals(7,myCache.get(7).orElseGet(()-> Integer.MIN_VALUE));

        Assertions.assertTrue(myCache.contains(7));
        Assertions.assertTrue(myCache.contains(8));
        Assertions.assertTrue(myCache.contains(10));
    }
}
