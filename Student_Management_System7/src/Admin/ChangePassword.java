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

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import common.DataBaseConnection;

/**
 * Change Password
 * 
 * @author Prem Kumar
 *
 */
public class ChangePassword extends ActionSupport implements
		HttpSessionListener, SessionAware, ServletRequestAware,
		ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpSession session, sessionName, sessionadminId;
	SessionMap<String, Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	private String adminId, password, newPassword, confirmPassword;

	/*
	 * method changePassword
	 */
	public String changePassword() {
		String status = "error";
		int flag = 1;
		try {
			Connection con = DataBaseConnection.getConnection();
			// select process
			String sql = "SELECT password  FROM tbl_admin WHERE delflag = 0 AND password = '"
					+ getPassword() + "' AND adminId = '" + getAdminId() + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = 0;
			} else {
				JOptionPane.showMessageDialog(null,
						"Incorrect Current Password", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			if (flag == 0) {
				if (getNewPassword().equals(getConfirmPassword())) {
					Connection conn = DataBaseConnection.getConnection();
					// update process
					String sql1 = "UPDATE tbl_admin SET password = '"
							+ getNewPassword() + "' WHERE adminId = '"
							+ getAdminId() + "'";
					PreparedStatement pst = conn.prepareStatement(sql1);
					pst.executeUpdate();
					if (pst.executeUpdate() == 1) {
						session = request.getSession(true);
						session.setMaxInactiveInterval(1 * 60);
						session.setAttribute("sessionadminId", getAdminId());
						session.getAttribute("sessionadminId");
						status = "success";
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"New Password And Confirm Password Are Not Same",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	/**
	 * @param adminId
	 */
	public String getAdminId() {
		return adminId;
	}

	/**
	 * @return adminId
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return ChangePassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param ChangePassword
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
		event.getSession().setMaxInactiveInterval(1 * 60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
		sessionMap.put("sessionUser", adminId);
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * validation process
	 */
	public void validate() {
		if (password.length() < 1)
			addFieldError("password", "Please Enter The Current Password");
		else if (newPassword.length() < 1)
			addFieldError("newPassword", "Please Enter The New Password");
		else if (confirmPassword.length() < 1)
			addFieldError("confirmPassword",
					"Please Enter The Confrim password");
	}
}