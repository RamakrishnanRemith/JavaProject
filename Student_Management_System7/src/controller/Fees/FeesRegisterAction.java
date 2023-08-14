package controller.Fees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.Fees.FeesRegisterActionDAO;
import model.Fees.Select;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;

import common.DataBaseConnection;

public class FeesRegisterAction extends ActionSupport implements
		HttpSessionListener, SessionAware, ServletRequestAware,
		ServletResponseAware {

	private static final long serialVersionUID = 1L;
	public List<Select> courseNameList = new ArrayList<Select>();
	public ArrayList<Select> batchNameList = new ArrayList<Select>();
	public ArrayList<Select> studentNameList = new ArrayList<Select>();
	public String courseName, studentId, studentName, totalfees, paidfees,
			paydate;
	public int delFlag;
	public String batchName, batchId, json, json11, json1111, fees;
	JSONArray jsonarray = new JSONArray();
	public List<Select> courseNames;
	public static HttpSession session;
	public HttpServletRequest request;
	HttpServletResponse response;
	public String courseId;

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getTotalfees() {
		return totalfees;
	}

	public void setTotalfees(String totalfees) {
		this.totalfees = totalfees;
	}

	public String getPaidfees() {
		return paidfees;
	}

	public void setPaidfees(String paidfees) {
		this.paidfees = paidfees;
	}

	public String getPaydate() {
		return paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public ArrayList<Select> getStudentNameList() {
		return studentNameList;
	}

	public void setStudentNameList(ArrayList<Select> studentNameList) {
		this.studentNameList = studentNameList;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getJson1111() {
		return json1111;
	}

	public void setJson1111(String json1111) {
		this.json1111 = json1111;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		
		this.fees = fees;
	}

	public String getJson11() {
		return json11;
	}

	public void setJson11(String json11) {
		this.json11 = json11;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public JSONArray getJsonarray() {
		return jsonarray;
	}

	public void setJsonarray(JSONArray jsonarray) {
		this.jsonarray = jsonarray;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Select> getCourseNameList() {
		return courseNameList;
	}

	public void setCourseNameList(List<Select> courseNameList) {
		this.courseNameList = courseNameList;
	}

	public ArrayList<Select> getBatchNameList() {
		return batchNameList;
	}

	public void setBatchNameList(ArrayList<Select> batchNameList) {
		this.batchNameList = batchNameList;
	}

	public List<Select> getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(List<Select> courseNames) {
		this.courseNames = courseNames;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
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

	/* REGISTER CONTROLLER */
	public String feesregister11() {
		String status = "success";
		status = FeesRegisterActionDAO.orginalregister(this);
		return status;
	}

	/* VALIDATION */
	public void validate() {
		/* System.out.println(courseId+" "+courseId.length()); */
		
			Pattern pattern = Pattern.compile("^\\d+$");
			Matcher matcher = pattern.matcher(paidfees);
			boolean feesflag = matcher.matches();
			
		
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			String existingDataSql = "SELECT courseId,courseName,fees from tbl_course";
			PreparedStatement pselect = con.prepareStatement(existingDataSql);
			ResultSet rs = pselect.executeQuery();
			while (rs.next()) {
				courseNameList.add(new Select(rs.getString("courseId"), rs
						.getString("courseName")));
				
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			String existingDataSql = "SELECT fees from tbl_course where courseId=?";
			PreparedStatement pselect = con.prepareStatement(existingDataSql);
			pselect.setString(1, getCourseId());
			ResultSet rs1 = pselect.executeQuery();
			while (rs1.next()) {
				setFees(rs1.getString("fees"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			String existingDataSql = "SELECT b.batchId,x.batchName  FROM tbl_addbatch as b LEFT JOIN tbl_course as c on c.courseId = b.courseId"
					+ " LEFT JOIN tbl_batch as x on x.batchId = b.batchId WHERE b.courseId =?";
			PreparedStatement pselect = con.prepareStatement(existingDataSql);
			pselect.setString(1, getCourseId());
			ResultSet rs = pselect.executeQuery();
			while (rs.next()) {
				batchNameList.add(new Select(rs.getString("b.batchId"), rs
						.getString("x.batchName")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			String existingDataSql = "SELECT b.studentId,s.studentName  FROM tbl_addstudent as b LEFT JOIN tbl_batch as c on c.batchId = b.batchId"
					+ " LEFT JOIN tbl_student as s on s.studentId = b.studentId WHERE b.studentId not in (select studentId from tbl_payfees) and b.batchId=?";
			PreparedStatement pselect = con.prepareStatement(existingDataSql);
			pselect.setString(1, getBatchId());
			ResultSet rs = pselect.executeQuery();
			while (rs.next()) {
				studentNameList.add(new Select(rs.getString("b.studentId"), rs
						.getString("s.studentName")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if (courseId.equals("select")) {
		
			addFieldError("courseId", "Please Enter the CourseName");
		} else if (batchId.equals("select")) {
		
			addFieldError("batchId", "Please Enter the BatchName");
		} else if (studentId.equals("select")) {
		
			addFieldError("studentId", "Please Enter the StudentName");
		} else if (paidfees.equals("")) {
			
			addFieldError("paidfees", "Please Enter the Payfees");
			
		}else if(!feesflag){
			addFieldError("paidfees", "Please Enter the  valid Payfees");
		}else if (paydate.equals("")) {
		
			addFieldError("paydate", "Please Enter the paydate");

		}

	}

}
