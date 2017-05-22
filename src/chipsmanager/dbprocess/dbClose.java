package chipsmanager.dbprocess;
import java.sql.*;

/**
 * @author MaoKaining(毛凯宁)
 * @version 1.0
 * Copyright (c) 2017,北京邮电大学科技创新大本营
 * All rights reserved.
 * 功 能:封装关闭数据库连接操作
 *
 */
public class dbClose {
	static final String DB_URL="jdbc:mysql://localhost:3306/ChipsDatabase?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	static final String DB_USRNAME="root";
	static final String DB_PWD="L0ngl0ngag0=";
	
	/**
	 * @author MaoKaining(毛凯宁)
	 * @param conn
	 * @param pstmt
	 * @param rs
	 * 功能：关闭数据库连接
	 */
	public static void closeQueryConnectionToDatabase(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}else if(pstmt!=null){
			try{
				pstmt.close();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}else if(rs!=null){
			try{
				rs.close();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public static void modifyConnectionClose(Connection conn,PreparedStatement pstmt){
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}else if(pstmt!=null){
			try{
				pstmt.close();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
	}
}
