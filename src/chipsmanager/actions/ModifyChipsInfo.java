package chipsmanager.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;

public class ModifyChipsInfo extends ActionSupport{
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
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		Chips queryChip=new chipsDataDao().searchChipsByChipId(chipID);
		request.setAttribute("chips", queryChip);	
		request.setCharacterEncoding("UTF-8");
		return SUCCESS;
	}
}
