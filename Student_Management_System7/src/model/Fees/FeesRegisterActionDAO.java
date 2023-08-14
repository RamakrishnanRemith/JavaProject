package model.Fees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import common.DataBaseConnection;
import controller.Fees.FeesRegisterAction;
import controller.Fees.PayFeesRegister;

public class FeesRegisterActionDAO {
	/* COURSE SELECT BOX */
	public static ResultSet process(PayFeesRegister payFeesRegister) {
		// TODO Auto-generated method stub
		String existingDataSql;
		ResultSet getValue = null;
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			existingDataSql = "SELECT courseId,courseName,fees from tbl_course";
			PreparedStatement pselect = con.prepareStatement(existingDataSql);
			getValue = pselect.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
		return getValue;
	}

	/* BATCH SELECT BOX */
	public static ResultSet batch11(PayFeesRegister payFeesRegister) {
		// TODO Auto-generated method stub
		String existingDataSql;
		ResultSet getValue = null;
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			existingDataSql = "SELECT b.batchId,x.batchName  FROM tbl_addbatch as b LEFT JOIN tbl_course as c on c.courseId = b.courseId"
					+ " LEFT JOIN tbl_batch as x on x.batchId = b.batchId WHERE b.courseId =?";
			PreparedStatement pselect = con.prepareStatement(existingDataSql);
			pselect.setString(1, payFeesRegister.getCourseId());
			getValue = pselect.executeQuery();
			HttpSession session = payFeesRegister.request.getSession();
			session = payFeesRegister.request.getSession(true);
		} catch (Exception e) {
			System.out.println(e);
		}
		return getValue;
	}

	/* STUDENT SELECT BOX */
	public static ResultSet student11(PayFeesRegister payFeesRegister) {
		// TODO Auto-generated method stub
		String existingDataSql;
		ResultSet getValue = null;
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			existingDataSql = "SELECT b.studentId,s.studentName  FROM tbl_addstudent as b LEFT JOIN tbl_batch as c on c.batchId = b.batchId"
					+ " LEFT JOIN tbl_student as s on s.studentId = b.studentId WHERE b.studentId not in (select studentId from tbl_payfees) and b.batchId=?";
			PreparedStatement pselect = con.prepareStatement(existingDataSql);
			pselect.setString(1, payFeesRegister.getBatchId());
			getValue = pselect.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
		return getValue;
	}

	/* FEES VALUE TAKEN FROM COURSE SELECT BOX */
	public static String processfees(PayFeesRegister payFeesRegister) {
		// TODO Auto-generated method stub
		String status = "success";
		String existingDataSql;
		ResultSet getValue = null;
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			existingDataSql = "SELECT fees from tbl_course where courseId=?";
			PreparedStatement pselect = con.prepareStatement(existingDataSql);
			pselect.setString(1, payFeesRegister.getCourseId());
			ResultSet rs1 = pselect.executeQuery();
			while (rs1.next()) {
				payFeesRegister.setFees(rs1.getString("fees"));
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	/* FEESREGISTER THE ALL VALUE */
	public static String orginalregister(FeesRegisterAction feesRegisterAction) {
		// TODO Auto-generated method stub
		String status = "fail", insertSql;
		int statusSuccess = 0;
		HttpSession session = feesRegisterAction.request.getSession();
		session = feesRegisterAction.request.getSession(true);
		try {
		String payfees = feesRegisterAction.getPaidfees();
		int pfees = Integer.parseInt(payfees);
		String totalfees = feesRegisterAction.getTotalfees();
		
		int totalfe = Integer.parseInt(totalfees);
		if ( pfees>totalfe ) {
			JOptionPane.showMessageDialog(null,
					"Please Enter Totalfees lessthan Payfees", "Error",
					JOptionPane.WARNING_MESSAGE);
		} else {
	
			java.sql.Connection con = DataBaseConnection.getConnection();
			insertSql = "INSERT INTO tbl_payfees(courseId,batchId,studentId,totalfees,paidfees,paydate,delFlag)VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insertSql);
			ps.setString(1, feesRegisterAction.getCourseId());
			ps.setString(2, feesRegisterAction.getBatchId());
			ps.setString(3, feesRegisterAction.getStudentId());
			ps.setString(4, feesRegisterAction.getTotalfees());
			ps.setString(5, feesRegisterAction.getPaidfees());
			ps.setString(6, feesRegisterAction.getPaydate());
			ps.setInt(7, feesRegisterAction.delFlag);
			statusSuccess = ps.executeUpdate();
			
			
			
				status = "success";
		}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}