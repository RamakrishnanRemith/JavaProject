package controller.Student;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import common.CommonFunction;
import common.DataBaseConnection;
/**
 * StudentRegisterAction
 * @author Prem Kumar
 *
 */
public class StudentRegisterAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String studentId,studentName,mailId,contactNo,address,pincode,dob,imageContentType,confirmPassword,password;
	private int gender,qualification;
	private File image;
	
	/**
	 * 
	 * method studentRegister
	 */
	public String studentRegister() {
		int statusSuccess=0, delFlag = 0,exsitingFlag = 0;
		String status="fail", insertSql, existingDataSql,sql; 
		String formate = "2001-01-01";
		String password =new String(CommonFunction.generateMd5(getPassword()));
		try {
			//Existing validation
			java.sql.Connection con=DataBaseConnection.getConnection();
			existingDataSql = "SELECT id,studentName,mailId FROM tbl_student WHERE delFlag = 0";
			java.sql.PreparedStatement pselect=con.prepareStatement(existingDataSql);
			int id= 1;
			String label="STU";
			ResultSet rs=pselect.executeQuery();
			String testmail = null;
			while (rs.next()){
				testmail=rs.getString("mailId");
				if((testmail.equals(getMailId()))) {
					exsitingFlag=1;
				}
			}
			if(exsitingFlag==0) {
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
				sql = "SELECT id FROM tbl_student ORDER BY id DESC Limit 1";
				java.sql.PreparedStatement pselect1=con.prepareStatement(sql);
				ResultSet rs1=pselect1.executeQuery();
				//ID generation
				if(rs1.next()) {
					id=rs1.getInt("id") + 1;
				}
				String studentId=String.format(label+"%05d",id);
				//image path
				String FilePath = "C:/AdvanceJava/StudentManagementSystem/WebContent/uploads/StudentImage";
				String Imagename=studentId+".png";
				File ImageFileToCreate = new File(FilePath,Imagename);
				FileUtils.copyFile(getImage(),ImageFileToCreate);
				FileInputStream finstream = new FileInputStream(ImageFileToCreate);
				//insert process
				if(getPassword().equals(getConfirmPassword())) {
					insertSql = "INSERT INTO tbl_student(studentId,studentName,gender,dob,mailId,contactNo,address,pincode,delflag,image,imageBlob,password)VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps=con.prepareStatement(insertSql);
					ps.setString (1, studentId);
					ps.setString (2, getStudentName());
					ps.setInt (3, getGender());
					ps.setString (4, getDob());
					ps.setString (5, getMailId());
					ps.setString (6, getContactNo());
					ps.setString (7, getAddress());
					ps.setString (8, getPincode());
					ps.setInt (9, delFlag);
					ps.setString(10,Imagename);
					ps.setBinaryStream(11,finstream,finstream.available());
					ps.setString (12, password);
					statusSuccess=ps.executeUpdate(); 
					if (statusSuccess == 1) {
						status="success";
					}
					else {
						System.out.println("error");
					} 
				} else {
					JOptionPane.showMessageDialog(null, "Password And Confirm Password Are Not Same","Error",JOptionPane.ERROR_MESSAGE);
				} 
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter Above 20 Years","Error",JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "MailID is Already Exist","Error",JOptionPane.ERROR_MESSAGE);
			} 
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
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
	 * @return StudentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * 
	 * @param StudentName
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
	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * 
	 * @param confirmPassword
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}