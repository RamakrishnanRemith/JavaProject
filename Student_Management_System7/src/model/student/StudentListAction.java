package model.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import common.DataBaseConnection;
import common.User;

/**
 * StudentListAction
 * @author prem kumar
 *
 */
public class StudentListAction extends ActionSupport implements HttpSessionListener,SessionAware,ServletRequestAware,ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;
	HttpSession session;
	SessionMap<String,Object>sessionMap;
	static HttpServletRequest request;
	public static String[] testArray;
	public String searchText,sort;
	private int filterId = 2,currentPage=1,sortField;
	public String linkname,hdnuserid;
	ArrayList<User>list=new ArrayList<User>();
	ArrayList<User>check=new ArrayList<User>();
	/**
	 * 
	 * method view
	 */
	public String view() {
			HttpSession session=request.getSession();
			session=request.getSession(true);
			String status="loginRedirectFail";
			String sessionstudentId=(String)session.getAttribute("sessionstudentId");
			try {
				Connection con= DataBaseConnection.getConnection();
				String sql=null,searchCondition="",condition="",sortCondition="",sortConditionFiled="studentId";
				//For filter Process
				if(filterId==0)
				{condition= " delflag = '"+filterId+"'";;
				}
				else if(filterId==1)
				{condition= " delflag = '"+filterId+"'";
				} else {
					condition= " (delflag = 0 OR delflag = 1)";
				}
				//Search Condition
				if(this.searchText != null && this.searchText.length()>0){
					searchCondition=" And studentId LIKE '%"+searchText.trim()+"%' OR studentName LIKE '%"+searchText.trim()+"%' OR mailId LIKE '%"+searchText.trim()+"%'";
				}
				//order Condition
				if(this.sortField==2)
				{sortConditionFiled="studentName";}
				if(this.sort != null && this.sort.length()>0){
					sortCondition = " ORDER BY "+sortConditionFiled+" "+sort+"";
				}else{
					sort="DESC";
					sortCondition = " ORDER BY "+sortConditionFiled+" "+sort+"";
				}
				//For Pagination
				int pageid=1, recordsPerPage=5;
				if(currentPage !=0)
				{pageid= currentPage;}
				else {currentPage =1;}
				if(pageid !=1){
					pageid=pageid-1;
					pageid=pageid*recordsPerPage+1;
				}
				String countIdSql = "SELECT COUNT(id) as count FROM tbl_student Where".concat(condition).concat(searchCondition);
				java.sql.PreparedStatement countIdPs=con.prepareStatement(countIdSql);
				ResultSet countIdgetValue=countIdPs.executeQuery();
				int rows=0;
				while(countIdgetValue.next()){
					rows=countIdgetValue.getInt("count");
				}
				int nOfPages=rows/recordsPerPage;
				if (rows%recordsPerPage>0){
					nOfPages++;
				}
				int loadStart=1,loadEnd=0;
				if(currentPage>1){
					if(nOfPages>=currentPage +1){
						loadStart=currentPage;
						loadEnd=currentPage +1;
					}else{
						loadStart=nOfPages -1;
						loadEnd=nOfPages;
					}
				} else {loadEnd=currentPage +1;}
				//set session
				session.setAttribute("nOfPages",nOfPages);
				session.setAttribute("currentPage",currentPage);
				session.setAttribute("loadEnd",loadEnd);
				session.setAttribute("loadStart",loadStart);
				session.setAttribute("sno",pageid);
				session.setAttribute("sorting",sort);
				session.setAttribute("sessionFilterId",filterId);
				session.setAttribute("sessionSearchText",searchText);
				session.setAttribute("sessionuser","Student");
				//select process
				sql="SELECT*FROM tbl_student Where".concat(condition).concat(searchCondition).concat(sortCondition)+" LIMIT "+(pageid-1)+" , "+recordsPerPage;
				java.sql.PreparedStatement ps=con.prepareStatement(sql);
				ResultSet getValue=ps.executeQuery();
				int sno =0;
				list=new ArrayList<User>();
				while(getValue.next()){
					User user = new User();
					sno++;
					user.setSno(sno);
					user.setStudentName(getValue.getString("studentName"));
					user.setStudentId(getValue.getString("studentId"));
					user.setMailId(getValue.getString("mailId"));
					user.setDob(getValue.getString("dob"));
					user.setGender(getValue.getInt("gender"));
					user.setContactNo(getValue.getString("contactNo"));
					user.setDelflag(getValue.getInt("delflag"));
					//set user in list
					list.add(user);
				}
				if(!list.equals(null)){
					status="loginRedirectSuccess";
				}else{
					System.out.println("loginRedirectFail");
				}
			}catch(Exception e){
				System.out.println(e);
			}
			return status;
	}
	/**
	 * 
	 * method listUse
	 */
	public String listUse() {
	String status ="UseFail";
		try {
			//updete process
			Connection con= DataBaseConnection.getConnection();
			PreparedStatement useUpdate = con.prepareStatement("Update tbl_student set delflag=0 where studentId = ?");
			useUpdate.setString(1, getHdnuserid());
			int delUpdate=useUpdate.executeUpdate();
			if(delUpdate==1){
				status ="UseSuccess";
			}
		} catch(Exception e1) {
			System.out.println(e1);
		}
		return status;
	}
	/**
	 * 
	 * method listNotUse
	 */
	public String listNotUse() {
	String status ="NotUseFail";
		try {
			Connection con= DataBaseConnection.getConnection();
			PreparedStatement notUseUpdate = con.prepareStatement("Update tbl_student set delflag=1 where studentId = ?");
			notUseUpdate.setString(1, getHdnuserid());
			int delUpdate=notUseUpdate.executeUpdate();
			if(delUpdate==1){
				status ="NotUseSuccess";
			}
			
		} catch(Exception e1) {
			System.out.println(e1);
		}
		return status;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		StudentListAction.request=request;
		
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
	
	public ArrayList<User> getList()
	{return list;}
    public void setList(ArrayList<User> List)
	{this.list=List;}
    public ArrayList<User> getCheck()
	{return check;}
    public void setCheck(ArrayList<User> Check)
	{this.check=Check;}
}
