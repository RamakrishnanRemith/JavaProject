package controller.course;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.batch.AddStudentDAO;
import model.course.AddBatchDAO;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import common.User;

public class AddBatchAction  implements HttpSessionListener, SessionAware,
ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	public HttpSession session;
	HttpServletRequest request;
	HttpServletResponse response;
	private String hdnuserId;
	public String userId, courseId, batchId, addbatcId;
	public ArrayList<User> list = new ArrayList<User>();
	public ArrayList<User> listsss = new ArrayList<User>();
	public String checkboxvalco;
	private String course;
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
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getAddbatcId() {
		return addbatcId;
	}
	public void setAddbatcId(String addbatcId) {
		this.addbatcId = addbatcId;
	}
	public ArrayList<User> getList() {
		return list;
	}
	public void setList(ArrayList<User> list) {
		this.list = list;
	}
	public ArrayList<User> getListsss() {
		return listsss;
	}
	public void setListsss(ArrayList<User> listsss) {
		this.listsss = listsss;
	}
	public String getCheckboxvalco() {
		return checkboxvalco;
	}
	public void setCheckboxvalco(String checkboxvalco) {
		this.checkboxvalco = checkboxvalco;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSessionbatchId() {
		return sessionbatchId;
	}
	public void setSessionbatchId(String sessionbatchId) {
		this.sessionbatchId = sessionbatchId;
	}
	public String getSessioncourseId() {
		return sessioncourseId;
	}
	public void setSessioncourseId(String sessioncourseId) {
		this.sessioncourseId = sessioncourseId;
	}
	private String sessionbatchId, sessioncourseId;
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
	public String getaddbatc() {
		String status = "success";
		session = ServletActionContext.getRequest().getSession(true);
		sessioncourseId = (String) session.getAttribute("sessioncourseId");
		String sql = null;
		if (userId == null && sessioncourseId == null) {
			userId = (String) session.getAttribute("sessionId");
		} else if (userId == null && sessioncourseId != null) {
			userId = sessioncourseId;
		} else if (userId == null && sessioncourseId != null) {
			userId = sessioncourseId;
		} else {
			userId = userId;
		}
		
		status = AddBatchDAO.addbatc(this);
		return status;
	}
	public String insertaddcou() {
		String status = "success";
		session = ServletActionContext.getRequest().getSession(true);
		sessioncourseId = (String) session.getAttribute("sessioncourseId");
		session = ServletActionContext.getRequest().getSession(true);
		setSessionbatchId((String) session.getAttribute("sessionbatchId"));
		status = AddBatchDAO.addbatcin(this);
		return status;
	}
}
