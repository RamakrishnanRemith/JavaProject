package model.teacher;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
 * TeacherSingleView
 * @author prem kumar
 *
 */
public class TeacherSingleView extends ActionSupport implements HttpSessionListener, SessionAware, ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	HttpSession session;
	HttpServletRequest request;
	private String hdnuserId,sessionteacherId,userId;
	ArrayList<User> list=new ArrayList<User>();
	private int gender;
	private String contactNo;
	private String dob;
	private String mailId;
	private String teacherName;
	private String teacherId;
	private String address;
	private String pincode;
	private int qualification;
	private String image;
	private String imageContentType;
	
	/**
	 * 
	 * method ProfileView
	 */
	public String ProfileView(){
		String status="ProfileFail";
		try{
			java.sql.Connection con=DataBaseConnection.getConnection();
			String sql=null;
			session =ServletActionContext.getRequest().getSession(false);
			if(userId == null) {
				sessionteacherId = (String)session.getAttribute("sessionId");
			} else {
				sessionteacherId=userId;
			}
			sql="SELECT * FROM tbl_teacher Where teacherId='"+sessionteacherId+"'";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ResultSet getValue=ps.executeQuery();
			while(getValue.next()){
				User user=new User();
				user.setTeacherId(getValue.getString("teacherId"));
				user.setTeacherName(getValue.getString("teacherName"));
				user.setQualification(getValue.getInt("qualification"));
				user.setGender(getValue.getInt("gender"));
				user.setDob(getValue.getString("dob"));
				user.setMailId(getValue.getString("mailId"));
				user.setContactNo(getValue.getString("contactNo"));
				user.setAddress(getValue.getString("address"));
				user.setPincode(getValue.getString("pincode"));
				user.setImage(getValue.getString("image"));
				user.setImageContentType(getValue.getString("imageBlob"));
				list.add(user);
				status="ProfileSuccess";
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	/*
	 * method getUpdateData
	 */
	public String getUpdateData(){
		String status="UpdateDataFail";
		final HttpServletRequest request=ServletActionContext.getRequest();
		final HashMap<Integer,String> genderRadioGroup=new HashMap<Integer,String>();
		genderRadioGroup.put(1,"Male");
		genderRadioGroup.put(2,"Female");
		request.setAttribute("genderList", genderRadioGroup);
		try{
			java.sql.Connection con=DataBaseConnection.getConnection();
			session =ServletActionContext.getRequest().getSession(false);
			String sql="SELECT * FROM tbl_teacher Where teacherId='"+userId+"'";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet getValue=ps.executeQuery();
			while (getValue.next()){
				User user=new User();
				user.setTeacherId(getValue.getString("teacherId"));
				user.setTeacherName(getValue.getString("teacherName"));
				user.setQualification(getValue.getInt("qualification"));
				user.setGender(getValue.getInt("gender"));
				user.setDob(getValue.getString("dob"));
				user.setMailId(getValue.getString("mailId"));
				user.setTeacherMailId(getValue.getString("mailId"));
				user.setContactNo(getValue.getString("contactNo"));
				user.setAddress(getValue.getString("address"));
				user.setPincode(getValue.getString("pincode"));
				user.setImage(getValue.getString("image"));
				user.setImageContentType(getValue.getString("imageBlob"));
				list.add(user);
				status="UpdateDataSuccess";
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	public ArrayList<User> getList() {
		return list;
	}
	public void setList(ArrayList<User> list) {
		this.list = list;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getHdnuserid() {
		return hdnuserId;
	}
	public void setHdnuserid(String hdnuserId) {
		this.hdnuserId = hdnuserId;
	}	
	public int getQualification() {
		return qualification;
	}
	public void setQualification(int qualification) {
		this.qualification = qualification;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 
	 * @return image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 
	 * @return imageContentType
	 */
	public String getImageContentType() {
		return imageContentType;
	}
	/**
	 * 
	 * @param imageContentType
	 */
	public void setImageContentType(String imageContentType) {
	this.imageContentType=imageContentType;
	}
		@Override
		public void setServletResponse(HttpServletResponse arg0) {
		}
		@Override
		public void setServletRequest(HttpServletRequest arg0) {
		}
		@Override
		public void setSession(Map<String, Object> arg0) {
		}
		@Override
		public void sessionCreated(HttpSessionEvent arg0) {
		}
		@Override
		public void sessionDestroyed(HttpSessionEvent arg0) {
		}
}