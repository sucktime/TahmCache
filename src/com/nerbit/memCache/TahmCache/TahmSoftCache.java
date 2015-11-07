package com.nerbit.memCache.TahmCache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TahmSoftCache<K,V> implements TahmCache<K, V>{

	ConcurrentMap<K, TahmSoftReference<K,V>> refs = new ConcurrentHashMap<K, TahmSoftReference<K,V>>();
	ReferenceQueue<V> refQueue = new ReferenceQueue<V>();
	
	public <KK,VV>TahmSoftCache() {
		super();
	}

	@SuppressWarnings("unchecked")
	private void shit(){
		/*
		 * remove the soft reference which is GCed from the map
		 * Future work: let another thread do this stuff
		 */
		TahmSoftReference<K, V> ref = null;
		while( (ref =  (TahmSoftReference<K, V>) refQueue.poll()) != null){
			refs.remove(ref.getKey());
		}
	}
	
	@Override
	public V get(K key){
		shit();
		TahmSoftReference<K, V> ref = refs.get(key);
		if(ref == null)
			return null;
		return ref.get();
	}
	
	@Override
	public V put(K key, V referent){
		shit();
		TahmSoftReference<K, V> ref = newReference(key, referent);
		ref = refs.put(key, ref);
		return ref==null?null:ref.get();
	}
	
	private TahmSoftReference<K, V> newReference(K key, V referent){
		return new TahmSoftReference<K, V>(key,referent,refQueue);
	}
	
	private static class TahmSoftReference<K,V> extends SoftReference<V>{
		protected K key;// the key of the referent
		
		public TahmSoftReference(K key, V referent, ReferenceQueue<V> q){
			super(referent, q);
			this.setKey(key);
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}
		
	}
}
