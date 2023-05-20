# CustomCache

Design and implement a caching system that supports two operations: get(key) and put(key, value).

The cache should have a fixed size and should support multiple eviction policies. An eviction policy determines which item to remove from the cache when it is full and a new item is being added.
Initially, the system should support the Least Recently Used (LRU) eviction policy. This policy evicts the item that has not been accessed for the longest time.
However, the system design should be extensible to support other policies such as Most Recently Used(MRU) and First In, First Out (FIFO).


MRU policy removes the items that have been accessed most recently.
FIFO policy removes items in the order they were added.
Least Accessed Policy removes least accessed items from cache.

get(key): This operation should retrieve the value of the key if it exists in the cache.
put(key, value): This operation should insert or update the value if the key is not already present.

When the cache reaches its capacity, it should invalidate the item selected by the current eviction policy before inserting the new item.


Here's an example to illustrate the requirements:
Let's say we initialize a cache with a capacity of 2 and the LRU eviction policy:

Cache<Integer, Integer> cache = new Cache<>(2, new Cache.LRUPolicy );
The cache is currently empty: O

We perform the following operations: 

cache.put(1, 1); // The cache is now {1=1}
cache.put(2, 2); // The cache is now {1=1, 2=2}
cache.get(1); // Returns 1. The cache is now {2=2, 1=1}
cache.put(3, 3); // The cache is full, so key 2 is evicted (LRU). The cache is now {1=1,3=3}
cache.get(2); // Returns -1 (not found)
cache.put(4, 4); // The cache is full, so key 1 is evicted (LRU). The cache is now {3=3,4=4)
cache.get(1); // Returns -1 (not found)
