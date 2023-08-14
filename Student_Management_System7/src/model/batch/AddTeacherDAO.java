 package model.batch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DataBaseConnection;
import common.User;
import controller.batch.AddStudentAction;
import controller.batch.AddTeacherAction;

public class AddTeacherDAO {

	public static String addtea(AddTeacherAction addTeacherAction) {
		String status="fail";
		String sql=null;
		
		
		try {
			java.sql.Connection con=DataBaseConnection.getConnection();
			sql="SELECT * FROM tbl_batch Where batchId='"+addTeacherAction.getUserId()+"'";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			
			ResultSet getValue=ps.executeQuery();
			while(getValue.next()){
				User user=new User();
				user.setBatchId(getValue.getString("batchId"));
			   user.setBatchName(getValue.getString("batchName"));
				
			   addTeacherAction.list.add(user);
				//set session
			   addTeacherAction.session.setAttribute("sessionbatchId", getValue.getString("batchId"));
			   addTeacherAction.session.getAttribute("sessionbatchId");
			
				status="success";
			}
			
			java.sql.Connection con1=DataBaseConnection.getConnection();
			sql="SELECT s.teacherId,s.teacherName,ast.teacherId IN (SELECT IF(batchId='"+addTeacherAction.getUserId()+"',teacherId,batchId) FROM tbl_addteacher) FROM tbl_teacher AS s LEFT JOIN tbl_addteacher AS ast on s.teacherId = ast.teacherId GROUP BY s.teacherId";
			java.sql.PreparedStatement ps1=con1.prepareStatement(sql);
		
			ResultSet getValue1=ps1.executeQuery();
		
			while(getValue1.next()){
				User user=new User();
				user.setTeacherId(getValue1.getString("s.teacherId"));
			   user.setTeacherName(getValue1.getString("s.teacherName"));
			  user.setAddteachId(getValue1.getString("ast.teacherId IN (SELECT IF(batchId='"+addTeacherAction.getUserId()+"',teacherId,batchId) FROM tbl_addteacher)"));
			
			   addTeacherAction.listsss.add(user);
				//set session
			   addTeacherAction.session.setAttribute("sessionteacherId", getValue1.getString("teacherId"));
			   addTeacherAction.session.getAttribute("sessionteacherId");
				status="success";
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
		}
	
	public static String addteain(AddTeacherAction addTeacherAction) {
		String status="fail";
		String insertSql=null;
		
		try{
          java.sql.Connection con12=DataBaseConnection.getConnection();
			java.sql.PreparedStatement ps1 = con12.prepareStatement("DELETE FROM  tbl_addteacher WHERE  batchId =?");
			ps1.setString(1, addTeacherAction.getSessionbatchId());
			
			int rs=ps1.executeUpdate();
			ps1.close();

			
			java.sql.Connection con=DataBaseConnection.getConnection();
			String cbox=addTeacherAction.getCheckboxval1();
			
			String cbocvalue[]=cbox.split(",");
			for(String str:cbocvalue){
			insertSql = "INSERT INTO tbl_addteacher(batchId,teacherId)VALUES (?,?)";
		PreparedStatement ps=con.prepareStatement(insertSql);
		ps.setString (1,addTeacherAction.getSessionbatchId());
		
		
		ps.setString (2,str.trim());
		int statusSuccess1=ps.executeUpdate(); 
		 status="success";
			}
}catch(Exception e) {
	System.out.println(e);
}

return status;
}
	
}
