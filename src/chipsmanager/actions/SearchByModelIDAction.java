package chipsmanager.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;
import chipsmanager.tools.pageBean;

public class SearchByModelIDAction extends ActionSupport{
	
	private String modelID;
	private int page=1;
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
	public pageBean getSelectPageBean(int pagesize,int page,String model_id){
		pageBean pagebean=new pageBean();
		ArrayList<Chips> list=new chipsDataDao().searchChipsByModelIdWithoutDivide(model_id);
		ArrayList<Chips> temp=new chipsDataDao().searchChipsByModelId(model_id, page, pagesize);
		
		
		int allRows=list.size();
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
		pageBean pagebean=getSelectPageBean(10, page, modelID);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("pageBean", pagebean);
		ArrayList<Chips> list=pagebean.getList();
		return SUCCESS;
	}




	
}
