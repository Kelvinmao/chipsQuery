package chipsmanager.actions;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.util.StringBuilderWriter;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;
import net.sf.json.JSONObject;

/**
 * @author kelvin
 * 功能：通过AJAX向前端发送芯片详情
 *
 */
public class ShowChipsDetailByAjaxAction extends ActionSupport{
	private String chipID;
	/**
	 * @return the chipID
	 */
	public String getChipID() {
		return chipID;
	}

	/**
	 * @param chipID the chipID to set
	 */
	public void setChipID(String chipID) {
		this.chipID = chipID;
	}
	
	@Override
	public void validate() {
		if(!new chipsDataDao().validChipID(chipID)){
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try{
				PrintWriter out=response.getWriter();
				out.write("芯片ID有误");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}	
	}
	
	@Override
	public String execute() throws Exception {
		Chips chip=new chipsDataDao().searchChipsByChipId(chipID);
		HashMap<String, String> map=new HashMap<>();
		String [] itemArr={"id","name","func","pinnum","pindef","pinintro"};
		String [] valueArr={
				String.valueOf(chip.getChipID()),
				chip.getChipName(),
				chip.getFunctions(),
				String.valueOf(chip.getPinNumber()),
				chip.getPinDefination(),
				chip.getPinIntroduction()
				};
		for(int i=0;i<6;++i)
			map.put(itemArr[i], valueArr[i]);
		JSONObject json=JSONObject.fromObject(map);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.write(json.toString());
		return null;
	}
}
