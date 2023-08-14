package model.Fees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import common.DataBaseConnection;
import common.User;
import controller.Fees.FeesHistoryAction;

/*FEESHISTORY*/
public class FeesHistoryActionDAO {
	public static String feeshisviewlist(FeesHistoryAction feesHistoryAction) {
		// TODO Auto-generated method stub
		String status = "loginRedirectFail";
		int sno = 0;
		feesHistoryAction.list1 = new ArrayList<User>();
		String sql = null;
		/* FEESHISTORY LABEL */
		try {
			Connection con = DataBaseConnection.getConnection();
			String sql1 = "SELECT c.courseId,s.studentId,b.batchId,s.studentName,b.batchName,c.fees,pf.paidfees from tbl_student as s "
					+ "LEFT JOIN tbl_addstudent as sd on sd.studentId= s.studentId "
					+ "LEFT JOIN tbl_batch as b on b.batchId= sd.batchId "
					+ "LEFT JOIN tbl_addbatch as cd on cd.batchId= sd.batchId "
					+ "LEFT JOIN tbl_course as c on c.courseId= cd.courseId "
					+ "LEFT JOIN tbl_payfees as pf on pf.studentId= s.studentId "
					+ "where sd.studentId= '"
					+ feesHistoryAction.getUserId()
					+ "' " + "Group by studentId;";
			PreparedStatement ps = con.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				HttpSession session1 = feesHistoryAction.request.getSession();
				feesHistoryAction.session = feesHistoryAction.request
						.getSession(true);
				feesHistoryAction.session.setAttribute("BD",
						rs.getString("b.batchId"));
				user.setStudentName(rs.getString("s.studentName"));
				user.setBatchName(rs.getString("b.batchName"));
				int paidfees = getpaidfees1(rs.getString("studentId"));
				int totalfees = getfees(rs.getString("batchId"));
				DecimalFormat formatter = new DecimalFormat("##,##,###");
				String tf = formatter.format(totalfees);
				String pf = formatter.format(paidfees);
				user.setTotalFees(tf);
				user.setTotalpaidFees(pf);
				feesHistoryAction.list.add(user);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			/* FEESHISTORY TABLE */
			Connection con = DataBaseConnection.getConnection();
			sql = "SELECT c.id,c.courseId,c.coursename,c.fees,pf.paidfees,pf.batchId,pf.studentId from tbl_course as c "
					+ "LEFT JOIN tbl_addbatch as cd on cd.courseId= c.courseId  "
					+ "LEFT JOIN tbl_addstudent as sd on sd.batchId= cd.batchId  "
					+ "LEFT JOIN tbl_payfees as pf on pf.courseId= c.courseId where sd.studentId='"
					+ feesHistoryAction.getUserId()
					+ "' "
					+ "GROUP by c.courseId";

			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ResultSet getValue = ps.executeQuery();
			while (getValue.next()) {
				HttpSession session1 = feesHistoryAction.request.getSession();
				feesHistoryAction.session = feesHistoryAction.request
						.getSession(true);
				feesHistoryAction.session.setAttribute("SD",
						feesHistoryAction.getUserId());
				User user = new User();
				int paidfees = getpaidfees(getValue.getString("studentId"),
						getValue.getString("courseId"));
				int totalfees = getTotalfees(getValue.getString("courseId"));
				int remfees = totalfees - paidfees;
				DecimalFormat formatter = new DecimalFormat("##,##,###");
				String tf = formatter.format(totalfees);
				String rf = formatter.format(remfees);
				sno++;
				user.setSno(sno);
				user.setCourseId(getValue.getString("c.courseId"));
				user.setCourseName(getValue.getString("c.courseName"));
				user.setFees(getValue.getString("c.fees"));
				user.setPaidFees(getValue.getString("pf.paidfees"));
				user.setStudentId(getValue.getString("pf.studentId"));
				user.setTotalFees(tf);
				user.setRemainFees(rf);
				feesHistoryAction.list1.add(user);
				status = "loginRedirectSuccess";

			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return status;
	}/* FEESHISTORY TABLE */

	private static int getTotalfees(String courseId) throws Exception {
		String total;
		int total1 = 0;
		Connection con = DataBaseConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT fees FROM tbl_course where courseId=?");
		ps.setString(1, courseId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			total = rs.getString("fees");
			total1 += Integer.parseInt(total);
		}
		return total1;
	}

	/* FEESHISTORY TABLE */
	private static int getpaidfees(String studentId, String courseId)
			throws Exception {
		String total;
		int total1 = 0;
		Connection con = DataBaseConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT paidfees FROM tbl_payfees where studentId=? and courseId=?");
		ps.setString(1, studentId);
		ps.setString(2, courseId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			total = rs.getString("paidfees");
			total1 += Integer.parseInt(total);
		}
		return total1;
	}

	/* FEESHISTORY LABEL */
	private static int getfees(String batchId) throws Exception {
		String total;
		int total1 = 0;
		Connection con = DataBaseConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT fees FROM tbl_course as c LEFT JOIN tbl_addbatch as cd on cd.courseId= c.courseId where batchId=?");
		ps.setString(1, batchId);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			total = rs.getString("fees");
			total1 += Integer.parseInt(total);
		}
		return total1;
	}

	/* FEESHISTORY LABEL */
	private static int getpaidfees1(String studentId) throws Exception {
		String total;
		int total1 = 0;
		Connection con = DataBaseConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT paidfees FROM tbl_payfees where studentId=?");
		ps.setString(1, studentId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			total = rs.getString("paidfees");
			total1 += Integer.parseInt(total);
		}
		return total1;
	}
}
