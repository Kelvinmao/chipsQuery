package chipsmanager.javabean;

public class Admin {
	public Admin(String adminID, String adminPwd) {
		super();
		this.adminID = adminID;
		this.adminPwd = adminPwd;
	}
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
}
