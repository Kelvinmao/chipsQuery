package chipsmanager.dbprocess;
import java.sql.*;

/**
 * @author MaoKaining(ë����)
 * @version 1.0
 * Copyright (c) 2017,�����ʵ��ѧ�Ƽ����´�Ӫ
 * All rights reserved.
 * �� ��:��װ�ر����ݿ����Ӳ���
 *
 */
public class dbClose {
	static final String DB_URL="jdbc:mysql://localhost:3306/ChipsDatabase?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	static final String DB_USRNAME="root";
	static final String DB_PWD="L0ngl0ngag0=";
	
	/**
	 * @author MaoKaining(ë����)
	 * @param conn
	 * @param pstmt
	 * @param rs
	 * ���ܣ��ر����ݿ�����
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
