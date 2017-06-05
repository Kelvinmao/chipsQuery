package chipsmanager.tools;

import java.util.ArrayList;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;
import chipsmanager.redisDao.RedisDao;

public class CacheHighFreqChips {
	public void getHighFreqList(){
		ArrayList<Chips> tmp=new chipsDataDao().getHighFreqChips();
		RedisDao dao=new RedisDao();
		for(int i=0;i<tmp.size();++i){
			String chip_id=new String().valueOf(tmp.get(i).getChipID());
			String sear_freq=new String().valueOf(tmp.get(i).getSearchFreq());
			dao.frequenceCache(sear_freq, tmp.get(i).getChipName(), tmp.get(i).getFunctions(), chip_id);
		}
	}
}
