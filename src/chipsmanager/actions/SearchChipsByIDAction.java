package chipsmanager.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;

public class SearchChipsByIDAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
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
		Chips chips=new chipsDataDao().searchChipsByChipId(chipID);
		HttpServletRequest request=ServletActionContext.getRequest();
		if(chips==null){
			this.addFieldError("IDerr", "芯片ID有误，请重新输入");
			System.out.println("芯片ID有误");
		}
			
		request.setAttribute("chips", chips);	
		request.setCharacterEncoding("UTF-8");
		return SUCCESS;
	}

	
}
