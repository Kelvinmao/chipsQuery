package chipsmanager.actions;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Result;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;

public class AdminGetInfo extends ActionSupport{
	@Override
	public String execute() throws Exception {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT COUNT(*) FROM CHIPS";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				HttpServletResponse response=ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out=response.getWriter();
				out.write(resultSet.getString(1));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return null;
	}
}
