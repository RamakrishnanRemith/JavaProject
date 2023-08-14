package controller.Fees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.Fees.FeesHistoryActionDAO;
import model.Fees.FeesListActionDAO;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import common.DataBaseConnection;
import common.User;

public class FeesHistoryAction 
	extends ActionSupport implements
	HttpSessionListener, SessionAware, ServletRequestAware,
	ServletResponseAware {
		private static final long serialVersionUID = 1L;
		public HttpServletResponse response;
		public HttpSession session;
		SessionMap<String, Object> sessionMap;
		public HttpServletRequest request;
		public String[] testArray;
		public String studentId,userId,totalpaidfees,sid,bid,cid;
		public ArrayList<User> list = new ArrayList<User>();
		public ArrayList<User> list1 = new ArrayList<User>();
		ArrayList<User> check = new ArrayList<User>();
		String sql = null;
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

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
		}

		public String getTotalpaidfees() {
			return totalpaidfees;
		}

		public void setTotalpaidfees(String totalpaidfees) {
			this.totalpaidfees = totalpaidfees;
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

		public ArrayList<User> getList1() {
			return list1;
		}

		public void setList1(ArrayList<User> list1) {
			this.list1 = list1;
		}

		public ArrayList<User> getList() {
			return list;
		}

		public void setList(ArrayList<User> list) {
			this.list = list;
		}

		public ArrayList<User> getCheck() {
			return check;
		}

		public void setCheck(ArrayList<User> check) {
			this.check = check;
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
		/*FEES HISTRORY CONTROLLER*/
		public String Feeshistory() throws Exception {
			String status = "loginRedirectSuccess";
			User user=new User();
			HttpSession session = request.getSession();
			session = request.getSession(true);
			setSid((String) session.getAttribute("SD"));
			setBid((String) session.getAttribute("BD"));
		status = FeesHistoryActionDAO.feeshisviewlist(this);
			
			return status;
		}
	}
