package chipsmanager.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import chipsmanager.dbprocess.dbClose;
import chipsmanager.dbprocess.dbConn;
import chipsmanager.javabean.*;

public class ImportExcelToDatabase {
	
    public static final String EXCEL_PATH = "d:\\import.xls";
    
    public static final String INSERT_CHIP_SQL = "INSERT INTO CHIPS(MODEL_ID, CHIP_NAME, FUNCTIONS, PIN_NUMBER, PIN_DEFINATION,CHIP_INTRODUCTION) values(?,?,?, ?, ?, ?)";

    public List<Chips> readXls() throws IOException {
        InputStream is = new FileInputStream(EXCEL_PATH);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Chips chips = null;
        List<Chips> list = new ArrayList<Chips>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    chips = new Chips();
                    HSSFCell chip_name = hssfRow.getCell(0);
                    HSSFCell model_id = hssfRow.getCell(1);
                    HSSFCell function = hssfRow.getCell(2);
                    HSSFCell pin_number = hssfRow.getCell(3);
                    HSSFCell pin_def = hssfRow.getCell(4);
                    HSSFCell pin_intro = hssfRow.getCell(5);
                    chips.setChipName(getValue(chip_name));
                    chips.setModelID(getValue(model_id));
                    chips.setFunctions(getValue(function));
                    chips.setPinNumber(Integer.valueOf(getValue(pin_number)));
                    chips.setPinDefination(getValue(pin_def));
                    chips.setPinIntroduction(getValue(pin_intro));
                    list.add(chips);
                }
            }
        }
        return list;
    }
    
     @SuppressWarnings({ "static-access", "deprecation" })
    private String getValue(HSSFCell hssfCell) {
           try{
        	   if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                   return String.valueOf(hssfCell.getBooleanCellValue());
               } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            	   //避免出现带小数点的问题
               	   DecimalFormat df = new DecimalFormat("0");    
                   String strCell = df.format(hssfCell.getNumericCellValue());   
                   return strCell;
               } else {
                   return String.valueOf(hssfCell.getStringCellValue());
               }
        	   //34号芯片会抛一个很奇怪的空指针异常 暂未能发现原因 暂时用catch处理一下
           }catch(Exception ex){
        	   return "null";
           }
    }
     
     public static void insert(String sql, Chips chip) throws SQLException {
         Connection conn = null;
         PreparedStatement ps = null;
         try {
        	 conn=dbConn.connectToDatabase();
             ps = conn.prepareStatement(sql);
             ps.setString(1, chip.getModelID());
             ps.setString(2, chip.getChipName());
             ps.setString(3, chip.getFunctions());
             ps.setString(4, String.valueOf(chip.getPinNumber()));
             ps.setString(5, chip.getPinDefination());
             ps.setString(6, chip.getPinIntroduction());
             ps.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             dbClose.modifyConnectionClose(conn, ps);
         }
     }
     
     public void importData() {
		try {
			List<Chips> list=new ImportExcelToDatabase().readXls();
			for(int i=0;i<list.size();++i)
				try {
					insert(INSERT_CHIP_SQL, list.get(i));
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}