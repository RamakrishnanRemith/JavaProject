package controller.course;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.batch.BatchActionEditDAO;
import model.course.CourseEditDAO;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CourseEditAction extends ActionSupport implements
HttpSessionListener, SessionAware, ServletRequestAware,
ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private String courseId, courseName;
	public String duration;
	public String fees;
	public int delFlag;
	public HttpSession session, SessionbatchId;
	public HttpServletRequest request;
	HttpServletResponse response;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFees() {
		
		return fees;
	}

	public void setFees(String fees) {
		
		this.fees = fees;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpSession getSessionbatchId() {
		return SessionbatchId;
	}

	public void setSessionbatchId(HttpSession sessionbatchId) {
		SessionbatchId = sessionbatchId;
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
	public String courseeditUpdate() {
		String status = "fail";
		session = ServletActionContext.getRequest().getSession(true);
		setCourseId((String) session.getAttribute("sessioncourseId"));

		status = CourseEditDAO.courseupdate(this);

		return status;

	}

}
