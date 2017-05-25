package chipsmanager.tools;
import java.util.ArrayList;

import chipsmanager.javabean.*;
/**
 * @author MaoKaining(毛凯宁)
 * @version 1.0
 * Copyright (c) 2017,北京邮电大学科技创新大本营
 * All rights reserved.
 * 功 能:对项目显示界面进行分页
 *
 */
public class pageBean
{
    private ArrayList<chips> list; 				//用ArrayList<chips>存放查询结果
    
    private int allRows; 			//总记录数
    
    private int totalPage; 		//总页数
    
    private int currentPage; 	//当前页

    public ArrayList<chips> getList()
    {
        return list;
    }

    public void setList(ArrayList<chips> list)
    {
        this.list = list;
    }

    public int getAllRows()
    {
        return allRows;
    }

    public void setAllRows(int allRows)
    {
        this.allRows = allRows;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
    
    /**
     * 得到总页数
     * @param pageSize 每页记录数
     * @param allRows  总记录数
     * @return 总页数
     */
    public int getTotalPages(int pageSize, int allRows)
    {
        int totalPage = (allRows % pageSize == 0)? (allRows / pageSize): (allRows / pageSize) + 1;
       
        return totalPage;
    }
    
    /**
     * 得到当前开始记录号
     * @param pageSize 每页记录数
     * @param currentPage 当前页
     * @return
     */
    public int getCurrentPageOffset(int pageSize, int currentPage)
    {
        int offset = pageSize * (currentPage - 1);
        
        return offset;
    }
    
    /**
     * 得到当前页， 如果为0 则开始第一页，否则为当前页
     * @param page
     * @return
     */
    public int getCurPage(int page)
    {
        int currentPage = (page == 0)? 1: page;
        
        return currentPage;
    }
    
}
