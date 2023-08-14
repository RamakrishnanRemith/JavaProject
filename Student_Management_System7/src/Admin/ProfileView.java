package Admin;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.*;
import org.apache.struts2.ServletActionContext;
import common.DataBaseConnection;
import common.User;

/**
 * ProfileView
 * @author Prem kumar
 *
 */
public class ProfileView {
	HttpSession session;
	HttpServletRequest request;
	private String hdnuserId;
	ArrayList<User> list=new ArrayList<User>();
	/**
	 * 
	 * @return status
	 */
	public String profileView() {
		String status="ProfileViewFail";
		try {
			java.sql.Connection con=DataBaseConnection.getConnection();
			String sql=null;
			session =ServletActionContext.getRequest().getSession(false);
			String sessionadminId=(String)session.getAttribute("sessionadminId");
			//select process
			sql="SELECT userName,adminId,mailId,dob,gender,contactNo FROM tbl_admin Where adminId='"+sessionadminId+"'";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ResultSet getValue=ps.executeQuery();
			while(getValue.next()) {
				User user=new User();
				user.setUserName(getValue.getString("userName"));
				user.setAdminId(getValue.getString("adminId"));
				user.setMailId(getValue.getString("mailId"));
				user.setDob(getValue.getString("dob"));
				user.setGender(getValue.getInt("gender"));
				user.setContactNo(getValue.getString("contactNo"));
				list.add(user);
				status="ProfileViewSuccess";
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	/**
	 * 
	 * @return hdnuserId
	 */
	public String getHdnuserId() {
		return hdnuserId;
	}
	/**
	 * 
	 * @param hdnUserId
	 */
	public void setHdnuserId (String hdnUserId) {
		this.hdnuserId=hdnUserId;
	}
	/**
	 * 
	 * @return list
	 */
	public ArrayList<User>getList() {
		return list;
	}
	/**
	 * 
	 * @param list
	 */
	public void setList (ArrayList<User>list) {
		this.list=list;
	}
}