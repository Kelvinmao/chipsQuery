package chipsmanager.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.tools.ImportExcelToDatabase;

public class UploadFileAction extends ActionSupport{
	private File upload;
	private String uploadFileName;    
    private String uploadContentType;
    
    /**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}
	/**
	 * @param upload the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}
	/**
	 * @param uploadContentType the uploadContentType to set
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	//文件上传不能单写成一个函数！！否则会抛空指针异常！！
	@Override
	public String execute() throws Exception {
		File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("/upload/excel/"));
		if(!uploadFile.exists())
			uploadFile.mkdirs();
		FileInputStream inputStream=new FileInputStream(upload);
		FileOutputStream outputStream=new FileOutputStream(uploadFile+"//"+uploadFileName);
		try{
			byte [] buffer=new byte[1024];
			int len=0;
			while((len=inputStream.read(buffer))>0){
				outputStream.write(buffer,0,len);
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			closeStream(inputStream, outputStream);
		}
		//上传成功，开始写入
		ImportExcelToDatabase importer=new ImportExcelToDatabase();
		importer.setDataPath(uploadFile+"\\"+uploadFileName);
		importer.importData();
		return SUCCESS;
	}
	
	/**
	 * @author MaoKaining(毛凯宁)
	 * @param fInputStream
	 * @param fOutputStream
	 * 功能：封装流对象关闭操作
	 */
	private void closeStream(FileInputStream fInputStream,FileOutputStream fOutputStream){
		try{
			fInputStream.close();
			fOutputStream.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
		
}
