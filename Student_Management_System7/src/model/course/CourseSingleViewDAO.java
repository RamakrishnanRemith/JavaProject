package model.course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.CommonFunction;
import common.DataBaseConnection;
import common.User;
import controller.course.CourseSingleViewAction;

public class CourseSingleViewDAO {

	public static String coursesinview(
			CourseSingleViewAction courseSingleViewAction) {
		// TODO Auto-generated method stub
		String status="ProfileFail";
		String sql=null;
		try {
			String teacname = "";
			java.sql.Connection con=DataBaseConnection.getConnection();
			PreparedStatement ps1 = con.prepareStatement( "SELECT b.batchName  FROM tbl_addbatch as bt LEFT JOIN tbl_batch as b ON bt.batchId = b.batchId WHERE  courseId='"+courseSingleViewAction.userId+"' ");
			
			ResultSet rs1 = ps1.executeQuery();
			
			while (rs1.next()) {
				teacname +=rs1.getString("b.batchName") +",";
			}
			if(teacname.length()==0){	
			
				courseSingleViewAction.setBatchName("Nil");
			
				
			}else{
				teacname=teacname.substring(0, teacname.length()-1);
				courseSingleViewAction.setBatchName(teacname);}
				
			rs1.close();
			ps1.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			java.sql.Connection con=DataBaseConnection.getConnection();
			sql="SELECT * FROM tbl_course Where courseId='"+courseSingleViewAction.userId+"'";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			
			ResultSet getValue=ps.executeQuery();
			while(getValue.next()){
				User user=new User();
				user.setCourseId(getValue.getString("courseId"));
			    user.setCourseName(getValue.getString("courseName"));
			    CommonFunction cf=new CommonFunction();
				//user.setDuration(getValue.getString("duration"));
				String duration1=getValue.getString("duration");
				int duration2=Integer.parseInt(duration1);
				if(duration2==1){
					user.setDuration(duration2+" Month");
				}else{
				
					user.setDuration(duration2+" Months");
				}
			
				String fees=getValue.getString("fees");
				int fees2=Integer.parseInt(fees);
				String fees3=cf.feesFormat(fees2);	
				user.setFees(fees3);
				//user.setFees(getValue.getString("fees"));
				courseSingleViewAction.list.add(user);
				//set session
				courseSingleViewAction.session.setAttribute("sessioncourseId", getValue.getString("courseId"));
				courseSingleViewAction.session.getAttribute("sessioncourseId");
				
				
				
				
				status="ProfileSuccess";
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
		}

	public static String coursebatchupdate(
			CourseSingleViewAction courseSingleViewAction) {
		// TODO Auto-generated method stub
String status="UpdateDataFail";
		
		try{
			java.sql.Connection con=DataBaseConnection.getConnection();
			String sql="SELECT * FROM tbl_course Where courseId='"+courseSingleViewAction.userId+"'";
			PreparedStatement ps=con.prepareStatement(sql);
		
			ResultSet getValue=ps.executeQuery();
			
			while (getValue.next()){
				User user=new User();
				
				user.setCourseId(getValue.getString("courseId"));
				user.setCourseName(getValue.getString("courseName"));
				user.setDuration(getValue.getString("duration"));
				 CommonFunction cf=new CommonFunction();
				
				String fees=getValue.getString("fees");
				int fees2=Integer.parseInt(fees);
				String fees3=cf.feesFormat(fees2);	
				user.setFees(fees3);
				courseSingleViewAction.list.add(user);
				/*for(User user1:courseSingleViewAction.lists){
					System.out.println(user1.duration+"eeeeeee");
					
				}*/
				status="UpdateDataSuccess";
				
				courseSingleViewAction.session.setAttribute("sessioncourseId", getValue.getString("courseId"));
				courseSingleViewAction.session.getAttribute("sessioncourseId");
				
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return status;
	}

	
}
