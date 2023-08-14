package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.swing.JOptionPane;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import common.CommonFunction;
import common.DataBaseConnection;

/**
 * LoginAction
 * @author Prem Kumar
 *
 */
public class LoginAction extends ActionSupport implements HttpSessionListener, SessionAware, ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpSession session,sessionName;
	SessionMap<String, Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	private String adminId,password,userName;
	public String Login() {
		String status = "error";
		try {
			Connection con = DataBaseConnection.getConnection();
			//select process
			String password =new String(CommonFunction.generateMd5(getPassword()));
			String sql = "SELECT adminId,password,userName  FROM tbl_admin WHERE delflag = 0 AND (adminId = '"+getAdminId()+"' OR userName = '"+getAdminId()+"' OR mailId = '"+getAdminId()+"') AND password = '"+password+"'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//set session
				session = request.getSession(true);
				session.setMaxInactiveInterval(1000000);
				session.setAttribute("sessionadminId", rs.getString("adminId"));
				session.getAttribute("sessionadminId");
				session.setAttribute("sessionuserName", rs.getString("userName"));
				session.getAttribute("sessionuserName");
				session.setAttribute("sessionuser","Admin");
				session.getAttribute("sessionuser");
				session.removeAttribute("sessionstudentId");
				status = "success";
			} else { 
				JOptionPane.showMessageDialog(null, "Incorrect MailID Or Password","Error",JOptionPane.ERROR_MESSAGE);
			}
		} catch(Exception e) {
			System.out.println(e); 
		}
		return status;	
	}
	/**
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param adminId
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 * @return userName
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setMaxInactiveInterval(1*60);
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {	
	}
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>)map;
		sessionMap.put("sessionUser",adminId);
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	/**
	 * validation process
	 */
	public void validate(){
		if(adminId.length()<1)addFieldError("adminId","Please Enter The UserName");
		else if(adminId.length()<5)addFieldError("adminId","UserName Has Maximum Five(5) Letters");
		else if(password.length()<1)addFieldError("password","Please Enter The password");
	}
}