package chipsmanager.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.*;
import chipsmanager.tools.pageBean;
/**
 * @author MaoKaining(毛凯宁)
 * @version 1.0
 * Copyright (c) 2017,北京邮电大学科技创新大本营
 * All rights reserved.
 * 功 能:对属于某一分类中的项目进行分页显示
 *
 */
public class ClassifySelectAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String function;
	private int page;
	
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
	/**
	 * @param pagesize
	 * @param page
	 * @param classify
	 * @return
	 * 功能：分页操作
	 */
	public pageBean getSelectPageBean(int pagesize,int page,String function){
		pageBean pagebean=new pageBean();
		ArrayList<chips> list=new chipsDataDao().searchChipsAndDivided(function);
		ArrayList<chips> temp=new chipsDataDao().searchChipsAndDivided(page,pagesize,function);
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
		String judge="";
		switch (function){
			case "0": judge="";break;
		 	case "1": judge="与非门";break;
		 	case "2": judge="反相器";break;
		 	case "3": judge="驱动器";break;
		 	case "4": judge="与门";break;
		 	case "5": judge="或非门";break;
		 	case "6": judge="或门";break;
		 	case "7": judge="缓冲器";break;
		 	case "8": judge="译码器";break;
		 	case "9": judge="数值比较器";break;
		 	case "10": judge="异或/异或非门";break;
		 	case "11": judge="计数器";break;
		 	case "12": judge="寄存器";break;
		 	case "13": judge="校验器";break;
		 	case "14": judge="函数产生器";break;
		 	case "15": judge="全加器";break;
		}
		ActionContext.getContext().getSession().put("Classify", judge);
		pageBean pagebean=getSelectPageBean(10, page, judge);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("pageBean", pagebean);
		return SUCCESS;
	}

	
}