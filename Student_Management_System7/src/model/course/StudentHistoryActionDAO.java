package model.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import common.DataBaseConnection;
import common.User;
import controller.course.StudentHistoryAction;

public class StudentHistoryActionDAO {

	public static String stuhistorytlis(
			StudentHistoryAction studentHistoryAction) throws Exception {
		// TODO Auto-generated method stub
		String status = "fail";
		try {
			Connection con = DataBaseConnection.getConnection();
			String sql = "SELECT courseId,courseName FROM tbl_course Where courseId=?";
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, studentHistoryAction.getSessioncourseId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				studentHistoryAction.setCourseName(rs.getString("courseName"));
				studentHistoryAction.setCourseId(rs.getString("courseId"));
			}
			con.close();
		} catch (Exception e) {
		}
		try {
			Connection con = DataBaseConnection.getConnection();
			String QUERY = "SELECT c.id,c.courseId,b.batchId,s.studentId,c.fees,s.studentName,b.batchName,c.courseName,pf.paidfees from tbl_student as s "
					+ "Left join tbl_addstudent as sd on s.studentId = sd.studentId "
					+ "left join tbl_batch as b on sd.batchId = b.batchId "
					+ "left join tbl_addbatch as cd on b.batchId = cd.batchId "
					+ "left join tbl_course as c on cd.courseId = c.courseId "
					+ "left join tbl_payfees as pf on b.batchId = pf.batchId where  c.courseId =? "
					+ "group by studentId";
			java.sql.PreparedStatement pst = con.prepareStatement(QUERY);
			pst.setString(1, studentHistoryAction.getSessioncourseId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				int paidfees = getpaidfees(rs.getString("courseId"),
						rs.getString("studentId"));
				int totalfees = gettotalfees(rs.getString("courseId"));
				int remfees = totalfees - paidfees;
				DecimalFormat formatter = new DecimalFormat("##,##,###");
				String tf = formatter.format(totalfees);
				String pf = formatter.format(paidfees);
				String rf = formatter.format(remfees);
				user.setStudentName(rs.getString("s.studentName"));
				user.setStudentId(rs.getString("s.studentId"));
				user.setTotalFees(tf);
				user.setPaidFees(pf);
				user.setRemainFees(rf);
				studentHistoryAction.list1.add(user);
				status = "success";
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return status;
	}

	private static int gettotalfees(String courseId) throws Exception {
		String total;
		int total1 = 0;
		Connection con = DataBaseConnection.getConnection();// remith//
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

	private static int getpaidfees(String courseId, String studentId)
			throws Exception {
		// TODO Auto-generated method stub
		String total;
		int total1 = 0;
		Connection con = DataBaseConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT paidfees FROM tbl_payfees where  courseId=? and studentId=?");
		ps.setString(1, courseId);
		ps.setString(2, studentId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			total = rs.getString("paidfees");
			total1 += Integer.parseInt(total);
		}

		return total1;
	}

}