package model.batch;


import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import common.DataBaseConnection;
import controller.batch.BatchRegisterAction;

public class BatchActionDAO {

	public static String process(BatchRegisterAction batchRegisterAction) {
		// TODO Auto-generated method stub
		String status="fail", insertSql, existingDataSql,sql; 
		int statusSuccess=0, delFlag = 0,exsitingFlag = 0;
	
	
		
		try {
			//Existing validation
			java.sql.Connection con=DataBaseConnection.getConnection();
			existingDataSql = "SELECT * from tbl_batch";
			java.sql.PreparedStatement pselect=con.prepareStatement(existingDataSql);
		
			int id= 1;
			String label="B";
			ResultSet rs=pselect.executeQuery();
			String testmail = null;
			while (rs.next()){
				testmail=rs.getString("batchname");
				if((testmail.equals(batchRegisterAction.getBatchname()))) {
					exsitingFlag=1;
				}
			}
			if(exsitingFlag==0) {
				
				sql = "SELECT id FROM tbl_batch ORDER BY id DESC Limit 1";
				java.sql.PreparedStatement pselect1=con.prepareStatement(sql);
				
				ResultSet rs1=pselect1.executeQuery();
				//ID generation
				if(rs1.next()) {
					id=rs1.getInt("id") + 1;
				}
				String batchId=String.format(label+"%05d",id);
				if (batchRegisterAction.enddate.compareTo(batchRegisterAction.startdate) < 0) {
					JOptionPane.showMessageDialog(null,"Ending date should be comes after Starting date", "ERROR",JOptionPane.OK_CANCEL_OPTION);
				}else{
				//insert process
			
					insertSql = "INSERT INTO tbl_batch(batchId,batchname,startdate,enddate,delFlag)VALUES (?,?,?,?,?)";
					PreparedStatement ps=con.prepareStatement(insertSql);
					ps.setString (1, batchId);
					ps.setString (2,batchRegisterAction.getBatchname());
					ps.setString (3,batchRegisterAction.getStartdate());
					ps.setString (4,batchRegisterAction.getEnddate());
					ps.setInt (5,batchRegisterAction.delFlag);
					
					statusSuccess=ps.executeUpdate(); 
					if (statusSuccess == 1) {status="success";
					
					}
					else {
						System.out.println("fail");
						}
				}
				
				} else {
				JOptionPane.showMessageDialog(null,"Batch Name is Already Exist",
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			}catch(Exception e) {
				System.out.println(e);
			}
	
		return status;
	}

}