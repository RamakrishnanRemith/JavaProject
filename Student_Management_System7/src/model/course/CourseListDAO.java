package model.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import common.CommonFunction;
import common.DataBaseConnection;
import common.User;
import controller.batch.BatchListAction;
import controller.course.CourseListAction;

public class CourseListDAO {

	public static String courseview(CourseListAction courseListAction) {
		// TODO Auto-generated method stub
		HttpSession session=courseListAction.request.getSession();
		session=courseListAction.request.getSession(true);
		String status="loginRedirectFail";
		String sessioncourseId=(String)session.getAttribute("sessioncourseId");
	String sql=null;
		//select process
	try{
		Connection con= DataBaseConnection.getConnection();
		sql="SELECT*FROM tbl_course Where".concat(courseListAction.condition).concat(courseListAction.searchCondition).concat(courseListAction.sortCondition)+" LIMIT "+(courseListAction.pageid-1)+" , "+courseListAction.recordsPerPage;
		java.sql.PreparedStatement ps=con.prepareStatement(sql);
		
		
		ResultSet getValue=ps.executeQuery();
		int sno =0;
		courseListAction.list=new ArrayList<User>();
		while(getValue.next()){
			User user = new User();
			sno++;
			user.setSno(sno);
			user.setCourseName(getValue.getString("courseName"));
			user.setCourseId(getValue.getString("courseId"));
			CommonFunction cf=new CommonFunction();
			
			String fees=getValue.getString("fees");
			int fees2=Integer.parseInt(fees);
			String fees3=cf.feesFormat(fees2);	
			user.setFees(fees3);
			user.setDelflag(getValue.getInt("delflag"));
			//set user in list
			
			//user.setDuration(getValue.getString("duration"));
			
			String duration1=getValue.getString("duration");
			int duration2=Integer.parseInt(duration1);
			if(duration2==1){
				user.setDuration(duration2+" Month");
			}else{
			
				user.setDuration(duration2+" Months");
			}
			courseListAction.list.add(user);
		}
		if(!courseListAction.list.equals(null)){
			status="loginRedirectSuccess";
		}else{
			System.out.println("loginRedirectFail");
		}
		
		
	}catch(Exception e1) {
				System.out.println(e1);
			}
		 return status;

	}

	public static String courselistUse(CourseListAction courseListAction) {
		// TODO Auto-generated method stub
		String status ="UseFail";
		
		try {
			Connection con= DataBaseConnection.getConnection();
			PreparedStatement useUpdate = con.prepareStatement("Update tbl_course set delflag=0 where courseId = ?");
			useUpdate.setString(1, courseListAction.getHdnuserid());
			int delUpdate=useUpdate.executeUpdate();
			if(delUpdate==1) {
				status ="UseSuccess";
			}
		} catch(Exception e1) {
			System.out.println(e1);
		}
		return status;
	}

	public static String coursenotlistUse(CourseListAction courseListAction) {
		// TODO Auto-generated method stub
		String status ="NotUseFail";
		try {
			Connection con= DataBaseConnection.getConnection();
			PreparedStatement notUseUpdate = con.prepareStatement("Update tbl_course set delflag=1 where courseId = ?");
			notUseUpdate.setString(1, courseListAction.getHdnuserid());
			int delUpdate=notUseUpdate.executeUpdate();
			if(delUpdate==1){
				status ="NotUseSuccess";
			}
			
		} catch(Exception e1) {
			System.out.println(e1);
		}
		return status;
	}
}
	