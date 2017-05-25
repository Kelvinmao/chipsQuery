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
 * @author MaoKaining(ë����)
 * @version 1.0
 * Copyright (c) 2017,�����ʵ��ѧ�Ƽ����´�Ӫ
 * All rights reserved.
 * �� ��:������ĳһ�����е���Ŀ���з�ҳ��ʾ
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
	 * ���ܣ���ҳ����
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
		 	case "1": judge="�����";break;
		 	case "2": judge="������";break;
		 	case "3": judge="������";break;
		 	case "4": judge="����";break;
		 	case "5": judge="�����";break;
		 	case "6": judge="����";break;
		 	case "7": judge="������";break;
		 	case "8": judge="������";break;
		 	case "9": judge="��ֵ�Ƚ���";break;
		 	case "10": judge="���/������";break;
		 	case "11": judge="������";break;
		 	case "12": judge="�Ĵ���";break;
		 	case "13": judge="У����";break;
		 	case "14": judge="����������";break;
		 	case "15": judge="ȫ����";break;
		}
		ActionContext.getContext().getSession().put("Classify", judge);
		pageBean pagebean=getSelectPageBean(10, page, judge);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("pageBean", pagebean);
		return SUCCESS;
	}

	
}