package model.course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import common.DataBaseConnection;
import controller.course.CourseEditAction;

public class CourseEditDAO {

	public static String courseupdate(CourseEditAction courseEditAction) {
		// TODO Auto-generated method stub

		String status="fail",existingDataSql;
		int exsitingFlag = 0;
		
		try {
			java.sql.Connection con=DataBaseConnection.getConnection();
			
			//existing validation processs
			existingDataSql = "SELECT courseName FROM tbl_course WHERE courseId NOT IN (?)";
			java.sql.PreparedStatement pselect=con.prepareStatement(existingDataSql);
			pselect.setString(1,courseEditAction.getCourseId());
			ResultSet rs=pselect.executeQuery();
			String testmail = null;
			while (rs.next()) {
				testmail=rs.getString("courseName");
				if((testmail.equals(courseEditAction.getCourseName()))) {
					exsitingFlag=1;
				}
			}
			if(exsitingFlag==1) {
				JOptionPane.showMessageDialog(null, "CourseName is Already Exist","Error",JOptionPane.ERROR_MESSAGE);
			} 
			else {
				//Update Process
				String UpdateSql="UPDATE tbl_course SET courseName='"+courseEditAction.getCourseName()+"',duration='"+courseEditAction.getDuration()+"' WHERE courseId='"+courseEditAction.getCourseId()+"'";
				PreparedStatement UpdateSqlps=con.prepareStatement(UpdateSql);
				
				int result=UpdateSqlps.executeUpdate();
				if(result==1) {
					status="success";
					//set session
					courseEditAction.session = courseEditAction.request.getSession(true);
					courseEditAction.session.setAttribute("sessioncourseId",courseEditAction.getCourseId());
					
				}
			
		
			}
			} catch(Exception e) {
				System.out.println(e);
			}
			return status;

	}
}
