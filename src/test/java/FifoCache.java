import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import net.prashantk.cache.Cache;
import net.prashantk.cache.CacheImpl;
import net.prashantk.evict.EvictionPolicy;
import net.prashantk.evict.impl.FIFOEvictionPolicy;

public class FifoCache {
    @Test
    public void fifoCache() {

        EvictionPolicy<Integer, Integer> fifoEvictionPolicy = new FIFOEvictionPolicy<>();

        Cache<Integer, Integer> myCache = new CacheImpl<>(fifoEvictionPolicy, 3L);


        myCache.put(10, 10);
        myCache.put(9, 9);
        myCache.put(8, 9);

        Assertions.assertEquals(10, myCache.get(10).orElseGet(() -> Integer.MIN_VALUE));

        myCache.put(7, 7);
        Assertions.assertFalse(myCache.contains(10));

        Assertions.assertTrue(myCache.contains(9));
        Assertions.assertTrue(myCache.contains(8));
        Assertions.assertTrue(myCache.contains(7));

        myCache.put(7, 6);
        Assertions.assertEquals(6, myCache.get(7).orElseGet(() -> Integer.MIN_VALUE));
    }
}
