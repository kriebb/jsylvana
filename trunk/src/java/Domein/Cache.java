package Domein;

import java.util.HashMap;
import java.util.Map;

public class Cache
{
	private static Map<String, Object> cachelijst = new HashMap<String,Object>();
	
	public static void putInCache(String key, Object value)
	{
		cachelijst.put(key,value);
	}
	
	public static Object getFromCache(String key)
	{
		return cachelijst.get(key);
	}
	
	public static void purgeCache(String key)
	{
		for (Map.Entry<String, Object> item : cachelijst.entrySet())
        {
            if (item.getKey().startsWith(key))
               cachelijst.remove(item.getKey());
        }
	}
}
