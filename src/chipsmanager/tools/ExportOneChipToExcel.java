package chipsmanager.tools;

import java.io.File;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import jxl.Workbook;
import jxl.write.*;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.Chips;

public class ExportOneChipToExcel extends ActionSupport{
	
	private String chipID;
	final static String filePath="C:/chipsExport";
	
	public String getChipID() {
		return chipID;
	}
	public void setChipID(String chipID) {
		this.chipID = chipID;
	}
	
	@Override
	public String execute() throws Exception {
		
		Chips tmp=new chipsDataDao().searchChipsByChipId(chipID);
		
		String fileName=tmp.getModelID()+tmp.getChipName()+"DataSheet.xls";
		
		String pathName=filePath+fileName;
		
		File file=new File(pathName);
		
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		
		WritableWorkbook wwb=Workbook.createWorkbook(new File(pathName));
		
		WritableSheet sheet=wwb.createSheet(tmp.getModelID(), 0);
				
		Label label_1=new Label(0, 0, "Ð¾Æ¬ID");
		Label label_2=new Label(0, 1, "Ð¾Æ¬ÐÍºÅ");
		Label label_3=new Label(0, 2, "Ð¾Æ¬Ãû");
		Label label_4=new Label(0, 3, "Ð¾Æ¬¹¦ÄÜ");
		Label label_5=new Label(0, 4, "¹Ü½ÅÊý");
		Label label_6=new Label(0, 5, "¹Ü½Å¶¨Òå");
		Label label_7=new Label(0, 6, "Ð¾Æ¬½éÉÜ");
		
		Label chip_id_label=new Label(1, 0, String.valueOf(tmp.getChipID()));
		Label model_id_label=new Label(1, 1, tmp.getModelID());
		Label chip_name_label=new Label(1, 2, tmp.getChipName());
		Label function_label=new Label(1, 3, tmp.getFunctions());
		Label pin_num_label=new Label(1, 4, String.valueOf(tmp.getPinNumber()));
		Label pin_def_label=new Label(1, 5, tmp.getPinDefination());
		Label intro_label=new Label(1, 6, tmp.getPinIntroduction());
		
		sheet.addCell(label_1);
		sheet.addCell(label_2);
		sheet.addCell(label_3);
		sheet.addCell(label_4);
		sheet.addCell(label_5);
		sheet.addCell(label_6);
		sheet.addCell(label_7);
		
		sheet.addCell(chip_id_label);
		sheet.addCell(model_id_label);
		sheet.addCell(chip_name_label);
		sheet.addCell(function_label);
		sheet.addCell(pin_num_label);
		sheet.addCell(pin_def_label);
		sheet.addCell(intro_label);
		
		wwb.write();
		wwb.close();
		
		
		return SUCCESS;
		
	}
	
}
