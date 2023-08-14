package Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import common.DataBaseConnection;
import common.User;

/**
 * AdminEdit
 * @author Prem kumar
 *
 */
public class AdminEdit extends ActionSupport implements HttpSessionListener, SessionAware, ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private String adminId,userName,mailId,dob,contactNo,hdnuserId,admin,hdnmailId;
	private int gender;
	HttpSession session,sessionName;
	SessionMap<String, Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	ArrayList<User> list=new ArrayList<User>();
	public String[] testArry;
	private String userId;
	private String existingDataSql;
	
	/**
	 * 
	 * @return status
	 */
	public String Update() {
		String status="fail";int exsitingFlag = 0;
		try {
			java.sql.Connection con=DataBaseConnection.getConnection();
			//existing validation
			existingDataSql = "SELECT mailId FROM tbl_admin WHERE mailId NOT IN (?)";
			java.sql.PreparedStatement pselect=con.prepareStatement(existingDataSql);
			pselect.setString(1,getHdnmailId());
			ResultSet rs=pselect.executeQuery();
			String testmail = null;
			while (rs.next()) {
				testmail=rs.getString("mailId");
				if((testmail.equals(getMailId()))) {
					exsitingFlag=1;
				}
			}
			if(exsitingFlag==1) {
				JOptionPane.showMessageDialog(null, "MailID is Already Exist","Error",JOptionPane.ERROR_MESSAGE);
			} else {
				//21 years validation
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-21);
				String[] date =getDob().split("-");
				int year =Integer.parseInt(date[0]);
				int month =Integer.parseInt(date[1]);
				int day =Integer.parseInt(date[2]);
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, year);
				cal1.set(Calendar.MONTH, month);
				cal1.set(Calendar.DAY_OF_MONTH, day);
				boolean flag = calendar.getTime().before(cal1.getTime());
				if (flag==false) {
					//Update Process
					String UpdateSql="UPDATE tbl_admin SET userName='"+getUserName()+"',gender='"+getGender()+"',dob='"+getDob()+"',contactNo='"+getContactNo()+"',mailId='"+getMailId()+"',updatedBy='"+getUserName()+"' WHERE adminId='"+getAdminId()+"'";
					PreparedStatement UpdateSqlps=con.prepareStatement(UpdateSql);
					int result=UpdateSqlps.executeUpdate();
					if(result==1){
						status="success";
						//set session Id
						session = request.getSession(true);
						session.setAttribute("sessionId",getAdminId());
					} 
				
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter Above 20 Years","Error",JOptionPane.ERROR_MESSAGE);
				}
			} 
		} catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	/**
	 * 
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 
	 * @return list
	 */
	public ArrayList<User> getList() {
		return list;
	}
	/**
	 * 
	 * @param list
	 */
	public void setList(ArrayList<User> list) {
		this.list = list;
	}
	/**
	 * 
	 * @return adminId
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * 
	 * @param adminId
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 * @return mailId
	 */
	public String getMailId() {
		return mailId;
	}
	/**
	 * 
	 * @param mailId
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	/**
	 * 
	 * @return dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * 
	 * @param dob
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	/**
	 * 
	 * @return contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}
	/**
	 * 
	 * @param contactNo
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/**
	 * 
	 * @return gender
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * 
	 * @param gender
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * 
	 * @return hdnuserId
	 */
	public String getHdnuserId() {
		return hdnuserId;
	}
	/**
	 * 
	 * @param hdnuserId
	 */
	public void setHdnuserId(String hdnuserId) {
		this.hdnuserId = hdnuserId;
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
	 * @return the hdnmailId
	 */
	public String getHdnmailId() {
		return hdnmailId;
	}
	/**
	 * @param hdnmailId the hdnmailId to set
	 */
	public void setHdnmailId(String hdnmailId) {
		this.hdnmailId = hdnmailId;
	}
	/**
	 * @return the admin
	 */
	public String getAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}
}