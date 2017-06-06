package chipsmanager.actions;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.tools.ExportDatabaseToExcel;

public class ExportDatabaseToExcelAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		new ExportDatabaseToExcel().export();
		return SUCCESS;
	}
}
