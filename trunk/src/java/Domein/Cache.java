package Domein;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class Cache
{
	private static Cache  singleton;
	private net.sf.ehcache.Cache cachje;
        
        private Cache()
        {
            CacheManager singletonManager = CacheManager.create();          
            singletonManager.addCache("cachje");
            cachje = singletonManager.getCache("cachje");
        }
        
        public static Cache getInstance()
        {
            if(singleton==null)
            {
                singleton=new Cache();
            }
            return singleton;
        }
	public  void putInCache(String key, Object value)
	{            
            cachje.put(new Element(key, value));
	}
	
	public Object getFromCache(String key)
	{        
            return cachje.get(key).getObjectValue();
	}
	
	public void purgeCache(String key)
	{
            List keys = cachje.getKeys();
            for(Object o: keys)
            {
                if(o.toString().startsWith(key))
                {
                   cachje.remove(o);	
                }                
            }
        }
           
}
