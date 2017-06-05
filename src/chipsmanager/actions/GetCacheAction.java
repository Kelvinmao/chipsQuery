package chipsmanager.actions;

import java.io.PrintWriter;


import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;
import chipsmanager.redisDao.RedisDao;
import chipsmanager.tools.CacheHighFreqChips;
import chipsmanager.tools.pageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.*;

public class GetCacheAction extends ActionSupport{
	
	@Override
	public String execute() throws Exception {
		new CacheHighFreqChips().getHighFreqList();
		JSONArray jsonArr=new RedisDao().getHighFreqChipsFromCache();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.write(jsonArr.toString());
			
		return null;
	}
}