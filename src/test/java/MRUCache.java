import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import net.prashantk.cache.Cache;
import net.prashantk.cache.CacheImpl;
import net.prashantk.evict.EvictionPolicy;
import net.prashantk.evict.impl.MRUEvictionPolicy;

public class MRUCache {
    @Test
    public void mruCache() {

        EvictionPolicy<Integer, Integer> mruEvictionPolicy = new MRUEvictionPolicy<>();

        Cache<Integer, Integer> myCache = new CacheImpl<>(mruEvictionPolicy, 3L);


        myCache.put(10, 10);
        myCache.put(9, 9);
        myCache.put(8, 8);

        Assertions.assertEquals(10, myCache.get(10).orElseGet(() -> Integer.MIN_VALUE));

        myCache.put(7, 7);

        Assertions.assertFalse(myCache.contains(10));

        myCache.put(7, 7);

        Assertions.assertEquals(7, myCache.get(7).orElseGet(() -> Integer.MIN_VALUE));
        Assertions.assertEquals(8, myCache.get(8).orElseGet(() -> Integer.MIN_VALUE));
        Assertions.assertEquals(9, myCache.get(9).orElseGet(() -> Integer.MIN_VALUE));
    }
}
