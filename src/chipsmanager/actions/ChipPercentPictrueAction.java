package chipsmanager.actions;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import net.sf.json.JSONArray;

public class ChipPercentPictrueAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		JSONArray jsonArr=new chipsDataDao().computeAllFunctionsPercent();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.write(jsonArr.toString());
		return null;
	}
}
