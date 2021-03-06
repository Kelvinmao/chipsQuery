package chipsmanager.dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

import com.opensymphony.xwork2.Result;

import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;
import chipsmanager.javabean.Chips;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;;
/**
 * @author MaoKaining(毛凯宁)
 * @version 1.0
 * 功 能:对chips表进行操作
 *
 */
public class chipsDataDao  {
	
	/**
	 * @param chip_id
	 * @return
	 * 查询芯片ID是否存在
	 */
	public boolean validChipID(String chip_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT CHIP_ID FROM CHIPS WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, chip_id);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
				return true;
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return false;
	}
	
	/**
	 * @param chip_id
	 * 功能：根据chip_id删除芯片
	 */
	public void deleteChip(String chip_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="DELETE FROM CHIPS WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setString(1, chip_id);
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	
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
				this.increaseQueryFreq(rSet.getInt("CHIP_ID"));
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
//		System.out.println("modelid"+model_id);
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
				this.increaseQueryFreq(rSet.getInt("CHIP_ID"));
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
				this.increaseQueryFreq(rSet.getInt("CHIP_ID"));
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
	 * @param chip_name
	 * @return
	 * 功能：获取chip_name相同的chip数量，并配合下面那个函数完成分页
	 */
	public int searchByChipName(String chip_name){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT COUNT(*) FROM CHIPS WHERE CHIP_NAME LIKE ?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, "%"+chip_name+"%");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
				return resultSet.getInt(1);
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return 0;
	}
	/**
	 * @param curPage
	 * @param page_size
	 * @param chip_name
	 * @return
	 * 功能：根据chip_name返回芯片（模糊查询）
	 */
	public ArrayList<Chips> searchByChipName(int curPage,int page_size,String chip_name){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Chips> chipsList=new ArrayList<>();
		String querySQL="SELECT * FROM CHIPS WHERE CHIP_NAME LIKE ? LIMIT ?,?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, "%"+chip_name+"%");
			preparedStatement.setInt(2, (curPage-1)*page_size);
			preparedStatement.setInt(3, page_size);
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
				chipsList.add(tmp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return chipsList;
	}
	
	/**
	 * @param model_id
	 * @return
	 * 功能：对modelid进行模糊查询并返回结果数量
	 */
	public int searchByModelID(String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT COUNT(*) FROM CHIPS WHERE MODEL_ID LIKE ?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
				return resultSet.getInt(1);
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return 0;
	}
	
	/**
	 * @param curPage
	 * @param page_size
	 * @param model_id
	 * @return
	 * 功能：对modelid进行模糊查询并分页
	 */
	public ArrayList<Chips> searchByModelID(int curPage,int page_size,String model_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Chips> chipsList=new ArrayList<>();
		String querySQL="SELECT * FROM CHIPS WHERE MODEL_ID LIKE ? LIMIT ?,?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			preparedStatement.setString(1, "%"+model_id+"%");
			preparedStatement.setInt(2, (curPage-1)*page_size);
			preparedStatement.setInt(3, page_size);
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
				chipsList.add(tmp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return chipsList;
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
			preparedStatement.executeUpdate();
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
	
	/**
	 * @param chip
	 * 功能：添加芯片
	 */
	public void addChips(Chips chip){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="INSERT INTO CHIPS(MODEL_ID,CHIP_NAME,FUNCTIONS,PIN_NUMBER,PIN_DEFINATION,CHIP_INTRODUCTION)VALUES(?,?,?,?,?,?)";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setString(1, chip.getModelID());
			preparedStatement.setString(2, chip.getChipName());
			preparedStatement.setString(3, chip.getFunctions());
			preparedStatement.setInt(4, chip.getPinNumber());
			preparedStatement.setString(5, chip.getPinDefination());
			preparedStatement.setString(6, chip.getPinIntroduction());
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	/**
	 * 功能：管理员界面获取信息
	 */
	public void getAdminInfo(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String queryMountSQL="SELECT COUNT(*) FROM CHIPS";
		int chipMount=0;
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(queryMountSQL);
			resultSet=preparedStatement.executeQuery();
			chipMount=resultSet.getInt(1);
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
	}
	
	/**
	 * @return
	 * 功能：用于获取芯片总数
	 */
	public int getChipAmount(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT COUNT(*) FROM CHIPS";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
				return resultSet.getInt(1);
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return 0;
	}
	
	/**
	 * @param chip_id
	 * 功能：查询频率自增
	 */
	public void increaseQueryFreq(int chip_id){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String modifySQL="UPDATE CHIPS SET QUERY_FREQ=QUERY_FREQ+1 WHERE CHIP_ID=?";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(modifySQL);
			preparedStatement.setInt(1, chip_id);
			preparedStatement.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.modifyConnectionClose(connection, preparedStatement);
		}
	}
	
	/**
	 * @return
	 * 功能：返回频繁查询的芯片列表（前十位）
	 */
	public ArrayList<Chips> getHighFreqChips(){
		ArrayList<Chips> highFreqList=new ArrayList<>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="SELECT * FROM CHIPS ORDER BY QUERY_FREQ DESC LIMIT 10";
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(querySQL);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				Chips tmp= new Chips(resultSet.getInt("CHIP_ID"), 
						resultSet.getString("CHIP_NAME"), 
						resultSet.getString("FUNCTIONS"), 
						resultSet.getString("QUERY_FREQ")
				);
				highFreqList.add(tmp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultSet);
		}
		return highFreqList;
	}
	
	/**
	 * @return
	 * 功能：计算各功能芯片所占比重并以hashmap返回
	 */
	public JSONArray computeAllFunctionsPercent(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultKeySet=null;
		ResultSet resultValueSet=null;
		String [] valueArr=null;
		String queryKeySQL="SELECT DISTINCT FUNCTIONS FROM CHIPS ";
		String queryValueSQL="SELECT COUNT(*) FROM CHIPS WHERE FUNCTIONS=? ";
		ArrayList<String> keySet=new ArrayList<>();
		ArrayList<String> valueSet=new ArrayList<>();
		HashMap<String, String> map=new HashMap<>();
		int index=0;
		JSONArray jsonArr=new JSONArray();
		try{
			connection=dbConn.connectToDatabase();
			preparedStatement=connection.prepareStatement(queryKeySQL);
			resultKeySet=preparedStatement.executeQuery();
			while(resultKeySet.next())
				keySet.add(resultKeySet.getString("FUNCTIONS"));
			for(String key : keySet){
				preparedStatement=connection.prepareStatement(queryValueSQL);
				preparedStatement.setString(1, key);
				resultValueSet=preparedStatement.executeQuery();
				while(resultValueSet.next())
					valueSet.add(resultValueSet.getString(1));
			}
			while(index<6){
				map.put("value", valueSet.get(index));
				map.put("label", keySet.get(index));
				jsonArr.add(JSONObject.fromObject(map));
				++index;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultValueSet);
			dbClose.closeQueryConnectionToDatabase(connection, preparedStatement, resultKeySet);
		}
		return jsonArr;
	}
	
	/**
	 * @return
	 * 功能：获取功能数目
	 */
	public String getFunctionNum(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String querySQL="select count(distinct functions) from chips";
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
