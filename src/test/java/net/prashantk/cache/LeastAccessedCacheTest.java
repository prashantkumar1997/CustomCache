package net.prashantk.cache;

import net.prashantk.evict.EvictionPolicy;
import net.prashantk.evict.impl.LeastAccessedPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeastAccessedCacheTest {

    @Test
    public void leastAccessedCache() {

        EvictionPolicy<Integer, Integer> leastAccessedPolicy = new LeastAccessedPolicy<>();

        Cache<Integer, Integer> myCache = new CacheImpl<>(leastAccessedPolicy, 3L);

        myCache.put(10, 10);
        myCache.put(9, 9);
        myCache.put(8, 9);

        Assertions.assertEquals(10, myCache.get(10).orElseGet(() -> Integer.MIN_VALUE));

        myCache.put(7, 7);

        Assertions.assertTrue(myCache.contains(10));
        Assertions.assertTrue(myCache.contains(7));

        myCache.put(7, 6);
        Assertions.assertEquals(6, myCache.get(7).orElseGet(() -> Integer.MIN_VALUE));
    }
}
