package chipsmanager.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;
import chipsmanager.javabean.Chips;

public class ShowChipsDetailAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String modelID;
	
	public String getModelID() {
		return modelID;
	}

	public void setModelID(String modelID) {
		this.modelID = modelID;
	}
	
	@Override
	public void validate() {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT MODEL_ID FROM CHIPS WHERE MODEL_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, modelID);
			resultSet=preparedStatement.executeQuery();
			if(!resultSet.next()){
				this.addFieldError("modelIDerr", "ÐÍºÅ´íÎó");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		
		Chips queryChip=new chipsDataDao().searchChipsByModelId(modelID);
		request.setAttribute("chips", queryChip);	
		request.setCharacterEncoding("UTF-8");
		return SUCCESS;
	}

	
	
}
