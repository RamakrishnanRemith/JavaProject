package controller.Fees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.Fees.FeesListActionDAO;
import model.Fees.Select;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import common.DataBaseConnection;
import common.User;

public class FeesListAction implements HttpSessionListener, SessionAware,
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	public HttpServletResponse response;
	public HttpSession session;
	SessionMap<String, Object> sessionMap;
	public HttpServletRequest request;
	public String[] testArray;
	public String searchText, sort;
	public int filterId = 2, currentPage = 1, sortField;
	public String linkname, hdnuserid, userId, cid, bid;
	public String studentName, courseName, batchName, totalFees, remainFees,
			totalpaidFees, paidFees, paydate, sid;
	public String paidfees, totalfees, TToalfees;
	public List<Select> courseNameList = new ArrayList<Select>();
	public ArrayList<Select> batchNameList = new ArrayList<Select>();
	public ArrayList<Select> studentNameList = new ArrayList<Select>();
	public ArrayList<User> list = new ArrayList<User>();
	public ArrayList<User> listdetailtable = new ArrayList<User>();
	public ArrayList<User> listdetailregister = new ArrayList<User>();
	public ArrayList<User> listdetail = new ArrayList<User>();
	ArrayList<User> check = new ArrayList<User>();
	String sql = null;
	public int delFlag;
	public String searchCondition = "";
	public String condition = "";
	public String sortCondition = "";
	public String sortConditionFiled = "studentId";
	String sessionstudentId;
	public int pageid;
	public int recordsPerPage;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public ArrayList<Select> getStudentNameList() {
		return studentNameList;
	}

	public void setStudentNameList(ArrayList<Select> studentNameList) {
		this.studentNameList = studentNameList;
	}

	public String getTToalfees() {
		return TToalfees;
	}

	public void setTToalfees(String tToalfees) {
		TToalfees = tToalfees;
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

	public String getPaidFees() {
		return paidFees;
	}

	public void setPaidFees(String paidFees) {
		this.paidFees = paidFees;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<User> getListdetail() {
		return listdetail;
	}

	public void setListdetail(ArrayList<User> listdetail) {
		this.listdetail = listdetail;
	}

	public ArrayList<User> getListdetailtable() {
		return listdetailtable;
	}

	public void setListdetailtable(ArrayList<User> listdetailtable) {
		this.listdetailtable = listdetailtable;
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

	public String getHdnuserid() {
		return hdnuserid;
	}

	public void setHdnuserid(String hdnuserid) {
		this.hdnuserid = hdnuserid;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getFilterId() {
		return filterId;
	}

	public void setFilterId(int filterId) {
		this.filterId = filterId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getSortField() {
		return sortField;
	}

	public void setSortField(int sortField) {
		this.sortField = sortField;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	String status = "loginRedirectFail";

	/* FEES LIST VALUES IN CONTROLLER */
	public String Feesview() throws Exception {
		HttpSession session = request.getSession();
		session = request.getSession(true);
		try {
			Connection con = DataBaseConnection.getConnection();
			// For filter Process
			if (filterId == 0)

			{
				condition = " delFlag = '" + filterId + "'";
				;
			} else if (filterId == 1) {
				condition = " delFlag = '" + filterId + "'";
			} else {
				condition = " (delFlag = 0 OR delFlag = 1)";
			}
			// Search Condition
						if (this.searchText != null && this	.searchText.length() > 0) {
							searchCondition = " where pf.delFlag=0 and s.studentName LIKE '%" + searchText.trim()
									+ "%' OR s.studentId LIKE '%" + searchText.trim() + "%' OR b.batchName LIKE '%" + searchText.trim() + "%'";
						}
			// order Condition
			if (this.sortField == 2) {
				sortConditionFiled = "studentName";
			}
			if (this.sort != null && this.sort.length() > 0) {
				sortCondition = " ORDER BY " + sortConditionFiled + " " + sort
						+ "";
			} else {
				sort = "DESC";
				sortCondition = " ORDER BY " + sortConditionFiled + " " + sort
						+ "";
			}
			// For Pagination
			pageid = 1;
			recordsPerPage = 5;

			if (currentPage != 0) {
				pageid = currentPage;
			} else {
				currentPage = 1;
			}
			if (pageid != 1) {
				pageid = pageid - 1;
				pageid = pageid * recordsPerPage + 1;
			}
			String countIdSql = "SELECT COUNT(pf.id) as count FROM tbl_payfees as pf left join tbl_student as s on s.studentId=pf.studentId left join tbl_batch as b on b.batchId=pf.batchId"
					.concat(searchCondition);
			java.sql.PreparedStatement countIdPs = con
					.prepareStatement(countIdSql);
			ResultSet countIdgetValue = countIdPs.executeQuery();
			int rows = 0;
			while (countIdgetValue.next()) {
				rows = countIdgetValue.getInt("count");
			}
			int nOfPages = rows / recordsPerPage;
			if (rows % recordsPerPage > 0) {
				nOfPages++;
			}
			int loadStart=1,loadEnd=0;
			if(currentPage>1) {
				if(nOfPages>=currentPage +4) {
					loadStart=currentPage-1;
					loadEnd=currentPage +3;
				} else {
					if(nOfPages>5){
						loadStart=nOfPages-4;
					}else{
						loadStart=1;
					}
					loadEnd=nOfPages;
					


				}
			} else { 
				if(nOfPages>5){
					loadEnd=currentPage+4;
				}else{
					loadEnd=nOfPages;
				}
			}
			// set session
			session.setAttribute("nOfPages", nOfPages);
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("loadEnd", loadEnd);
			session.setAttribute("loadStart", loadStart);
			session.setAttribute("sno", pageid);
			session.setAttribute("sorting", sort);
			session.setAttribute("sessionFilterId", filterId);
			session.setAttribute("sessionSearchText", searchText);
			session.setAttribute("sessionuser", "Fees");
			User user = new User();
			HttpSession session1 = request.getSession();
			session = request.getSession(true);
			setSid((String) session.getAttribute("SD"));
			setCid((String) session.getAttribute("CD"));
			setBid((String) session.getAttribute("BD"));
			status = FeesListActionDAO.feesviewlist(this);

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	/* FEES DETAIL VALUES IN CONTROLLER */
	public String FeesDetail() throws Exception {
		String status = "loginRedirectSuccess";
		User user = new User();
		HttpSession session3 = request.getSession();
		session = request.getSession(true);
		if (userId != null) {
			setCid((String) session.getAttribute("CD"));
		} else {
			setUserId((String) session.getAttribute("CD"));
		}
		setBid((String) session.getAttribute("BD"));
		setSid((String) session.getAttribute("SD"));
		status = FeesListActionDAO.feeDetailviewlist(this);
		return status;
	}

	/* FEES DETAIL REGISTER VALUES IN CONTROLLER */
	public String FeesDetailregisterscreen() throws Exception {
		String status = "success";
		User user = new User();
		HttpSession session3 = request.getSession();
		session = request.getSession(true);
		setCid((String) session.getAttribute("CD"));
		setUserId((String) session.getAttribute("CD"));
		setSid((String) session.getAttribute("SD"));
		status = FeesListActionDAO.feeDetailregisterscreen(this);
		return status;
	}
}
