package chipsmanager.redisDao;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import chipsmanager.javabean.Chips;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.*;
/**
 * @author kelvin
 *
 */
public class RedisDao {
	/**
	 * @param model_id
	 * 功能：对被高频率查询的芯片进行缓存
	 */
	public void frequenceCache(String search_freq,String chip_name,String functions,String chip_id){
		Jedis jedis=new Jedis("localhost");
		HashMap<String, String> map=new HashMap<>();
		map.put("search_freq", search_freq);
		map.put("chip_name", chip_name);
		map.put("functions", functions);
		map.put("chip_id", chip_id);
		jedis.hmset("chips"+chip_id, map);
		jedis.close();
	}
	
	/**
	 * 功能：从缓存中取数据 以json数组形式返回
	 * @return
	 */
	public JSONArray getHighFreqChipsFromCache(){
		Jedis jedis=new Jedis("localhost");
		Set<String> keySet=jedis.keys("*");
		String [] keys=keySet.toArray(new String[0]);
		JSONArray  jsonStrs=new JSONArray();
		HashMap<String, String> map=new HashMap<>();
		String [] itemArr={"id","name","func","freq"};
 		int index=0;
		while(index<keys.length){		
			java.util.List<String> list=jedis.hmget(keys[index], "chip_id","chip_name","functions","search_freq");
			for(int i=0;i<itemArr.length;++i){
				map.put(itemArr[i], list.get(i));
			}
			jsonStrs.add(JSONObject.fromObject(map));
			++index;
		}
		jedis.close();
		return jsonStrs;
	}
	
	/**
	 * @return
	 * 从缓存中取数据 以ArrayList形式返回
	 */
	public ArrayList<Chips> getHighFreqChipsListFromCache(){
		Jedis jedis=new Jedis("localhost");
		Set<String> keySet=jedis.keys("*");
		String [] keys=keySet.toArray(new String[0]);
		ArrayList<Chips> chipList=new ArrayList<>();
		int index=0;
		while(index<keys.length){
			java.util.List<String> list=jedis.hmget(keys[index++], "search_freq","chip_name","functions","chip_id");
//			for(String tmp : list)
//				System.out.println(tmp);
			Chips chip=new Chips(list);
			chipList.add(chip);
		}
		jedis.close();
		return chipList;
	}
	
//	public static void main(String[] args) {
//		ArrayList<Chips> list=new RedisDao().getHighFreqChipsListFromCache();
//	}
}
