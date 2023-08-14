package controller.Fees;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.Fees.FeesDetailRegisterDAO;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import common.User;

public class FeesDetailRegisterAction extends ActionSupport implements
HttpSessionListener, SessionAware, ServletRequestAware,
ServletResponseAware { 
	private static final long serialVersionUID = 1L;
	public HttpServletResponse response;
	public String studentName,courseName,batchName,totalFees,remainFees,totalpaidFees,paidFees,paydate;
	public int delFlag;
	public String paidfees,totalfees;
	public HttpSession session;
	SessionMap<String, Object> sessionMap;
	public HttpServletRequest request;
	public String sid,cid,bid,userId;
	public String TToalfees;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public String getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(String totalFees) {
		this.totalFees = totalFees;
	}
	public String getRemainFees() {
		return remainFees;
	}
	public void setRemainFees(String remainFees) {
		this.remainFees = remainFees;
	}
	public String getTotalpaidFees() {
		return totalpaidFees;
	}
	public void setTotalpaidFees(String totalpaidFees) {
		this.totalpaidFees = totalpaidFees;
	}
	public String getPaidFees() {
		return paidFees;
	}
	public void setPaidFees(String paidFees) {
		this.paidFees = paidFees;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getPaidfees() {
		return paidfees;
	}
	public void setPaidfees(String paidfees) {
		this.paidfees = paidfees;
	}
	public String getTotalfees() {
		return totalfees;
	}
	public void setTotalfees(String totalfees) {
		this.totalfees = totalfees;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getTToalfees() {
		return TToalfees;
	}
	public void setTToalfees(String tToalfees) {
		TToalfees = tToalfees;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	/*FeesDetailregisterscreeninsert*/
	public String FeesDetailregisterscreeninsert() throws Exception {
		String status = "success";
		HttpSession session3 = request.getSession();
		session = request.getSession(true);
		setSid((String) session.getAttribute("SD"));
		setCid((String) session.getAttribute("CD"));
		setBid((String) session.getAttribute("BD"));
		setTToalfees((String) session.getAttribute("FEES"));
		status = FeesDetailRegisterDAO.feeDetailregisterinsert(this);
		return status;
	}
}
