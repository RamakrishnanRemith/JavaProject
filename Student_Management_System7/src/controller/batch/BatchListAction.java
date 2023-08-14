package controller.batch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.batch.BatchActionListDAO;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import common.DataBaseConnection;
import common.User;

public class BatchListAction extends ActionSupport implements
		HttpSessionListener, SessionAware, ServletRequestAware,
		ServletResponseAware {
	private static final long serialVersionUID = 1L;
	public HttpServletResponse response;
	public HttpSession session;
	SessionMap<String, Object> sessionMap;
	public HttpServletRequest request;
	public String[] testArray;
	public String searchText, sort;
	public int filterId = 2, currentPage = 1, sortField;
	public String linkname, hdnuserid;
	public ArrayList<User> list = new ArrayList<User>();

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

	ArrayList<User> check = new ArrayList<User>();
	String sql = null;
	public String searchCondition = "";
	public String condition = "";
	public String sortCondition = "";
	String sortConditionFiled = "batchId";
	String sessionbatchId;
	public int pageid;
	public int recordsPerPage;

	/**
	 * 
	 * method view
	 */

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

	public String batchview() throws Exception {

		HttpSession session = request.getSession();
		session = request.getSession(true);
		sessionbatchId = (String) session.getAttribute("sessionbatchId");

		try {
			Connection con = DataBaseConnection.getConnection();

			// For filter Process
			if (filterId == 0) {
				condition = " delflag = '" + filterId + "'";
				;
			} else if (filterId == 1) {
				condition = " delflag = '" + filterId + "'";
			} else {
				condition = " (delflag = 0 OR delflag = 1)";
			}

			// Search Condition
			if (this.searchText != null && this.searchText.length() > 0) {
				searchCondition = " And batchId LIKE '%" + searchText.trim()
						+ "%' OR batchName LIKE '%" + searchText.trim() + "%'";
			}
			// order Condition
			if (this.sortField == 2) {
				sortConditionFiled = "batchName";
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
			String countIdSql = "SELECT COUNT(id) as count FROM tbl_batch Where"
					.concat(condition).concat(searchCondition);
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
			session.setAttribute("sessionuser", "Batch");
			status = BatchActionListDAO.Batchview(this);
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public String batchlistUse() {
		String status = "UseSuccess";
		status = BatchActionListDAO.BatchlistUse(this);
		return status;
	}

	public String batchlistNotUse() {
		String status = "NotUseSuccess";
		status = BatchActionListDAO.BatchnotlistUse(this);
		return status;
	}

}