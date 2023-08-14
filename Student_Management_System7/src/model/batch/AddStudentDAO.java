package model.batch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import common.DataBaseConnection;
import common.User;
import controller.batch.AddStudentAction;

public class AddStudentDAO {
	public static String addstu(AddStudentAction addBatchAction) {
		// TODO Auto-generated method stub
		String status = "fail";
		String sql = null;
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			sql = "SELECT * FROM tbl_batch Where batchId='"
					+ addBatchAction.getUserId() + "'";
			java.sql.PreparedStatement ps = con.prepareStatement(sql);

			ResultSet getValue = ps.executeQuery();
			while (getValue.next()) {
				User user = new User();
				user.setBatchId(getValue.getString("batchId"));
				user.setBatchName(getValue.getString("batchName"));

				addBatchAction.list.add(user);
				// set session
				addBatchAction.session.setAttribute("sessionbatchId",
						getValue.getString("batchId"));
				addBatchAction.session.getAttribute("sessionbatchId");

				status = "success";
			}

			java.sql.Connection con1 = DataBaseConnection.getConnection();
			sql = "SELECT s.studentId,s.studentName,ast.studentId FROM tbl_student AS s LEFT JOIN tbl_addstudent AS ast on s.studentId = ast.studentId WHERE s.studentId NOT IN (SELECT IF(batchId='"
					+ addBatchAction.getUserId()
					+ "',batchId,studentId)FROM tbl_addstudent)&& s.delflag='0' ORDER BY s.studentId DESC";
			java.sql.PreparedStatement ps1 = con1.prepareStatement(sql);
			ResultSet getValue1 = ps1.executeQuery();
			while (getValue1.next()) {
				User user = new User();
				user.setStudentId(getValue1.getString("s.studentId"));
				user.setStudentName(getValue1.getString("s.studentName"));
				user.setAddstuId(getValue1.getString("ast.studentId"));
				addBatchAction.listsss.add(user);
				// set session
				addBatchAction.session.setAttribute("sessionstudentId",
						getValue1.getString("studentId"));
				addBatchAction.session.getAttribute("sessionstudentId");
				status = "success";
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	public static String addstuin(AddStudentAction addStudentAction) {
		String status = "fail";
		String insertSql = null;
		try {
			java.sql.Connection con12 = DataBaseConnection.getConnection();
			java.sql.PreparedStatement ps1 = con12
					.prepareStatement("DELETE FROM  tbl_addstudent WHERE  batchId =?");
			ps1.setString(1, addStudentAction.getSessionbatchId());
			int rs = ps1.executeUpdate();
			ps1.close();
			java.sql.Connection con = DataBaseConnection.getConnection();
			String cbox = addStudentAction.getCheckboxval();
			String cbocvalue[] = cbox.split(",");
			for (String str : cbocvalue) {
				insertSql = "INSERT INTO tbl_addstudent(batchId,studentId)VALUES (?,?)";
				PreparedStatement ps = con.prepareStatement(insertSql);
				ps.setString(1, addStudentAction.getSessionbatchId());
				ps.setString(2, str.trim());
				int statusSuccess1 = ps.executeUpdate();
				status = "success";
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

}