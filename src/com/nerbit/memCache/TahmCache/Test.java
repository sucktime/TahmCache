package com.nerbit.memCache.TahmCache;

public class Test {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		CacheManager mgr = new CacheManager();
		
		TahmCache<String, String> c1 = mgr.createThamSoftCache("c1",String.class, String.class);
		
		c1.put("1", "2");
		
		System.out.println(c1.get("1"));
	}
}
