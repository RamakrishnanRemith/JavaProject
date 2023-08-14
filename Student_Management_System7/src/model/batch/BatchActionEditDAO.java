package model.batch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import common.DataBaseConnection;
import controller.batch.BatchEditAction;
import controller.batch.BatchRegisterAction;

public class BatchActionEditDAO {
	
		public static String batchupdate(BatchEditAction batchEditAction) {
			// TODO Auto-generated method stub
			
			
			String status="fail",existingDataSql;
			int exsitingFlag = 0;
			
			try {
				java.sql.Connection con=DataBaseConnection.getConnection();
				
				//existing validation processs
				existingDataSql = "SELECT batchname FROM tbl_batch WHERE batchId NOT IN (?)";
				java.sql.PreparedStatement pselect=con.prepareStatement(existingDataSql);
				pselect.setString(1,batchEditAction.getBatchId());
				ResultSet rs=pselect.executeQuery();
				String testmail = null;
				while (rs.next()) {
					testmail=rs.getString("batchname");
					if((testmail.equals(batchEditAction.getBatchName()))) {
						exsitingFlag=1;
					}
				}
				if(exsitingFlag==1) {
					JOptionPane.showMessageDialog(null, "BatchName is Already Exist","Error",JOptionPane.ERROR_MESSAGE);
				} 
				else {
					if (batchEditAction.endDate.compareTo(batchEditAction.startDate) < 0) {
						JOptionPane.showMessageDialog(null,"Ending date should be comes after Starting date", "ERROR",JOptionPane.OK_CANCEL_OPTION);
					}else{
					//Update Process
					String UpdateSql="UPDATE tbl_batch SET batchname='"+batchEditAction.getBatchName()+"',startdate='"+batchEditAction.getStartDate()+"',enddate='"+batchEditAction.getEndDate()+"' WHERE batchId='"+batchEditAction.getBatchId()+"'";
					PreparedStatement UpdateSqlps=con.prepareStatement(UpdateSql);
					
					int result=UpdateSqlps.executeUpdate();
					if(result==1) {
						status="success";
						//set session
						batchEditAction.session = batchEditAction.request.getSession(true);
						batchEditAction.session.setAttribute("sessionbatchId",batchEditAction.getBatchId());
						
					}
				
			}
				}
				} catch(Exception e) {
					System.out.println(e);
				}
				return status;

		}
}
