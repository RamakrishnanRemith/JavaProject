package controller.Teacher;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.DateFormatter;

import com.opensymphony.xwork2.ActionSupport;

import common.CommonFunction;
import common.DataBaseConnection;
/**
 * TaecherRegisterAction
 * @author Prem Kumar
 * 
 * 
 *
 */
public class TeacherRegisterAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String teacherId,teacherName,mailId,contactNo,address,pincode;
	private String dob;
	private String imageContentType;
	private int gender,qualification;
	private File image;
	/**
	 * 
	 * method teacherRegister
	 */
	public String teacherRegister() {
		int statusSuccess=0, delFlag = 0,exsitingFlag = 0;
		String status="fail", insertSql, existingDataSql,sql; 
		String formate = "2001-01-01";
		try {
			//existing validation
			java.sql.Connection con=DataBaseConnection.getConnection();
			existingDataSql = "SELECT id,teacherName,mailId FROM tbl_teacher WHERE delFlag = 0";
			java.sql.PreparedStatement pselect=con.prepareStatement(existingDataSql);
			int id= 1;
			String label="T";
			ResultSet rs=pselect.executeQuery();
			String testmail = null;
			while (rs.next()){
				testmail=rs.getString("mailId");
				if((testmail.equals(getMailId()))) {
					exsitingFlag=1;
				}
			}
			if(exsitingFlag==0)	{
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
				sql = "SELECT id FROM tbl_teacher ORDER BY id DESC Limit 1";
				java.sql.PreparedStatement pselect1=con.prepareStatement(sql);
				ResultSet rs1=pselect1.executeQuery();
				//ID generation
				if(rs1.next()) 
				{
					id=rs1.getInt("id") + 1;
				}
				String teacherId=String.format(label+"%05d",id);
				//image path
				String FilePath = "C:/AdvanceJava/StudentManagementSystem/WebContent/uploads/image";
				String Imagename=teacherId+".png";
				File ImageFileToCreate = new File(FilePath,Imagename);
				FileUtils.copyFile(getImage(),ImageFileToCreate);
				FileInputStream finstream = new FileInputStream(ImageFileToCreate);
				//insert process
				insertSql = "INSERT INTO tbl_teacher(teacherId,teacherName,qualification,gender,dob,mailId,contactNo,address,pincode,delflag,image,imageBlob)VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(insertSql);
				ps.setString (1, teacherId);
				ps.setString (2, getTeacherName());
				ps.setInt (3, getQualification());
				ps.setInt (4, getGender());
				ps.setString (5, getDob());
				ps.setString (6, getMailId());
				ps.setString (7, getContactNo());
				ps.setString (8, getAddress());
				ps.setString (9, getPincode());
				ps.setInt (10, delFlag);
				ps.setString(11,Imagename);
				ps.setBinaryStream(12,finstream,finstream.available());
				statusSuccess=ps.executeUpdate();
				if (statusSuccess == 1) {
					status="success";
				}
				else {
					System.out.println("error");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please Enter Date Above 20 Years","Error",JOptionPane.ERROR_MESSAGE);
			}
			}else {
				JOptionPane.showMessageDialog(null, "MailID or UserName is Already Exist","Error",JOptionPane.ERROR_MESSAGE);
			} 
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
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
	this.imageContentType=imageContentType;
	}
}