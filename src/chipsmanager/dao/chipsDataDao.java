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
import chipsmanager.javabean.Chips;;
/**
 * @author MaoKaining(毛凯宁)
 * @version 1.0
 * 功 能:对chips表进行操作
 *
 */
public class chipsDataDao  {
	/**
	 * @param model_id
	 * @return
	 * 功能：根据芯片型号返回芯片ID
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
	 * 功能：根据芯片型号返回芯片名
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
	 * 功能：根据芯片型号返回芯片功能
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
	 * 功能：根据芯片型号返回芯片管教数
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
	 * 功能：根据芯片型号返回芯片管脚定义
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
	 * 功能：根据芯片型号返回芯片对象
	 */
	public Chips searchChipsByModelId(String model_id){
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
				return new Chips(rSet.getInt("CHIP_ID"), 
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
	 * @param model_id
	 * @return
	 * 功能：根据芯片型号返回芯片对象
	 */
	public Chips searchChipsByChipId(String chip_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT * FROM CHIPS WHERE CHIP_ID =?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, chip_id);
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				return new Chips(rSet.getInt("CHIP_ID"), 
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
	 * 功能：组合条件查询芯片
	 */
	public Chips searchChipsByModelId(String model_id,
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
				return new Chips(rSet.getInt("CHIP_ID"), 
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
	 * 功能：按功能查询芯片，不分页
	 */
	public ArrayList<Chips> searchChipsAndDivided(String function){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Chips> list=new ArrayList<>();
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
				Chips tmp=new Chips(resultSet.getInt("CHIP_ID"), 
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
	 * 功能：查找芯片并分页
	 */
	public ArrayList<Chips> searchChipsAndDivided(int curPage,int pageSize,String function){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT * FROM CHIPS WHERE FUNCTION=? LIMIT ?,?";
		ArrayList<Chips> list=new ArrayList<>();
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
				Chips tmp=new Chips(resultSet.getInt("CHIP_ID"), 
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
