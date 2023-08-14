package model.batch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DataBaseConnection;
import common.User;
import controller.batch.BatchSingleView;

public class BatchSingleViewDAO {

	public static String batchsinview(BatchSingleView batchSingleView) {
		String status="ProfileFail";
		String sql=null;
	
		try {
			String stuname = "";
			java.sql.Connection con=DataBaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement( "SELECT s.studentName  FROM tbl_addstudent as bs LEFT JOIN tbl_student as s ON bs.studentId = s.studentId WHERE  batchId='"+batchSingleView.userId+"' ");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				stuname +=rs.getString("s.studentName") +",";
			}
			if(stuname.length()==0){	
			
				batchSingleView.setStudentName("Nil");
			
				
			}else{
				stuname=stuname.substring(0, stuname.length()-1);
				batchSingleView.setStudentName(stuname);}
				
			rs.close();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
			try {
				String teacname = "";
				java.sql.Connection con=DataBaseConnection.getConnection();
				PreparedStatement ps1 = con.prepareStatement( "SELECT t.teacherName  FROM tbl_addteacher as bt LEFT JOIN tbl_teacher as t ON bt.teacherId = t.teacherId WHERE  batchId='"+batchSingleView.userId+"' ");
				
				ResultSet rs1 = ps1.executeQuery();
				
				while (rs1.next()) {
					teacname +=rs1.getString("t.teacherName") +",";
				}
				if(teacname.length()==0){	
				
					batchSingleView.setTeacherName("Nil");
				
					
				}else{
					teacname=teacname.substring(0, teacname.length()-1);
					batchSingleView.setTeacherName(teacname);}
					
				rs1.close();
				ps1.close();
			} catch (Exception e) {
				System.out.println(e);
			}


		try {
			java.sql.Connection con=DataBaseConnection.getConnection();
			sql="SELECT * FROM tbl_batch Where batchId='"+batchSingleView.userId+"'";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			
			ResultSet getValue=ps.executeQuery();
			while(getValue.next()){
				User user=new User();
				user.setBatchId(getValue.getString("batchId"));
			    user.setBatchName(getValue.getString("batchName"));
				user.setStartDate(getValue.getString("startDate"));
				user.setEndDate(getValue.getString("endDate"));
				batchSingleView.list.add(user);
				//set session
				batchSingleView.session.setAttribute("sessionbatchId", getValue.getString("batchId"));
				batchSingleView.session.getAttribute("sessionbatchId");
				
				
				
				
				status="ProfileSuccess";
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
		}

	public static String batchupdate(BatchSingleView batchSingleView) {
		// TODO Auto-generated method stub
		String status="UpdateDataFail";
		
		try{
			java.sql.Connection con=DataBaseConnection.getConnection();
			String sql="SELECT * FROM tbl_batch Where batchId='"+batchSingleView.userId+"'";
			PreparedStatement ps=con.prepareStatement(sql);
		
			ResultSet getValue=ps.executeQuery();
			while (getValue.next()){
				User user=new User();
				
				user.setBatchId(getValue.getString("batchId"));
				user.setBatchName(getValue.getString("batchName"));
				user.setStartDate(getValue.getString("startDate"));
				user.setEndDate(getValue.getString("endDate"));
				batchSingleView.list.add(user);
				status="UpdateDataSuccess";
				
				batchSingleView.session.setAttribute("sessionbatchId", getValue.getString("batchId"));
				batchSingleView.session.getAttribute("sessionbatchId");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return status;
	}
}
