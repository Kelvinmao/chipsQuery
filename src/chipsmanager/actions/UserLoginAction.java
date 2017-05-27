package chipsmanager.actions;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.opensymphony.xwork2.ActionSupport;

import chipsmanager.dao.UserDataDao;
import chipsmanager.javabean.User;
import chipsmanager.tools.Md5Encode;

public class UserLoginAction extends ActionSupport{
	private String id;
	private String password;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void validate() {
		if(id.isEmpty()||password.isEmpty())
			this.addFieldError("logErr", "用户名和密码不能为空");
	}
	
	@Override
	public String execute() throws Exception {
		if(new UserDataDao().validUser(id, password)){
			HttpServletResponse response=ServletActionContext.getResponse();
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			String usr_name=new UserDataDao().getUserByID(id).getUserName();
			session.setAttribute("user_name", usr_name);
			
			Cookie userID=new Cookie("user_id", URLEncoder.encode(id, "utf-8"));
			Cookie userPwd=new Cookie("user_pwd",Md5Encode.getMd5Code(password));
			response.addCookie(userID);
			response.addCookie(userPwd);
			response.sendRedirect("index.jsp");
			return SUCCESS;
		}
		else{
			this.addFieldError("logErr", "用户名和密码错误");
			return ERROR;
		}
	}
}
