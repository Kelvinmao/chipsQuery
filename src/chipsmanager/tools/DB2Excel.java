package chipsmanager.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import chipsmanager.dbprocess.dbConn;

public class DB2Excel {
	public List<Map<String, Object>> Query4List() throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from chips";
		Connection conn = dbConn.connectToDatabase();
		PreparedStatement prep = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet resu = prep.executeQuery();
		ResultSetMetaData md = (ResultSetMetaData) resu.getMetaData(); // ��ý�����ṹ��Ϣ,Ԫ����
		int columnCount = md.getColumnCount(); // �������
		while (resu.next()) {
			Map<String, Object> rowData = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) 
				rowData.put(md.getColumnName(i), resu.getObject(i));
			list.add(rowData);
		}
		return list;
	}

	public void DB2Excels(List<Map<String, Object>> list) throws SQLException, IOException {
		String filePath = "d:\\sample.xls";// �ļ�·��
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����������(Sheet)
		HSSFSheet sheet = workbook.createSheet();
		sheet = workbook.createSheet("Chips");
		// ������,��0��ʼ
		int rownum = 1;
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);// �����еĵ�Ԫ��,Ҳ�Ǵ�0��ʼ
		cell.setCellValue("оƬ����");// ���õ�Ԫ������
		row.createCell(1).setCellValue("оƬ�ͺ�");// ���õ�Ԫ������,����
		row.createCell(2).setCellValue("оƬ����");
		row.createCell(3).setCellValue("�ܽ���");
		row.createCell(4).setCellValue("�ܽŶ���");
		row.createCell(5).setCellValue("оƬ����");
		DB2Excel db = new DB2Excel();
		for (Map<String, Object> maps : list) {
			row = sheet.createRow(rownum++);
			row.createCell(0).setCellValue(maps.get("CHIP_NAME").toString());
			row.createCell(1).setCellValue(maps.get("MODEL_ID").toString());
			row.createCell(2).setCellValue(maps.get("FUNCTIONS").toString());
			row.createCell(3).setCellValue(maps.get("PIN_NUMBER").toString());
			row.createCell(4).setCellValue(maps.get("PIN_DEFINATION").toString());
			row.createCell(5).setCellValue(maps.get("CHIP_INTRODUCTION").toString());
		}
		FileOutputStream out = new FileOutputStream(filePath);
		workbook.write(out);// ����Excel�ļ�
		out.close();// �ر��ļ���
	}
	public static void main(String[] args) throws SQLException, IOException {
		DB2Excel db = new DB2Excel();
		List<Map<String, Object>> list = db.Query4List();
		db.DB2Excels(list);	
	}
}