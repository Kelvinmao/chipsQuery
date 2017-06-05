package chipsmanager.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;

public class AdminModifyChipsAction extends ActionSupport{
	private String ChipID;
	private String ModelID;
	private String Functions;
	private String PinNumber;
	private String PinDefination;
    private String ChipIntro;
    private String ChipName;
    
	/**
	 * @return the chipID
	 */
	public String getChipID() {
		return ChipID;
	}
	/**
	 * @param chipID the chipID to set
	 */
	public void setChipID(String chipID) {
		ChipID = chipID;
	}
	/**
	 * @return the modelID
	 */
	public String getModelID() {
		return ModelID;
	}
	/**
	 * @param modelID the modelID to set
	 */
	public void setModelID(String modelID) {
		ModelID = modelID;
	}
	/**
	 * @return the functions
	 */
	public String getFunctions() {
		return Functions;
	}
	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(String functions) {
		Functions = functions;
	}
	/**
	 * @return the pinNumber
	 */
	public String getPinNumber() {
		return PinNumber;
	}
	/**
	 * @param pinNumber the pinNumber to set
	 */
	public void setPinNumber(String pinNumber) {
		PinNumber = pinNumber;
	}
	/**
	 * @return the pinDefination
	 */
	public String getPinDefination() {
		return PinDefination;
	}
	/**
	 * @param pinDefination the pinDefination to set
	 */
	public void setPinDefination(String pinDefination) {
		PinDefination = pinDefination;
	}
	/**
	 * @return the chipIntro
	 */
	public String getChipIntro() {
		return ChipIntro;
	}
	/**
	 * @param chipIntro the chipIntro to set
	 */
	public void setChipIntro(String chipIntro) {
		ChipIntro = chipIntro;
	}
	
	@Override
	public void validate() {
		boolean flag= new chipsDataDao().validChipID(ChipID);
		if(!flag)
			this.addFieldError("idErr", "芯片ID错误");
	}
    
    @Override
    public String execute() throws Exception {
    	chipsDataDao dao=new chipsDataDao();
    	int id=0;
    	int pinnum=0;
    	try{
    		id=Integer.parseInt(ChipID);
    		pinnum=Integer.parseInt(PinNumber);
    	}catch(Exception ex){
    		HttpServletResponse response=ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
    		PrintWriter out=response.getWriter();
    		out.write("参数非法");
    	}
    	
    	if(ChipName!=null)	{
    		dao.modifyChipName(id, ChipName);
//    		HttpServletResponse response=ServletActionContext.getResponse();
//    		response.setCharacterEncoding("utf-8");
//    		PrintWriter out=response.getWriter();
//    		out.write("成功修改芯片名为："+ChipName);
    	}
    		
    	if(ModelID!=null){
    		dao.modifyModelID(id, ModelID);
//    		HttpServletResponse response=ServletActionContext.getResponse();
//    		response.setCharacterEncoding("utf-8");
//    		PrintWriter out=response.getWriter();
//    		out.write("成功修改芯片型号为："+ModelID);
    	}
    		
    	if(Functions!=null){
    		dao.modifyChipFunction(id, Functions);
//    		HttpServletResponse response=ServletActionContext.getResponse();
//    		response.setCharacterEncoding("utf-8");
//    		PrintWriter out=response.getWriter();
//    		out.write("成功修改芯片功能为："+Functions);
    	}
    		
    	if(PinNumber!=null){
    		dao.modifyChipPinNumber(id, pinnum);
//    		HttpServletResponse response=ServletActionContext.getResponse();
//    		response.setCharacterEncoding("utf-8");
//    		PrintWriter out=response.getWriter();
//    		out.write("成功修改芯片管脚数为："+PinNumber);
    	}
    		
    	if(PinDefination!=null){
    		dao.modifyPinDef(id, PinDefination);
//    		HttpServletResponse response=ServletActionContext.getResponse();
//    		response.setCharacterEncoding("utf-8");
//    		PrintWriter out=response.getWriter();
//    		out.write("成功修改芯片管脚定义为："+PinDefination);
    	}
    		
    	if(ChipIntro!=null){
    		dao.modifyChipIntro(id, ChipIntro);
    		HttpServletResponse response=ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
    		PrintWriter out=response.getWriter();
    		out.write("修改成功");
    	}
    		
    	
    	return null;
    }
}
