package model.course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import common.DataBaseConnection;

import controller.course.CourseRegisterAction;

public class CourseRegisterDAO {

	public static String process(CourseRegisterAction courseRegisterAction) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String status="fail", insertSql, existingDataSql,sql; 
				int statusSuccess=0, delFlag = 0,exsitingFlag = 0;
			
			
				
				try {
					//Existing validation
					java.sql.Connection con=DataBaseConnection.getConnection();
					existingDataSql = "SELECT * from tbl_course";
					java.sql.PreparedStatement pselect=con.prepareStatement(existingDataSql);
				
					int id= 1;
					String label="C";
					ResultSet rs=pselect.executeQuery();
					String testmail = null;
					while (rs.next()){
						testmail=rs.getString("coursename");
						if((testmail.equals(courseRegisterAction.getCoursename()))) {
							exsitingFlag=1;
						}
					}
					if(exsitingFlag==0) {
						
						sql = "SELECT id FROM tbl_course ORDER BY id DESC Limit 1";
						java.sql.PreparedStatement pselect1=con.prepareStatement(sql);
						
						ResultSet rs1=pselect1.executeQuery();
						//ID generation
						if(rs1.next()) {
							id=rs1.getInt("id") + 1;
						}
						String courseId=String.format(label+"%05d",id);
						
						//insert process
					
							insertSql = "INSERT INTO tbl_course(courseId,courseName,duration,fees,delFlag)VALUES (?,?,?,?,?)";
							PreparedStatement ps=con.prepareStatement(insertSql);
							ps.setString (1, courseId);
							ps.setString (2,courseRegisterAction.getCoursename());
							ps.setString (3,courseRegisterAction.getDuration());
							ps.setString (4,courseRegisterAction.getFees());
							ps.setInt (5,courseRegisterAction.delFlag);
							
							statusSuccess=ps.executeUpdate(); 
							if (statusSuccess == 1) {status="success";
							
							}
							else {
								System.out.println("fail");
								}
						
						
						} else {
						JOptionPane.showMessageDialog(null,"Course Name is Already Exist",
								"Error", JOptionPane.ERROR_MESSAGE);
					}

					}catch(Exception e) {
						System.out.println(e);
					}
			
				return status;
			}

		}