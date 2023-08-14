package controller.batch;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import model.batch.BatchActionEditDAO;

public class BatchEditAction extends ActionSupport implements
		HttpSessionListener, SessionAware, ServletRequestAware,
		ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private String batchId, batchName, updatedby;
	public String startDate;
	public String endDate;
	public int delFlag;
	public HttpSession session, SessionbatchId;
	public HttpServletRequest request;
	HttpServletResponse response;

	public String getBatchId() {

		return batchId;
	}

	public void setBatchId(String batchId) {

		this.batchId = batchId;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startdate) {
		this.startDate = startdate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String enddate) {
		this.endDate = enddate;
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

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
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

	public String batcheditUpdate() {
		String status = "fail";
		session = ServletActionContext.getRequest().getSession(true);
		setBatchId((String) session.getAttribute("sessionbatchId"));

		status = BatchActionEditDAO.batchupdate(this);

		return status;

	}

}
