package common;

/**
 * User
 * @author Prem kumar
 *
 */
public class User {
	public String adminId,userName, mailId, dob, contactNo,teacherName,teacherId,address,pincode,imageContentType;
	int qualification;
	 private String image,batchName,batchId,startDate,endDate,hdnbatchName,addstuId,addTeaId,addteachId,addBatcId;
	
	public String getAddteachId() {
		return addteachId;
	}
	public void setAddteachId(String addteachId) {
		this.addteachId = addteachId;
	}
	public String getAddstuId() {
		return addstuId;
	}
	public void setAddstuId(String addstuId) {
		this.addstuId = addstuId;
	}
	public int gender,delflag;
	private int sno;
	private String studentId;
	private String studentName,studentMailId;
	private String courseId;
	public String courseName;
	private String hdncourseName;
	public String duration;
	private String fees,totalFees,paidFees,remainFees,teacherMailId,paidDate,totalpaidFees;
	private String hdnmailId;
	

	
	/**
	 * 
	 * @return hdnMailId
	 */
	public String getHdnmailId() {
		return hdnmailId;
	}
	/**
	 * 
	 * @param hdnmailId
	 */
	public void setHdnmailId(String hdnmailId) {
		this.hdnmailId = hdnmailId;
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
	 * @param sno
	 */
	public void setSno(int sno) {
		this.sno = sno;	
	}
	/**
	 * 
	 * @return sno
	 */
	public int getSno() {
		return sno;
	}
	/**
	 * 
	 * @param del
	 */
	public void setDelflag(int del) {
		this.delflag = del;	
	}
	/**
	 * 
	 * @return delflag
	 */
	public int getDelflag() {
		return delflag;
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
	 * @return teacherName
	 */
	public String getTeacherName() {
		return teacherName;
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
	 * @return qualification
	 */
	public int getQualification() {
		return qualification;
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
	 * @return teacherId
	 */
	public String getTeacherId() {
		return teacherId;
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
	 * @param string
	 */
	public void setImage(String string) {
		this.image = string;
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
	 * @return batchName
	 */
	public String getBatchName() {
		return batchName;
	}
	/**
	 * 
	 * @param batchName
	 */
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}	
	/**
	 * 
	 * @return batchId
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * 
	 * @param batchId
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * 
	 * @return startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * 
	 * @return endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * 
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * 
	 * @return courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * 
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}	
	/**
	 * 
	 * @return courseId
	 */
	public String getCourseId() {
		return courseId;
	}
	/**
	 * 
	 * @param courseId
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	/**
	 * 
	 * @return Duration
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * 
	 * @param Duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}	
	/**
	 * 
	 * @return courseId
	 */
	public String getFees() {
		return fees;
	}
	/**
	 * 
	 * @param Fees
	 */
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String[] split(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	public void append(User user) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return the totalFees
	 */
	public String getTotalFees() {
		return totalFees;
	}
	/**
	 * @param totalFees the totalFees to set
	 */
	public void setTotalFees(String totalFees) {
		this.totalFees = totalFees;
	}
	/**
	 * @return the paidFees
	 */
	public String getPaidFees() {
		return paidFees;
	}
	/**
	 * @param paidFees the paidFees to set
	 */
	public void setPaidFees(String paidFees) {
		this.paidFees = paidFees;
	}
	/**
	 * @return the remainFees
	 */
	public String getRemainFees() {
		return remainFees;
	}
	/**
	 * @param i the remainFees to set
	 */
	public void setRemainFees(String i) {
		this.remainFees = i;
	}
	/**
	 * @return the getTeacherMailId
	 */
	public String getTeacherMailId() {
		return teacherMailId;
	}
	/**
	 * @param getTeacherMailId the getTeacherMailId to set
	 */
	public void setTeacherMailId(String teacherMailId) {
		this.teacherMailId = teacherMailId;
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
	/**
	 * @return the hdnbatchName
	 */
	public String getHdnbatchName() {
		return hdnbatchName;
	}
	/**
	 * @param hdnbatchName the hdnbatchName to set
	 */
	public void setHdnbatchName(String hdnbatchName) {
		this.hdnbatchName = hdnbatchName;
	}
	/**
	 * @return the hdncourseName
	 */
	public String getHdncourseName() {
		return hdncourseName;
	}
	/**
	 * @param hdncourseName the hdncourseName to set
	 */
	public void setHdncourseName(String hdncourseName) {
		this.hdncourseName = hdncourseName;
	}
	public Object remainFees(String string, int int1) {
		// TODO Auto-generated method stub
		return null;
	}
	public void amt1(Object remainFees2, Object setDelflag) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return the paidDate
	 */
	public String getPaidDate() {
		return paidDate;
	}
	/**
	 * @param paidDate the paidDate to set
	 */
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	/**
	 * @return the totalpaidFees
	 */
	public String getTotalpaidFees() {
		return totalpaidFees;
	}
	/**
	 * @param totalpaidFees the totalpaidFees to set
	 */
	public void setTotalpaidFees(String totalpaidFees) {
		this.totalpaidFees = totalpaidFees;
	}
	public String getAddTeaId() {
		
		return addTeaId;
	} 
	public void setAddTeaId(String addteaId) {
		this.addTeaId = addteaId;
	}
	public String getAddBatcId() {
		return addBatcId;
	}
	public void setAddBatcId(String addBatcId) {
		this.addBatcId = addBatcId;
	}
	
	
}