package com.nerbit.memCache.TahmCache;

import java.util.Hashtable;

public class CacheManager {
	
	private Hashtable<String,TahmCache> caches = new Hashtable<String,TahmCache>();
	
	public <K,V> TahmCache<K,V> createThamSoftCache_(K k,V v){
		return new TahmSoftCache<K,V>();
	}
	
	public TahmCache createThamSoftCache(String ckey, Class kcls,Class vcls)
			throws InstantiationException, IllegalAccessException{
		TahmCache c = createThamSoftCache_(kcls.newInstance(),vcls.newInstance());
		caches.put(ckey, c);
		return c;
	}
	
	public TahmCache get(String cacheName){
		return caches.get(cacheName);
	}
	
	public TahmCache remove(String cacheName){
		return caches.remove(cacheName);
	}
}
