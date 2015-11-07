package com.nerbit.memCache.TahmCache;

public interface TahmCache<K,V> {
	V get(K key);
	V put(K key, V referent);
}
