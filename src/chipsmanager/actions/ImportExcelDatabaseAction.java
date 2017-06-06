package chipsmanager.actions;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.tools.ImportExcelToDatabase;

public class ImportExcelDatabaseAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		new ImportExcelToDatabase().importData();
		return SUCCESS;
	}
}
