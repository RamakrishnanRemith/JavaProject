package controller.course;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.batch.BatchSingleViewDAO;
import model.course.CourseSingleViewDAO;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import common.User;

public class CourseSingleViewAction implements HttpSessionListener, SessionAware,
ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	public HttpSession session;
	HttpServletRequest request;
	HttpServletResponse response;
	private String hdnuserId;
	public String userId, courseId,duration,batchName;
	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		
		this.duration = duration;
	}

	public ArrayList<User> list = new ArrayList<User>();
	public ArrayList<User> getList() {
		return list;
	}

	public void setList(ArrayList<User> list) {
		this.list = list;
	}

	public ArrayList<User> lists = new ArrayList<User>();
	public ArrayList<User> getLists() {
		return lists;
	}

	public void setLists(ArrayList<User> lists) {
		this.lists = lists;
	}

	private String course;
	private String sessioncourseId;
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

	

	
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSessioncourseId() {
		return sessioncourseId;
	}

	public void setSessioncourseId(String sessioncourseId) {
		this.sessioncourseId = sessioncourseId;
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
	public String CourseSingle1View() {

		String status = "ProfileSuccess";
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

		status = CourseSingleViewDAO.coursesinview(this);
		return status;
	}

	public String getcourseeditUpdateData() {
	
		String status = "UpdateDataSucces";

		session = ServletActionContext.getRequest().getSession(true);
		// session = request.getSession(true);
		session = ServletActionContext.getRequest().getSession(true);
		sessioncourseId = (String) session.getAttribute("sessioncourseId");
       
		status = CourseSingleViewDAO.coursebatchupdate(this);
		return status;
	}
}
