package controller.Teacher;

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

import com.opensymphony.xwork2.ActionSupport;

import common.DataBaseConnection;
import common.User;

/**
 * TeacherEdit
 * 
 * @author prem kumar
 *
 */
public class TeacherEdit extends ActionSupport implements HttpSessionListener,
		SessionAware, ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private String teacherId, teacherName, mailId, contactNo, address, pincode,
			hdnuserId;
	private String dob;
	private String imageContentType;
	private int gender, qualification;
	private File image;
	HttpSession session, sessionName;
	SessionMap<String, Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	ArrayList<User> list = new ArrayList<User>();
	public String[] testArry;
	private String userId, teacherMailId;

	/**
	 * 
	 * method Update
	 */
	public String Update() {
		String status = "fail", existingDataSql;
		int exsitingFlag = 0;
		;
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			//image path
			String FilePath = "C:/AdvanceJava/StudentManagementSystem/WebContent/uploads/image";
			String Imagename = teacherId + ".png";
			File ImageFileToUpdate = new File(FilePath, Imagename);
			if (getImage() != null) {
				FileUtils.copyFile(getImage(), ImageFileToUpdate);
			}
			FileInputStream finstream = new FileInputStream(ImageFileToUpdate);
			//existing validation
			existingDataSql = "SELECT mailId FROM tbl_teacher WHERE mailId NOT IN (?)";
			java.sql.PreparedStatement pselect = con
					.prepareStatement(existingDataSql);
			pselect.setString(1, getTeacherMailId());
			ResultSet rs = pselect.executeQuery();
			String testmail = null;
			while (rs.next()) {
				testmail = rs.getString("mailId");
				if ((testmail.equals(getMailId()))) {
					exsitingFlag = 1;
				}
			}
			if (exsitingFlag == 1) {
				JOptionPane.showMessageDialog(null, "MailID is Already Exist",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				//21 years validation
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 21);
				String[] date = getDob().split("-");
				int year = Integer.parseInt(date[0]);
				int month = Integer.parseInt(date[1]);
				int day = Integer.parseInt(date[2]);
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, year);
				cal1.set(Calendar.MONTH, month);
				cal1.set(Calendar.DAY_OF_MONTH, day);
				boolean flag = calendar.getTime().before(cal1.getTime());
				if (flag == false) {
					//update process
					String UpdateSql = "UPDATE tbl_teacher SET teacherName='"
							+ getTeacherName() + "',qualification='"
							+ getQualification() + "',gender='" + getGender()
							+ "',dob='" + getDob() + "',mailId='" + getMailId()
							+ "',contactNo='" + getContactNo() + "',address='"
							+ getAddress() + "',pincode='" + getPincode()
							+ "',image='" + Imagename + "',imageBlob='"
							+ finstream + "',updatedBy='" + getTeacherName()
							+ "' WHERE teacherId='" + getTeacherId() + "'";
					PreparedStatement UpdateSqlps = con
							.prepareStatement(UpdateSql);
					int result = UpdateSqlps.executeUpdate();
					if (result == 1) {
						status = "success";
						//set session
						session = request.getSession(true);
						session.setAttribute("sessionId", getTeacherId());
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Please Enter Date Above 20 Years", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		} catch (Exception e) {
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
	 * @return teacherId
	 */
	public String getTeacherId() {
		return teacherId;
	}

	/**
	 * 
	 * @param teacherId
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * 
	 * @return teacherName
	 */
	public String getTeacherName() {
		return teacherName;
	}

	/**
	 * 
	 * @param teacherName
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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
	 * @return qualification
	 */
	public int getQualification() {
		return qualification;
	}

	/**
	 * 
	 * @param qualification
	 */
	public void setQualification(int qualification) {
		this.qualification = qualification;
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
		this.imageContentType = imageContentType;
	}

	public String getHdnuserId() {
		return hdnuserId;
	}

	public void setHdnuserId(String hdnuserId) {
		this.hdnuserId = hdnuserId;
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
		sessionMap.put("sessionUser", teacherId);
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
	 * @return the teacherMailId
	 */
	public String getTeacherMailId() {
		return teacherMailId;
	}

	/**
	 * @param teacherMailId
	 * the teacherMailId to set
	 */
	public void setTeacherMailId(String teacherMailId) {
		this.teacherMailId = teacherMailId;
	}
}