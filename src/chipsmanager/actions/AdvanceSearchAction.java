package chipsmanager.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;
import chipsmanager.javabean.Chips;
import chipsmanager.tools.pageBean;

public class AdvanceSearchAction extends ActionSupport{
	private String chipname;
	private String modelid;
	private String function;
	private int pinnumber;
	
	private String not1;
	private String not2;
	private String not3;
	private String not4;
	
	private String select1;
	private String select2;
	private String select3;
	
	/**
	 * @return the chipname
	 */
	public String getChipname() {
		return chipname;
	}
	/**
	 * @param chipname the chipname to set
	 */
	public void setChipname(String chipname) {
		this.chipname = chipname;
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
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}
	/**
	 * @param function the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	/**
	 * @return the pinnumber
	 */
	public int getPinnumber() {
		return pinnumber;
	}
	/**
	 * @param pinnumber the pinnumber to set
	 */
	public void setPinnumber(int pinnumber) {
		this.pinnumber = pinnumber;
	}
	
	/**
	 * @return the not1
	 */
	public String getNot1() {
		return not1;
	}
	/**
	 * @param not1 the not1 to set
	 */
	public void setNot1(String not1) {
		this.not1 = not1;
	}
	/**
	 * @return the not2
	 */
	public String getNot2() {
		return not2;
	}
	/**
	 * @param not2 the not2 to set
	 */
	public void setNot2(String not2) {
		this.not2 = not2;
	}
	/**
	 * @return the not3
	 */
	public String getNot3() {
		return not3;
	}
	/**
	 * @param not3 the not3 to set
	 */
	public void setNot3(String not3) {
		this.not3 = not3;
	}
	/**
	 * @return the not4
	 */
	public String getNot4() {
		return not4;
	}
	/**
	 * @param not4 the not4 to set
	 */
	public void setNot4(String not4) {
		this.not4 = not4;
	}
	/**
	 * @return the select1
	 */
	public String getSelect1() {
		return select1;
	}
	/**
	 * @param select1 the select1 to set
	 */
	public void setSelect1(String select1) {
		this.select1 = select1;
	}
	/**
	 * @return the select2
	 */
	public String getSelect2() {
		return select2;
	}
	/**
	 * @param select2 the select2 to set
	 */
	public void setSelect2(String select2) {
		this.select2 = select2;
	}
	/**
	 * @return the select3
	 */
	public String getSelect3() {
		return select3;
	}
	/**
	 * @param select3 the select3 to set
	 */
	public void setSelect3(String select3) {
		this.select3 = select3;
	}	
	
	
	/**
	 * @param pagesize
	 * @param page
	 * @param function
	 * @return 
	 * 功能：对查询到的芯片进行分页
	 */
	public pageBean getSelectPageBean(int pagesize,int page,ArrayList<Chips> tmp,ArrayList<Chips> list){
		pageBean pagebean=new pageBean();
		
		int allRows=list.size();
		int totalPage=pagebean.getTotalPages(pagesize,allRows);
		int currentPage=pagebean.getCurPage(page);
		
		pagebean.setList(tmp);
		pagebean.setAllRows(allRows);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPage(totalPage);
		
		return pagebean;
	}
	
	@Override
	public void validate() {
		if(
				new chipsDataDao().ifModelIDExists(modelid)&&
				new chipsDataDao().ifChipNameExists(chipname)){
			this.addFieldError("dataErr", "查询参数错误");
		}
	}
	
	@Override
	public String execute() throws Exception {
		 HttpServletRequest request=ServletActionContext.getRequest();
		 this.setSelect1(request.getParameter("select1"));
		 this.setSelect1(request.getParameter("select1"));
		 this.setSelect1(request.getParameter("select1"));
		 String querySQL="SELECT * FROM CHIPS WHERE";
		 
		 
		 if(not1==null)
			 querySQL=querySQL+" CHIP_NAME LIKE ?";
		 else if(not1.equals("on"))
			 querySQL=querySQL+" CHIP_NAME NOT LIKE ?";
		 
		 
		 if(select1.equals("and"))
			 querySQL=querySQL+" AND";
		 else if(select1.equals("or"))
			 querySQL=querySQL+" OR";
		 else if(select1.equals("empty"))
			 querySQL=querySQL+" OR";
		 
		 
		 if(not2==null)
			 querySQL=querySQL+" MODEL_ID LIKE ?";
		 else if(not2.equals("on"))
			 querySQL=querySQL+" MODEL_ID NOT LIKE ?";
		 
		 
		 
		 if(select2.equals("and"))
			 querySQL=querySQL+" AND";
		 else if(select2.equals("or"))
			 querySQL=querySQL+" OR";
		 else if(select1.equals("empty"))
			 querySQL=querySQL+" OR";
		 
		 
		 if(not3==null)
			 querySQL=querySQL+" FUNCTIONS LIKE ?";
		 else if(not3.equals("on"))
			 querySQL=querySQL+" FUNCTIONS NOT LIKE ?";
		 
		 
		 if(select3.equals("and"))
			 querySQL=querySQL+" AND";
		 else if(select3.equals("or"))
			 querySQL=querySQL+" OR";
		 else if(select1.equals("empty"))
			 querySQL=querySQL+" OR";
		 
		 
		 if(not4==null)
			 querySQL=querySQL+" PIN_NUMBER=?";
		 else if(not4.equals("on"))
			 querySQL=querySQL+" PIN_NUMBER!=? ";
		 
		 Connection connection=null;
		 PreparedStatement preparedStatement=null;
		 ResultSet resultSet=null;
		 ArrayList<Chips> chipList=new ArrayList<>();
		 try{
			 connection=dbConn.connectToDatabase();
//			 System.out.println(querySQL);
			 preparedStatement=connection.prepareStatement(querySQL);
			 preparedStatement.setString(1, "%"+chipname+"%");
			 preparedStatement.setString(2, "%"+modelid+"%");
			 preparedStatement.setString(3, "%"+function+"%");
			 preparedStatement.setInt(4, pinnumber);
			 resultSet=preparedStatement.executeQuery();
			 
			 while(resultSet.next()){
				 	new chipsDataDao().increaseQueryFreq(resultSet.getInt("CHIP_ID"));
					Chips tmp=new Chips(resultSet.getInt("CHIP_ID"), 
							resultSet.getString("MODEL_ID"), 
							resultSet.getString("CHIP_NAME"), 
							resultSet.getString("FUNCTIONS"),
							resultSet.getInt("PIN_NUMBER"), 
							resultSet.getString("PIN_DEFINATION"), 
							resultSet.getString("CHIP_INTRODUCTION")
					);
					chipList.add(tmp);
			}
		 }catch(SQLException ex){
			 ex.printStackTrace();
		 }finally{
			 dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		 } 
		 
		 pageBean pagebean=new pageBean();
		 pagebean.setList(chipList);
		 request.setAttribute("pageBean",pagebean );
		 return SUCCESS;
	}
	
}
