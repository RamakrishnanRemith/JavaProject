package controller.course;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.course.StudentHistoryActionDAO;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import common.User;

public class StudentHistoryAction extends ActionSupport implements
		HttpSessionListener, SessionAware, ServletRequestAware,
		ServletResponseAware {
	private static final long serialVersionUID = 1L;
	public HttpServletResponse response;
	public HttpSession session;
	SessionMap<String, Object> sessionMap;
	public HttpServletRequest request;
	public String[] testArray;
	public String userId, totalFees, paidFees, remainFees, sid, bid, cid;
	public String courseId, courseName;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	private String sessioncourseId;
	public ArrayList<User> list1 = new ArrayList<User>();

	public ArrayList<User> getList1() {
		return list1;
	}

	public void setList1(ArrayList<User> list1) {
		this.list1 = list1;
	}

	public String getSessioncourseId() {
		return sessioncourseId;
	}

	public void setSessioncourseId(String sessioncourseId) {
		this.sessioncourseId = sessioncourseId;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(String totalFees) {
		this.totalFees = totalFees;
	}

	public String getPaidFees() {
		return paidFees;
	}

	public void setPaidFees(String paidFees) {
		this.paidFees = paidFees;
	}

	public String getRemainFees() {
		return remainFees;
	}

	public void setRemainFees(String remainFees) {
		this.remainFees = remainFees;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
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
	public void setSession(Map<String, Object> arg0) {
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

	public String Studenthistorylist() throws Exception {
		String status = "fail";
		User user = new User();
		HttpSession session = request.getSession();
		session = request.getSession(true);
		setSid((String) session.getAttribute("SD"));
		setBid((String) session.getAttribute("BD"));
		setCid((String) session.getAttribute("CD"));
		sessioncourseId = (String) session.getAttribute("sessioncourseId");
		status = StudentHistoryActionDAO.stuhistorytlis(this);

		return status;
	}
}
