package model.Fees;

import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import common.DataBaseConnection;
import controller.Fees.FeesDetailRegisterAction;

public class FeesDetailRegisterDAO {
	/* FEESREGISTERDETAIL INSERT */
	public static String feeDetailregisterinsert(
			FeesDetailRegisterAction feesDetailRegisterAction) {
		// TODO Auto-generated method stub
		String status = "fail", insertSql, existingDataSql, sql;
		int statusSuccess = 0;
		HttpSession session = feesDetailRegisterAction.request.getSession();
		session = feesDetailRegisterAction.request.getSession(true);
		int remainfees = (int) session.getAttribute("REMAINFEES");
		String payfees = feesDetailRegisterAction.getPaidfees();
		String payfees1 = payfees.replaceAll(",", "");
		int pfees = Integer.parseInt(payfees1);
		if (pfees > remainfees) {
			JOptionPane.showMessageDialog(null,
					"Please Enter Payfees lessthan Remainfees", "Error",
					JOptionPane.WARNING_MESSAGE);
		} else {
			
			try {
				java.sql.Connection con = DataBaseConnection.getConnection();
				insertSql = "INSERT INTO tbl_payfees(courseId,batchId,studentId,totalfees,paidfees,paydate,delFlag)VALUES (?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(insertSql);
				ps.setString(1, feesDetailRegisterAction.getCid());
				ps.setString(2, feesDetailRegisterAction.getBid());
				ps.setString(3, feesDetailRegisterAction.getSid());
				ps.setString(4, feesDetailRegisterAction.getTToalfees());
				ps.setString(5, feesDetailRegisterAction.getPaidfees());
				ps.setString(6, feesDetailRegisterAction.getPaydate());
				ps.setInt(7, feesDetailRegisterAction.delFlag);
				statusSuccess = ps.executeUpdate();
				if (statusSuccess == 1) {
					status = "success";

				} else {
					System.out.println("fail");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return status;
	}

}
