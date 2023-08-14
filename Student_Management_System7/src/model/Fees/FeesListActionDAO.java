package model.Fees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import common.DataBaseConnection;
import common.User;
import controller.Fees.FeesListAction;

public class FeesListActionDAO {
	/* FEES LIST */
	public static String feesviewlist(FeesListAction feesListAction) {
		// TODO Auto-generated method stub
		HttpSession session = feesListAction.request.getSession();
		session = feesListAction.request.getSession(true);
		String status = "loginRedirectFail";
		String sql = null;
		/*// Search Condition
		if (feesListAction.searchText != null
				&& feesListAction.searchText.length() > 0) {
			feesListAction.searchCondition = " And studentId LIKE '%"
					+ feesListAction.searchText.trim()
					+ "%' OR studentName LIKE '%"
					+ feesListAction.searchText.trim() + "%'";
		}*/
		// select process
		try {
			Connection con = DataBaseConnection.getConnection();
			sql = "SELECT pf.studentId,c.fees,b.batchId,pf.paidfees,s.studentName,b.batchName,pf.delFlag,c.courseId From tbl_payfees as pf "
					+ "LEFT JOIN tbl_student as s on pf.studentId=s.studentId "
					+ "LEFT JOIN tbl_batch as b on pf.batchId=b.batchId "
					+ "LEFT JOIN tbl_course as c on pf.courseId=c.courseId " 
							.concat(feesListAction.searchCondition)
							.concat(" GROUP By pf.studentId")
							.concat(feesListAction.sortCondition)
					
					+ " LIMIT "
					+ (feesListAction.pageid - 1)
					+ " , "
					+ feesListAction.recordsPerPage;
			System.out.println(sql);
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			System.out.println(ps.toString());
			ResultSet getValue = ps.executeQuery();
			int sno = 0;
			feesListAction.list = new ArrayList<User>();
			while (getValue.next()) {
				User user = new User();
				int paidfees = getpaidfees(getValue.getString("studentId"));
				int totalfees = getTotalfees(getValue.getString("batchId"));
				int remfees = totalfees - paidfees;
				DecimalFormat formatter = new DecimalFormat("##,##,###");
				String tf = formatter.format(totalfees);
				String pf = formatter.format(paidfees);
				String rf = formatter.format(remfees);
				sno++;
				user.setSno(sno);
				user.setStudentId(getValue.getString("pf.studentId"));
				user.setBatchId(getValue.getString("b.batchId"));
				user.setCourseId(getValue.getString("c.courseId"));
				user.setStudentName(getValue.getString("s.studentName"));
				user.setBatchName(getValue.getString("b.batchName"));
				user.setTotalFees(tf);
				user.setRemainFees(rf);
				user.setPaidFees(pf);
				user.setDelflag(getValue.getInt("delFlag"));

				// set user in list
				feesListAction.list.add(user);

			}
			if (!feesListAction.list.equals(null)) {
				status = "loginRedirectSuccess";
			} else {
				System.out.println("loginRedirectFail");
			}

		} catch (Exception e1) {
			System.out.println(e1);
		}

		return status;
	}

