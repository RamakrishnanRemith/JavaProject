package controller.Student;

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
public class StudentLogin extends ActionSupport implements HttpSessionListener, SessionAware, ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpSession session,sessionName;
	SessionMap<String, Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	private String studentId,password,studentName;
	
	/**
	 * 
	 * method Login
	 */
	public String Login() {
	String status = "error";
		try {
			Connection con = DataBaseConnection.getConnection();
			//select process
			String password =new String(CommonFunction.generateMd5(getPassword()));
			String sql = "SELECT studentId,password,studentName FROM tbl_student WHERE delflag = 0 AND (studentId = '"+getStudentId()+"' OR studentName = '"+getStudentId()+"' OR mailId = '"+getStudentId()+"') AND password = '"+password+"'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//set session
				session = request.getSession(true);
				session.setMaxInactiveInterval(1*60);
				session.setAttribute("sessionstudentId", rs.getString("studentId"));
				session.getAttribute("sessionstudentId");
				session.setAttribute("sessionstudentName", rs.getString("studentName"));
				session.getAttribute("sessionstudentName");
				session.setAttribute("sessionuser","Student1");
				session.getAttribute("sessionuser");
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
		sessionMap.put("sessionUser",studentId);
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
		if(studentId.length()<1)addFieldError("studentId","Please Enter The UserName");
		else if(studentId.length()<5)addFieldError("studentId","UserName Has Maximum Five(5) Letters");
		else if(password.length()<1)addFieldError("password","Please Enter The password");
	}
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}