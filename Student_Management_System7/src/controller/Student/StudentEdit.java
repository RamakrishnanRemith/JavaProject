package controller.Student;

import java.io.File;
import java.io.FileInputStream;
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

import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.eclipse.jdt.internal.compiler.batch.Main;

import com.opensymphony.xwork2.ActionSupport;

import common.DataBaseConnection;
import common.User;

/**
 * StudentEdit
 * @author Prem kumar
 *
 */
public class StudentEdit extends ActionSupport implements HttpSessionListener, SessionAware, ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private String studentMailId,studentId,studentName,mailId,dob,contactNo,hdnuserId,sessionstudentId,userId,address,pincode,imageContentType,userid;
	private int gender;
	HttpSession session,sessionName;
	SessionMap<String, Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	ArrayList<User> list=new ArrayList<User>();
	public String[] testArry;
	private File image;
	
	/**
	 * 
	 * method Update
	 */
	public String Update() {
		String status="fail",existingDataSql;;int exsitingFlag = 0;
		try {
			java.sql.Connection con=DataBaseConnection.getConnection();
			//Image path
			String FilePath = "C:/AdvanceJava/StudentManagementSystem/WebContent/uploads/StudentImage";
			String Imagename=studentId+".png";
			File ImageFileToUpdate = new File(FilePath,Imagename);
			if(getImage() != null) {
				FileUtils.copyFile(getImage(),ImageFileToUpdate);
			}
			FileInputStream finstream  = new FileInputStream(ImageFileToUpdate);
			//existing validation processs
			existingDataSql = "SELECT mailId FROM tbl_student WHERE mailId NOT IN (?)";
			java.sql.PreparedStatement pselect=con.prepareStatement(existingDataSql);
			pselect.setString(1,getStudentMailId());
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
			} 
			else {
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
				String UpdateSql="UPDATE tbl_student SET studentName='"+getStudentName()+"',gender='"+getGender()+"',dob='"+getDob()+"',contactNo='"+getContactNo()+"',mailId='"+getMailId()+"',updatedBy='"+getStudentName()+"',address='"+getAddress()+"',pincode='"+getPincode()+"',image='"+Imagename+"',imageBlob='"+finstream+"' WHERE studentId='"+getStudentId()+"'";
				PreparedStatement UpdateSqlps=con.prepareStatement(UpdateSql);
				int result=UpdateSqlps.executeUpdate();
				if(result==1) {
					status="success";
					//set session
					session = request.getSession(true);
					session.setAttribute("sessionId",getStudentId());
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
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	/**
	 * 
	 * @param request
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	 * @return userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 
	 * @param userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
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
	 * @return studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * 
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * 
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * 
	 * @param studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	/**
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 
	 * @return pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * 
	 * @param pincode
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/**
	 * 
	 * @return image
	 */
	public File getImage() {
		return image;
	}
	/**
	 * 
	 * @param image
	 */
	public void setImage(File image) {
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
	 * @return the studentMailId
	 */
	public String getStudentMailId() {
		return studentMailId;
	}
	/**
	 * @param studentMailId the studentMailId to set
	 */
	public void setStudentMailId(String studentMailId) {
		this.studentMailId = studentMailId;
	}
}