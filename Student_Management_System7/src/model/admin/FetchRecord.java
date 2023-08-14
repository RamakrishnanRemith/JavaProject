package model.admin;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import common.DataBaseConnection;
import common.User;

/**
 * FetchRecord
 * @author Prem kumar
 *
 */
public class FetchRecord extends ActionSupport implements HttpSessionListener, SessionAware, ServletRequestAware, ServletResponseAware{
	private static final long serialVersionUID = 1L;
	private String adminId,userName,mailId,dob,contactNo,hdnuserid,admin,userId,sessionadminId;
	HttpSession session;
	private int gender;
	HttpServletRequest request;
	private String hdnuserId;
	ArrayList<User> list=new ArrayList<User>();
	/**
	 * 
	 * method profileView
	 */
	public String profileView(){
		String status="ProfileViewFail";
		try {
			session =ServletActionContext.getRequest().getSession(false);
			String sessionId = (String)session.getAttribute("sessionId");
			java.sql.Connection con=DataBaseConnection.getConnection();
			String sql=null;
			if(userId == null && sessionId == null) {
				userId = (String)session.getAttribute("sessionadminId");
			} else if(userId == null && sessionId != null) {
				userId = sessionId;
			} else {
				userId=userId;
			}
			//select process
			sql="SELECT userName,adminId,mailId,dob,gender,contactNo FROM tbl_admin Where adminId='"+userId+"'";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ResultSet getValue=ps.executeQuery();
			while(getValue.next()) {
				User user=new User();
				user.setUserName(getValue.getString("userName"));
				user.setAdminId(getValue.getString("adminId"));
				user.setMailId(getValue.getString("mailId"));
				user.setHdnmailId(getValue.getString("mailId"));
				user.setDob(getValue.getString("dob"));
				user.setGender(getValue.getInt("gender"));
				user.setContactNo(getValue.getString("contactNo"));
				list.add(user);
				status="ProfileViewSuccess";
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		session.removeAttribute("sessionId");
		return status;
	}
	//method getUpdateData
	public String getUpdateData(){
		String status="Fail";
		final HttpServletRequest request=ServletActionContext.getRequest();
		final HashMap<Integer,String> genderRadioGroup=new HashMap<Integer,String>();
		genderRadioGroup.put(1,"Male");
		genderRadioGroup.put(2,"Female");
		request.setAttribute("genderList", genderRadioGroup);
		try {
			java.sql.Connection con=DataBaseConnection.getConnection();
			String sql=null;
			session =ServletActionContext.getRequest().getSession(false);
			if(hdnuserId == null) {
				sessionadminId = (String)session.getAttribute("sessionAdminId");
			} else {
				sessionadminId=hdnuserId;
			}
			//select process
			sql="SELECT userName,adminId,mailId,dob,gender,contactNo FROM tbl_admin Where adminId='"+sessionadminId+"'";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ResultSet getValue=ps.executeQuery();
			while (getValue.next()) {
				User user=new User();
				user.setUserName(getValue.getString("userName"));
				user.setAdminId(getValue.getString("adminId"));
				user.setMailId(getValue.getString("mailId"));
				user.setDob(getValue.getString("dob"));
				user.setGender(getValue.getInt("gender"));
				user.setContactNo(getValue.getString("contactNo"));
				list.add(user);
				status="success";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	/**
	 * 
	 * @return hdnuserId
	 */
	public String getHdnuserId()	{
		return hdnuserId;
	}
	/**
	 * 
	 * @param hdnUserId
	 */
	public void setHdnuserId (String hdnUserId)	{
		this.hdnuserId=hdnUserId;
	}
	/**
	 * 
	 * @return list
	 */
	public ArrayList<User>getList()	{
		return list;
	}
	/**
	 * 
	 * @param list
	 */
	public void setList (ArrayList<User>list)	{
		this.list=list;
	}
	/**
	 * 
	 * adminId
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
	 * userName
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
	 * mailId
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
	 * dob
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
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
	}
	/**
	 * @return the hdnuserid
	 */
	public String getHdnuserid() {
		return hdnuserid;
	}
	/**
	 * @param hdnuserid the hdnuserid to set
	 */
	public void setHdnuserid(String hdnuserid) {
		this.hdnuserid = hdnuserid;
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