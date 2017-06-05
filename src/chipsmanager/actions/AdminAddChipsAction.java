package chipsmanager.actions;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;

public class AdminAddChipsAction extends ActionSupport{
	private int chipID;
	private String modelID;
	private String chipName;
	private String functions;
	private int pinNumber;
	private String pinDefination;
	private String chipIntroduction;
	/**
	 * @return the chipID
	 */
	public int getChipID() {
		return chipID;
	}
	/**
	 * @param chipID the chipID to set
	 */
	public void setChipID(int chipID) {
		this.chipID = chipID;
	}
	/**
	 * @return the modelID
	 */
	public String getModelID() {
		return modelID;
	}
	/**
	 * @param modelID the modelID to set
	 */
	public void setModelID(String modelID) {
		this.modelID = modelID;
	}
	/**
	 * @return the chipName
	 */
	public String getChipName() {
		return chipName;
	}
	/**
	 * @param chipName the chipName to set
	 */
	public void setChipName(String chipName) {
		this.chipName = chipName;
	}
	/**
	 * @return the functions
	 */
	public String getFunctions() {
		return functions;
	}
	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(String functions) {
		this.functions = functions;
	}
	/**
	 * @return the pinNumber
	 */
	public int getPinNumber() {
		return pinNumber;
	}
	/**
	 * @param pinNumber the pinNumber to set
	 */
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	/**
	 * @return the pinDefination
	 */
	public String getPinDefination() {
		return pinDefination;
	}
	/**
	 * @param pinDefination the pinDefination to set
	 */
	public void setPinDefination(String pinDefination) {
		this.pinDefination = pinDefination;
	}
	/**
	 * @return the chipIntroduction
	 */
	public String getChipIntroduction() {
		return chipIntroduction;
	}
	/**
	 * @param chipIntroduction the chipIntroduction to set
	 */
	public void setChipIntroduction(String chipIntroduction) {
		this.chipIntroduction = chipIntroduction;
	}
	
	@Override
	public String execute() throws Exception {
		try{
			new chipsDataDao().addChips(new Chips(modelID, chipName, functions, pinNumber, pinDefination, chipIntroduction));
			HttpServletResponse response=ServletActionContext.getResponse();
			response.sendRedirect("add_chips.jsp");
			return SUCCESS;
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
}
