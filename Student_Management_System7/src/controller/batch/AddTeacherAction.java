package controller.batch;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.batch.AddTeacherDAO;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import common.User;

public class AddTeacherAction implements HttpSessionListener, SessionAware,
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	public HttpSession session;
	HttpServletRequest request;
	HttpServletResponse response;
	private String hdnuserId;
	public String userId, studentId, batchId, checkboxval1, teacherId;
	public ArrayList<User> list = new ArrayList<User>();
	public ArrayList<User> listsss = new ArrayList<User>();
	private String batch;
	private String sessionbatchId;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getCheckboxval1() {

		return checkboxval1;
	}
	public void setCheckboxval1(String checkboxval1) {
		this.checkboxval1 = checkboxval1;
	}
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
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getHdnuserId() {
		return hdnuserId;
	}
	public void setHdnuserId(String hdnuserId) {
		this.hdnuserId = hdnuserId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public ArrayList<User> getListsss() {
		return listsss;
	}
	public void setListsss(ArrayList<User> listsss) {
		this.listsss = listsss;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
	public String getSessionbatchId() {
		return sessionbatchId;
	}
	public void setSessionbatchId(String sessionbatchId) {
		this.sessionbatchId = sessionbatchId;
	}
	public String getaddtea() {
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
		status = AddTeacherDAO.addtea(this);
		for (User u : listsss) {
		}
		return status;
	}
	public String insertaddtea() {
		String status = "success";
		session = ServletActionContext.getRequest().getSession(true);
		sessionbatchId = (String) session.getAttribute("sessionbatchId");
		status = AddTeacherDAO.addteain(this);
		return status;
	}
}
