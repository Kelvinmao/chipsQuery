package chipsmanager.dbprocess;
import java.sql.*;

import org.eclipse.jdt.internal.compiler.batch.Main;

/**
 * @author MaoKaining(ë����)
 * @version 1.0
 * Copyright (c) 2017,�����ʵ��ѧ�Ƽ����´�Ӫ
 * All rights reserved.
 * �� ��:��װ���ݿ����Ӳ���
 *
 */
public class dbConn {
	static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/ChipsDatabase?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	static final String ROOT_USRNAME="root";
	static final String ROOT_PWD="L0ngl0ngag0=";
	public static Connection connectToDatabase(){
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(DB_URL,ROOT_USRNAME,ROOT_PWD);
		}catch(ClassNotFoundException ex){
			System.out.println(ex.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
