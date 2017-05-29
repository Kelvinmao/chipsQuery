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
	 * 功能:根据芯片型号进行模糊查询，不分页
	 */
	public ArrayList<Chips> searchChipsByModelIdWithoutDivide(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT * FROM CHIPS WHERE MODEL_ID LIKE ?";
		System.out.println("modelid"+model_id);
		ArrayList<Chips> chipsList=new ArrayList<>();
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, "%"+model_id+"%");
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				Chips tmp= new Chips(rSet.getInt("CHIP_ID"), 
						rSet.getString("MODEL_ID"), 
						rSet.getString("CHIP_NAME"), 
						rSet.getString("FUNCTIONS"), 
						rSet.getInt("PIN_NUMBER"), 
						rSet.getString("PIN_DEFINATION"), 
						rSet.getString("CHIP_INTRODUCTION")
				);
				chipsList.add(tmp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return chipsList;
	}
	
	/**
	 * @param model_id
	 * @return
	 * 功能：根据芯片型号进行模糊查询并分页
	 */
	public ArrayList<Chips> searchChipsByModelId(String model_id,int curPage,int page_size){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet rSet=null;
		String querySQL="SELECT * FROM CHIPS WHERE MODEL_ID LIKE ? LIMIT ?,?";
		ArrayList<Chips> chipList=new ArrayList<>();
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, "%"+model_id+"%");
			preparedStatement.setInt(2, (curPage-1)*page_size);
			preparedStatement.setInt(3, page_size);
			
			rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				Chips tmp= new Chips(rSet.getInt("CHIP_ID"), 
						rSet.getString("MODEL_ID"), 
						rSet.getString("CHIP_NAME"), 
						rSet.getString("FUNCTIONS"), 
						rSet.getInt("PIN_NUMBER"), 
						rSet.getString("PIN_DEFINATION"), 
						rSet.getString("CHIP_INTRODUCTION")
				);
				chipList.add(tmp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, rSet);
		}
		return chipList;
	}
	
	/**
	 * @param model_id
	 * @return
	 * 功能：根据芯片ID返回芯片对象
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
	 * @param function
	 * @return
	 * 功能：按功能查询芯片，不分页
	 */
	public ArrayList<Chips> searchChipsAndDivided(String function){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Chips> list=new ArrayList<>();
		String querySQL="SELECT * FROM CHIPS WHERE FUNCTIONS=?";
		try{
			connection=dbConn.connectToDatabase();
			if("".equals(function)){
				preparedStatement=connection.prepareStatement("SELECT * FROM CHIPS");
			}else{
				preparedStatement=connection.prepareStatement(querySQL);
				preparedStatement.setString(1, function);
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
		String querySQL="SELECT * FROM CHIPS WHERE FUNCTIONS=? LIMIT ?,?";
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
	
	/**
	 * @param function
	 * @return
	 * 功能：按功能对芯片进行模糊查询
	 */
	public ArrayList<Chips> SearchByFunction(String function){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Chips> chipList=new ArrayList<>();
		String querySQL="SELECT * FROM CHIPS WHERE FUNCTIONS lIKE ?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, function);
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
				chipList.add(tmp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return chipList;
	}
	
	/**
	 * @param chip_id
	 * @return
	 * 功能：校验chip_id是否存在
	 */
	public boolean ifChipIDExists(int chip_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT CHIP_ID FROM CHIPS WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setInt(1, chip_id);
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
	
	/**
	 * @param model_id
	 * @return
	 * 功能：校验model_id是否存在
	 */
	public boolean ifModelIDExists(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT MODEL_ID FROM CHIPS WHERE MODEL_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, model_id);
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
	
	/**
	 * @param chip_name
	 * @return
	 * 功能：校验芯片名是否存在
	 */
	public boolean ifChipNameExists(String chip_name){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT CHIP_NAME FROM CHIPS WHERE CHIP_NAME=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, chip_name);
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
	
	/**
	 * @param new_id
	 * 功能：通过旧的CHIP_ID更改芯片ID
	 */
	public void modifyChipID(int old_id,int new_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="UPDATE CHIPS SET CHIP_ID=? WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setInt(1, new_id);
			preparedStatement.setInt(2, old_id);
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	/**
	 * @param chip_id
	 * @param model_id
	 * 功能：通过chip_id更改芯片型号
	 */
	public void modifyModelID(int chip_id,String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="UPDATE CHIPS SET MODEL_ID=? WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setString(1, model_id);
			preparedStatement.setInt(2,chip_id);
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	/**
	 * @param chip_id
	 * @param new_name
	 * 功能：修改芯片名称
	 */
	public void modifyChipName(int chip_id,String new_name){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="UPDATE CHIPS SET CHIP_NAME=? WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setString(1, new_name);
			preparedStatement.setInt(2,chip_id);
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	/**
	 * @param chip_id
	 * @param new_function
	 * 功能：修改芯片功能
	 */
	public void modifyChipFunction(int chip_id,String new_function){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="UPDATE CHIPS SET FUNCTIONS=? WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setString(1, new_function);
			preparedStatement.setInt(2, chip_id);
			preparedStatement.executeQuery();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	/**
	 * @param chip_id
	 * @param new_pinnum
	 * 功能：修改芯片管脚数
	 */
	public void modifyChipPinNumber(int chip_id,int new_pinnum){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="UPDATE CHIPS SET PIN_NUMBER=? WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setInt(1, new_pinnum);
			preparedStatement.setInt(2, chip_id);
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	/**
	 * @param chip_id
	 * @param new_pindef
	 * 功能：修改管脚定义
	 */
	public void modifyPinDef(int chip_id,String new_pindef){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="UPDATE CHIPS SET PIN_DEFINATION=? WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setString(1, new_pindef);
			preparedStatement.setInt(2, chip_id);
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	/**
	 * @param chip_id
	 * @param chip_intro
	 * 功能：更改芯片简介
	 */
	public void modifyChipIntro(int chip_id,String chip_intro){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="UPDATE CHIPS SET CHIP_INTRODUCTION=? WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setString(1, chip_intro);
			preparedStatement.setInt(2, chip_id);
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	public void getAdminInfo(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String queryMountSQL="SELECT COUNT(*) FROM CHIPS";
		int chipMount=0;
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			resultSet=preparedStatement.executeQuery();
			chipMount=resultSet.getInt(1);
		}
	}
}
