package chipsmanager.dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;
import chipsmanager.javabean.chips;;
/**
 * @author MaoKaining(ë����)
 * @version 1.0
 * �� ��:��chips����в���
 *
 */
public class chipsDataDao  {
	/**
	 * @param model_id
	 * @return
	 * ���ܣ�����оƬ�ͺŷ���оƬID
	 */
	public int getChipId(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT CHIP_ID FROM CHIPS WHERE MODEL_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, model_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return rSet.getInt("CHIP_ID");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return 0;
	}
	
	/**
	 * @param model_id
	 * @return
	 * ���ܣ�����оƬ�ͺŷ���оƬ��
	 */
	public String getChipName(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT CHIP_NAME FROM CHIPS WHERE MODEL_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, model_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return rSet.getString("CHIP_NAME");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return null;
	}
	
	/**
	 * @param model_id
	 * @return
	 * ���ܣ�����оƬ�ͺŷ���оƬ����
	 */
	public String getFunctions(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT FUNCTIONS FROM CHIPS WHERE MODEL_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, model_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return rSet.getString("FUNCTIONS");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return null;
	}
	
	
	/**
	 * @param model_id
	 * @return
	 * ���ܣ�����оƬ�ͺŷ���оƬ�ܽ���
	 */
	public int getPinNumber(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT PIN_NUMBER FROM CHIPS WHERE MODEL_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, model_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return rSet.getInt("PIN_NUMBER");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return 0;
	}
	
	/**
	 * @param model_id
	 * @return
	 * ���ܣ�����оƬ�ͺŷ���оƬ�ܽŶ���
	 */
	public String getDefination(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT PIN_DEFINATION FROM CHIPS WHERE MODEL_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, model_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return rSet.getString("PIN_DEFINATION");
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return null;
	}
	
	
	/**
	 * @param model_id
	 * @return
	 * ���ܣ�����оƬ�ͺŷ���оƬ����
	 */
	public chips searchChipsByModelId(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT * FROM CHIPS WHERE MODEL_ID =?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, model_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return new chips(rSet.getInt("CHIP_ID"), 
						rSet.getString("MODEL_ID"), 
						rSet.getString("CHIP_NAME"), 
						rSet.getString("FUNCTIONS"), 
						rSet.getInt("PIN_NUMBER"), 
						rSet.getString("PIN_DEFINATION"), 
						rSet.getString("CHIP_INTRODUCTION")
				);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return null;
	}
	/**
	 * @return
	 * ���ܣ����������ѯоƬ
	 */
	public chips searchChipsByModelId(String model_id,
			String chip_name,
			String functions,
			int pin_number,
			String [] queryParam){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT * FROM CHIPS WHERE MODEL_ID =?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, model_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return new chips(rSet.getInt("CHIP_ID"), 
						rSet.getString("MODEL_ID"), 
						rSet.getString("CHIP_NAME"), 
						rSet.getString("FUNCTIONS"), 
						rSet.getInt("PIN_NUMBER"), 
						rSet.getString("PIN_DEFINATION"), 
						rSet.getString("CHIP_INTRODUCTION")
				);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return null;
	}
	
	
	/**
	 * @param function
	 * @return
	 * ���ܣ������ܲ�ѯоƬ������ҳ
	 */
	public ArrayList<chips> searchChipsAndDivided(String function){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<chips> list=new ArrayList<>();
		String querySQL="SELECT * FROM CHIPS WHERE FUNCTION=?";
		try{
			connection=dbConn.connectToDatabase();
			if("".equals(function)){
				preparedStatement=connection.prepareStatement("SELECT * FROM CHIPS");
			}else{
				preparedStatement=connection.prepareStatement(querySQL);
			}
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				chips tmp=new chips(resultSet.getInt("CHIP_ID"), 
						resultSet.getString("MODEL_ID"), 
						resultSet.getString("CHIP_NAME"), 
						resultSet.getString("FUNCTIONS"),
						resultSet.getInt("PIN_NUMBER"), 
						resultSet.getString("PIN_DEFINATION"), 
						resultSet.getString("CHIP_INTRODUCTION")
				);
				list.add(tmp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	
	/**
	 * @param curPage
	 * @param pageSize
	 * @param function
	 * @return
	 * ���ܣ�����оƬ����ҳ
	 */
	public ArrayList<chips> searchChipsAndDivided(int curPage,int pageSize,String function){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT * FROM CHIPS WHERE FUNCTION=? LIMIT ?,?";
		ArrayList<chips> list=new ArrayList<>();
		try{
			connection=dbConn.connectToDatabase();
			if("".equals(function)){
				preparedStatement=connection.prepareStatement("SELECT * FROM CHIPS LIMIT ?,?");
				preparedStatement.setInt(1, (curPage-1)*pageSize);
				preparedStatement.setInt(2, pageSize);
			}else{
				preparedStatement=connection.prepareStatement(querySQL);
				preparedStatement.setString(1, function);
				preparedStatement.setInt(2, (curPage-1)*pageSize);
				preparedStatement.setInt(3, pageSize);
			}
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				chips tmp=new chips(resultSet.getInt("CHIP_ID"), 
						resultSet.getString("MODEL_ID"), 
						resultSet.getString("CHIP_NAME"), 
						resultSet.getString("FUNCTIONS"),
						resultSet.getInt("PIN_NUMBER"), 
						resultSet.getString("PIN_DEFINATION"), 
						resultSet.getString("CHIP_INTRODUCTION")
				);
				list.add(tmp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return list;
	}
}
