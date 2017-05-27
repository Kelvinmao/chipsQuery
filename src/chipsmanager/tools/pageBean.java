package chipsmanager.tools;
import java.util.ArrayList;

import chipsmanager.javabean.*;
/**
 * @author MaoKaining(ë����)
 * @version 1.0
 * Copyright (c) 2017,�����ʵ��ѧ�Ƽ����´�Ӫ
 * All rights reserved.
 * �� ��:����Ŀ��ʾ������з�ҳ
 *
 */
public class pageBean
{
    private ArrayList<Chips> list; 				//��ArrayList<chips>��Ų�ѯ���
    
    private int allRows; 			//�ܼ�¼��
    
    private int totalPage; 		//��ҳ��
    
    private int currentPage; 	//��ǰҳ

    public ArrayList<Chips> getList()
    {
        return list;
    }

    public void setList(ArrayList<Chips> list)
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
     * �õ���ҳ��
     * @param pageSize ÿҳ��¼��
     * @param allRows  �ܼ�¼��
     * @return ��ҳ��
     */
    public int getTotalPages(int pageSize, int allRows)
    {
        int totalPage = (allRows % pageSize == 0)? (allRows / pageSize): (allRows / pageSize) + 1;
       
        return totalPage;
    }
    
    /**
     * �õ���ǰ��ʼ��¼��
     * @param pageSize ÿҳ��¼��
     * @param currentPage ��ǰҳ
     * @return
     */
    public int getCurrentPageOffset(int pageSize, int currentPage)
    {
        int offset = pageSize * (currentPage - 1);
        
        return offset;
    }
    
    /**
     * �õ���ǰҳ�� ���Ϊ0 ��ʼ��һҳ������Ϊ��ǰҳ
     * @param page
     * @return
     */
    public int getCurPage(int page)
    {
        int currentPage = (page == 0)? 1: page;
        
        return currentPage;
    }
    
}
