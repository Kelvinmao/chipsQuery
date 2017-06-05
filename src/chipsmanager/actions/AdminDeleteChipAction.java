package chipsmanager.actions;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;

public class AdminDeleteChipAction extends ActionSupport{
	
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
	public void validate() {
		boolean flag=new chipsDataDao().validChipID(chipID);
		if(!flag)
			this.addFieldError("idErr", "Ð¾Æ¬ID´íÎó");
	}
	
	@Override
	public String execute() throws Exception {
		new chipsDataDao().deleteChip(chipID);
		return SUCCESS;
	}
}
