package chipsmanager.tools;

import java.awt.print.Book;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import jxl.Workbook;
import jxl.write.*;
import jxl.LabelCell;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.javabean.chips;

public class ExportOneChipToExcel extends ActionSupport{
	
	final static String filePath="C:/chipsExport";
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		
		chips tmp=(chips)request.getAttribute("chips");
		
		String fileName=tmp.getModelID()+tmp.getChipName()+" DataSheet";
		
		String pathName=filePath+fileName;
		
		WritableWorkbook wwb=Workbook.createWorkbook(new File(pathName));
		
		WritableSheet sheet=wwb.createSheet(tmp.getModelID(), 0);
				
		Label label_1=new Label(0, 0, "芯片ID");
		Label label_2=new Label(0, 1, "芯片型号");
		Label label_3=new Label(0, 2, "芯片名");
		Label label_4=new Label(0, 3, "芯片功能");
		Label label_5=new Label(0, 4, "管脚数");
		Label label_6=new Label(0, 5, "管脚定义");
		Label label_7=new Label(0, 6, "芯片介绍");
		

		Label model_id_label=new Label(1, 1, tmp.getModelID());
		Label chip_name_label=new Label(1, 2, tmp.getChipName());
		Label function_label=new Label(1, 3, tmp.getFunctions());
		Label pin_def_label=new Label(1, 5, tmp.getPinDefination());
		Label intro_label=new Label(1, 6, tmp.getPinIntroduction());
		
		sheet.addCell(label_1);
		sheet.addCell(label_2);
		sheet.addCell(label_3);
		sheet.addCell(label_4);
		sheet.addCell(label_5);
		sheet.addCell(label_6);
		sheet.addCell(label_7);
		
		sheet.addCell(model_id_label);
		sheet.addCell(chip_name_label);
		sheet.addCell(function_label);
		sheet.addCell(pin_def_label);
		sheet.addCell(intro_label);
		
		
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
		WritableCellFormat wcfF = new WritableCellFormat(wf);
		
		jxl.write.Number id_number=new jxl.write.Number(1,0,tmp.getChipID());
		jxl.write.Number pin_number=new jxl.write.Number(1,0,tmp.getPinNumber());
		
		wwb.write();
		wwb.close();
		
		return SUCCESS;
		
	}
}