	private static int getTotalfees(String batchId) throws Exception {
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

	private static int getpaidfees(String studentId) throws Exception {
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

	/* FEES DEtail */
	public static String feeDetailviewlist(FeesListAction feesListAction)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = feesListAction.request.getSession();
		session = feesListAction.request.getSession(true);
		String status = "loginRedirectSuccess";
		/* FEES DETAIL LABEL VALUE */
		try {
			User user = new User();
			Connection con = DataBaseConnection.getConnection();
			String sql = "SELECT c.courseId,b.batchId,s.studentId,c.fees,s.studentName,b.batchName,c.courseName from tbl_student as s "
					+ "left join tbl_addstudent as sd on s.studentId = sd.studentId "
					+ "left join tbl_batch as b on sd.batchId = b.batchId left join tbl_addbatch as cd on b.batchId = cd.batchId "
					+ "left join tbl_course as c on cd.courseId = c.courseId "
					+ "left join tbl_payfees as pf on b.batchId = pf.batchId "
					+ "where s.studentId='"
					+ feesListAction.getSid()
					+ "' "
					+ "and c.courseId ='"
					+ feesListAction.userId
					+ "' "
					+ "group by c.courseId";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int paidfees = getpaidfees(rs.getString("studentId"),
						rs.getString("courseId"));
				int totalfees = gettotalfees(rs.getString("courseId"));
				int remfees = totalfees - paidfees;
				int totalpaidfees = totalfees - remfees;
				feesListAction.setStudentName(rs.getString("s.studentName"));
				feesListAction.setBatchName(rs.getString("b.batchName"));
				feesListAction.setCourseName(rs.getString("c.courseName"));
				DecimalFormat formatter = new DecimalFormat("##,##,###");
				String tf = formatter.format(totalfees);
				String pf = formatter.format(paidfees);
				String rf = formatter.format(remfees);
				String tpf = formatter.format(totalpaidfees);
				feesListAction.setTotalFees(tf);
				feesListAction.setPaidFees(pf);
				feesListAction.setRemainFees(rf);
				feesListAction.setTotalpaidFees(tpf);
				feesListAction.listdetail.add(user);
				session.setAttribute("CD", feesListAction.getUserId());
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* FEES DETAIL TABLE VALUE */
		try {
			Connection con = DataBaseConnection.getConnection();
			String QUERY = "select paydate,paidfees from tbl_payfees where studentId='"
					+ feesListAction.getSid()
					+ "' "
					+ "And courseId='"
					+ feesListAction.userId + "' ";

			PreparedStatement pst = con.prepareStatement(QUERY);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				String total = rs.getString("paidfees");
				int total1 = Integer.parseInt(total);
				DecimalFormat formatter = new DecimalFormat("##,##,###");
				String pf = formatter.format(total1);
				user.setPaidDate(rs.getString("paydate"));
				user.setPaidFees(pf);
				feesListAction.listdetailtable.add(user);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return status;
	}

	private static int gettotalfees(String courseId) throws Exception {
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

	private static int getpaidfees(String studentId, String courseId)
			throws Exception {
		int total1 = 0;
		Connection con = DataBaseConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT paidfees FROM tbl_payfees where studentId=? and courseId=?");
		ps.setString(1, studentId);
		ps.setString(2, courseId);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			total1 += rs.getInt("paidfees");
		}
		return total1;
	}

	/* FEE DETAIL REGISTER VALUE TAKEN */
	public static String feeDetailregisterscreen(FeesListAction feesListAction)
			throws Exception {
		HttpSession session = feesListAction.request.getSession();
		session = feesListAction.request.getSession(true);
		String status = "fail", insertSql, existingDataSql, sql;
		int statusSuccess = 0;
		try {
			Connection con = DataBaseConnection.getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT c.courseName,c.courseId,b.batchName,s.studentId,s.studentName,pf.paidfees,c.fees,pf.batchId from tbl_student as s "
							+ "left join tbl_addstudent as sd on s.studentId = sd.studentId "
							+ "left join tbl_batch as b on sd.batchId = b.batchId "
							+ "left join tbl_addbatch as cd on b.batchId = cd.batchId "
							+ "left join tbl_course as c on cd.courseId = c.courseId "
							+ "left join tbl_payfees as pf on b.batchId = pf.batchId "
							+ "where s.studentId='"
							+ feesListAction.getSid()
							+ "' "
							+ "and c.courseId ='"
							+ feesListAction.getUserId()
							+ "' "
							+ "Group by coursename");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				int paidfees = getpaidfeesdelreg(rs.getString("studentId"),
						rs.getString("courseId"));
				int totalfees = gettotalfeesdelreg(rs.getString("courseId"));
				int remainfees = totalfees - paidfees;
				feesListAction.setBid(rs.getString("pf.batchId"));
				feesListAction.setStudentName(rs.getString("s.studentName"));
				feesListAction.setBatchName(rs.getString("b.batchName"));
				feesListAction.setCourseName(rs.getString("c.courseName"));
				DecimalFormat formatter1 = new DecimalFormat("##,##,###");
				String tf = formatter1.format(totalfees);
				feesListAction.setTotalFees(tf);
				DecimalFormat formatter2 = new DecimalFormat("##,##,###");
				String rf = formatter2.format(remainfees);
				feesListAction.setRemainFees(rf);
				feesListAction.session.setAttribute("FEES",
						rs.getString("c.fees"));
				feesListAction.session.getAttribute("FEES");
				feesListAction.session.setAttribute("REMAINFEES", remainfees);
				feesListAction.session.getAttribute("REMAINFEES");
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return status;
	}

	private static int getpaidfeesdelreg(String studentId, String courseId)
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

	private static int gettotalfeesdelreg(String courseId) throws Exception {
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
}
