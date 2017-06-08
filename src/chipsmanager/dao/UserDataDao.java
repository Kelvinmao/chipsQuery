package chipsmanager.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;
import chipsmanager.javabean.User;

public class UserDataDao {
	/**
	 * @param user_id
	 * @return
	 * 功能：校验用户
	 */
	public boolean validUser(String user_id,String user_pwd){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT USER_PASSWORD FROM USERDATA WHERE USER_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, user_id);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				if(user_pwd.equals(resultSet.getString("USER_PASSWORD"))){
					return true;
				}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return false;
	}
	
	/**
	 * @param user_id
	 * @return
	 * 功能：通过user_id返回用户对象
	 */
	public User getUserByID(String user_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT USER_NAME FROM USERDATA WHERE USER_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, user_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return new User(rSet.getString("USER_NAME"));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return null;
	}
	
	/**
	 * @return
	 * 功能：获取用户总数
	 */
	public String getUserNum(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="select count(*) from userdata";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
				return resultSet.getString(1);
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return null;
	}
}
