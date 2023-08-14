package controller.batch;



import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.batch.BatchSingleViewDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import common.User;

public class BatchSingleView implements HttpSessionListener, SessionAware,
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	public HttpSession session;
	HttpServletRequest request;
	HttpServletResponse response;
	private String hdnuserId;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {

		this.teacherName = teacherName;

	}

	public String userId, batchId, studentName, studentId, teacherName;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public ArrayList<User> liststu = new ArrayList<User>();

	public ArrayList<User> getListstu() {
		return liststu;
	}

	public void setListstu(ArrayList<User> liststu) {
		this.liststu = liststu;
	}

	public String getBatchId() {

		return batchId;
	}

	public void setBatchId(String batchId) {

		this.batchId = batchId;
	}

	public ArrayList<User> list = new ArrayList<User>();
	private String batch;

	private String sessionbatchId;

	public ArrayList<User> getList() {
		return list;
	}

	private String startDate, endDate;

	private String batchName;

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getHdnuserId() {
		return hdnuserId;
	}

	public void setHdnuserId(String hdnuserId) {
		this.hdnuserId = hdnuserId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getSessionbatchId() {
		return sessionbatchId;
	}

	public void setSessionbatchId(String sessionbatchId) {
		this.sessionbatchId = sessionbatchId;
	}

	public void setList(ArrayList<User> list) {
		this.list = list;
	}

	public String getHdnuserid() {
		return hdnuserId;
	}

	public void setHdnuserid(String hdnuserId) {
		this.hdnuserId = hdnuserId;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {

		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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

	public String BatchSingle1View() {

		String status = "ProfileSuccess";
		session = ServletActionContext.getRequest().getSession(true);
		sessionbatchId = (String) session.getAttribute("sessionbatchId");

		String sql = null;
		if (userId == null && sessionbatchId == null) {
			userId = (String) session.getAttribute("sessionId");
		} else if (userId == null && sessionbatchId != null) {
			userId = sessionbatchId;
		} else if (userId == null && sessionbatchId != null) {
			userId = sessionbatchId;
		} else {
			userId = userId;
		}

		status = BatchSingleViewDAO.batchsinview(this);
		return status;
	}

	public String geteditUpdateData() {
		String status = "UpdateDataSucces";

		session = ServletActionContext.getRequest().getSession(true);
		// session = request.getSession(true);
		session = ServletActionContext.getRequest().getSession(true);
		sessionbatchId = (String) session.getAttribute("sessionbatchId");

		status = BatchSingleViewDAO.batchupdate(this);
		return status;
	}
}