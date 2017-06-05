package chipsmanager.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;
import chipsmanager.tools.pageBean;

public class AdminDeleteChipsAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bychipname;
	private String bymodelid;
	private	String bychipid;
	private String chipName;
	private String modelid;
	private String chipid;
	private int page=1;
	/**
	 * @return the bychipname
	 */
	public String getBychipname() {
		return bychipname;
	}
	/**
	 * @param bychipname the bychipname to set
	 */
	public void setBychipname(String bychipname) {
		this.bychipname = bychipname;
	}
	/**
	 * @return the bymodelid
	 */
	public String getBymodelid() {
		return bymodelid;
	}
	/**
	 * @param bymodelid the bymodelid to set
	 */
	public void setBymodelid(String bymodelid) {
		this.bymodelid = bymodelid;
	}
	/**
	 * @return the bychipid
	 */
	public String getBychipid() {
		return bychipid;
	}
	/**
	 * @param bychipid the bychipid to set
	 */
	public void setBychipid(String bychipid) {
		this.bychipid = bychipid;
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
	 * @return the modelid
	 */
	public String getModelid() {
		return modelid;
	}
	/**
	 * @param modelid the modelid to set
	 */
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	/**
	 * @return the chipid
	 */
	public String getChipid() {
		return chipid;
	}
	/**
	 * @param chipid the chipid to set
	 */
	public void setChipid(String chipid) {
		this.chipid = chipid;
	}
	
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	/**
	 * @param pagesize
	 * @param page
	 * @param function
	 * @return 
	 * 功能：对根据型号查询到的芯片进行分页
	 */
	public pageBean getChipNamePageBean(int pagesize,int page,String chip_name){
		pageBean pagebean=new pageBean();
		
		ArrayList<Chips> temp=new chipsDataDao().searchByChipName(page, pagesize, chip_name);
		
		
		int allRows=new chipsDataDao().searchByChipName(chip_name);
		int totalPage=pagebean.getTotalPages(pagesize,allRows);
		int currentPage=pagebean.getCurPage(page);
		
		pagebean.setList(temp);
		pagebean.setAllRows(allRows);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPage(totalPage);
		
		return pagebean;
	}
	
	/**
	 * @param pagesize
	 * @param page
	 * @param model_id
	 * @return
	 * 功能：进行分页操作
	 */
	public pageBean getModelIDPageBean(int pagesize,int page,String model_id){
		pageBean pagebean=new pageBean();
		
		ArrayList<Chips> temp=new chipsDataDao().searchByModelID(page, pagesize, model_id);
		
		
		int allRows=new chipsDataDao().searchByModelID(model_id);
		int totalPage=pagebean.getTotalPages(pagesize,allRows);
		int currentPage=pagebean.getCurPage(page);
		
		pagebean.setList(temp);
		pagebean.setAllRows(allRows);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPage(totalPage);
		
		return pagebean;
	}
	
	@Override
	public String execute() throws Exception {
		if(bychipid!=null&&bychipname==null&&bymodelid==null){
			//开始按照id查询，然后让用户选择删除
			Chips chip=new chipsDataDao().searchChipsByChipId(chipid);
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("chip", chip);
			
			return SUCCESS;
		}else if(bychipid==null&&bychipname!=null&&bymodelid==null){
			pageBean pagebean=getChipNamePageBean(10, page, chipName);
			HttpServletRequest request=ServletActionContext.getRequest();
			
			request.setAttribute("pageBean", pagebean);
			
			return SUCCESS;
		}else if(bychipid==null&&bychipname==null&&bymodelid!=null){
			pageBean pagebean=getModelIDPageBean(10, page, modelid);
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("pageBean", pagebean);
			
			return SUCCESS;
		}
		return ERROR;
	}
	
}
