package chipsmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;

public class AdminDataDao {
	/**
	 * @param admin_id
	 * @param admin_pwd
	 * @return
	 * ���ܣ�У�����Ա��Ϣ
	 */
	public boolean validAdmin(String admin_id,String admin_pwd){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT ADMIN_PWD FROM ADMINDATA WHERE ADMIN_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, admin_id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				return true;
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return false;
	}
}
