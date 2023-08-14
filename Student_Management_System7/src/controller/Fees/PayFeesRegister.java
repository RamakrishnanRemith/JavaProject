package controller.Fees;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.json.JSONException;
import org.json.JSONObject;

public class PayFeesRegister implements HttpSessionListener, SessionAware,
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	public List<Select> courseNameList = new ArrayList<Select>();
	public ArrayList<Select> batchNameList = new ArrayList<Select>();
	public ArrayList<Select> studentNameList = new ArrayList<Select>();
	public String batchName, batchId, json, json11, json1111, fees;
	public String courseName, studentId, studentName, totalfees, paidfees,
			paydate;
	public int delFlag;
	public List<Select> courseNames;
	public HttpSession session;
	public HttpServletRequest request;
	HttpServletResponse response;
	public String courseId;
	JSONArray jsonarray = new JSONArray();

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

	/* COURSE SELECT BOX CONTROLLER */
	public String feesrgister() throws SQLException, JSONException {
		String status = "success";
		ResultSet rs = FeesRegisterActionDAO.process(this);
		while (rs.next()) {
			courseNameList.add(new Select(rs.getString("courseId"), rs
					.getString("courseName")));
		}
		return status;
	}

	/* BATCH SELECT BOX CONTROLLER */
	public String batchs() throws SQLException, JSONException {
		String status = "success";
		ResultSet rs = FeesRegisterActionDAO.batch11(this);
		while (rs.next()) {
			batchNameList.add(new Select(rs.getString("b.batchId"), rs
					.getString("x.batchName")));
			JSONObject Jsonobj = new JSONObject();
			Jsonobj.put("batchId", rs.getString("b.batchId"));
			Jsonobj.put("batchName", rs.getString("x.batchName"));
			jsonarray.put(Jsonobj);
			json = jsonarray.toString();
		}
		return status;
	}

	/* STUDENT SELECT BOX CONTROLLER */
	public String students() throws SQLException, JSONException {
		String status = "success";
		ResultSet rs = FeesRegisterActionDAO.student11(this);
		while (rs.next()) {
			JSONObject Jsonobj = new JSONObject();
			studentNameList.add(new Select(rs.getString("b.studentId"), rs
					.getString("s.studentName")));
			Jsonobj.put("studentId", rs.getString("b.studentId"));
			Jsonobj.put("studentName", rs.getString("s.studentName"));
			jsonarray.put(Jsonobj);
			json11 = jsonarray.toString();
		}
		return status;
	}

	public String fees111() throws SQLException, JSONException {
		String status = "success";
		status = FeesRegisterActionDAO.processfees(this);
		JSONObject Jsonobj = new JSONObject();
		Jsonobj.put("fees", getFees());
		jsonarray.put(Jsonobj);
		json1111 = jsonarray.toString();
		return status;
	}
}