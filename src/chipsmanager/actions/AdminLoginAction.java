package chipsmanager.actions;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.AdminDataDao;
import chipsmanager.javabean.Admin;
import chipsmanager.tools.Md5Encode;

public class AdminLoginAction extends ActionSupport{
	private String adminID;
	private String adminPwd;
	/**
	 * @return the adminID
	 */
	public String getAdminID() {
		return adminID;
	}
	/**
	 * @param adminID the adminID to set
	 */
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	/**
	 * @return the adminPwd
	 */
	public String getAdminPwd() {
		return adminPwd;
	}
	/**
	 * @param adminPwd the adminPwd to set
	 */
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	
	@Override
	public void validate() {
		if(adminID.isEmpty()||adminPwd.isEmpty())
			this.addFieldError("logErr", "用户名或密码不能为空");
	}
	
	@Override
	public String execute() throws Exception {
		if(new AdminDataDao().validAdmin(adminID, Md5Encode.getMd5Code(adminPwd))){
			HttpServletResponse response=ServletActionContext.getResponse();
			Admin admin=new Admin(adminID, adminPwd);
			HttpSession session=ServletActionContext.getRequest().getSession();
			session.setAttribute("admin", admin);
			response.sendRedirect("admin.jsp");
			return SUCCESS;
		}else{
			this.addFieldError("logErr", "用户名或密码错误");
		}
		return ERROR;
	}
}
