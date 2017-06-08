package chipsmanager.actions;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Result;

import chipsmanager.dao.AdminDataDao;
import chipsmanager.dao.UserDataDao;
import chipsmanager.dao.chipsDataDao;
import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;
import chipsmanager.javabean.Admin;
import net.sf.json.JSONObject;

public class AdminGetInfo extends ActionSupport{
	@Override
	public String execute() throws Exception {
		chipsDataDao cdao=new chipsDataDao();
		UserDataDao udao=new UserDataDao();
		AdminDataDao adao=new AdminDataDao();
		int cAmount=cdao.getChipAmount();
		String uAmount=udao.getUserNum();
		String aAmount=adao.getAdminNum();
		String fAmount=cdao.getFunctionNum();
		
		HashMap<String, String> map=new HashMap<>();
		map.put("cAmount", String.valueOf(cAmount));
		map.put("fAmount", fAmount);
		map.put("uAmount", uAmount);
		map.put("aAmount", aAmount);
		
		JSONObject json=JSONObject.fromObject(map);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		try{
			out.write(json.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
