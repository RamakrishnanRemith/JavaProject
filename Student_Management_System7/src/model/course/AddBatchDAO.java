package model.course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DataBaseConnection;
import common.User;
import controller.course.AddBatchAction;

public class AddBatchDAO {

	public static String addbatc(AddBatchAction addBatchAction) {
		// TODO Auto-generated method stub
		
		String status = "fail";
		String sql = null;
		try {
			java.sql.Connection con = DataBaseConnection.getConnection();
			sql = "SELECT * FROM tbl_course Where courseId='"
					+ addBatchAction.getUserId() + "'";
			java.sql.PreparedStatement ps = con.prepareStatement(sql);

			ResultSet getValue = ps.executeQuery();
			while (getValue.next()) {
				User user = new User();
				user.setCourseId(getValue.getString("courseId"));
				user.setCourseName(getValue.getString("courseName"));

				addBatchAction.list.add(user);
				// set session
				addBatchAction.session.setAttribute("sessioncourseId",
						getValue.getString("courseId")); 
				addBatchAction.session.getAttribute("sessioncourseId");

				status = "success";
			}
		
			java.sql.Connection con1 = DataBaseConnection.getConnection();
			sql = "SELECT b.batchId,b.batchName,abt.batchId IN (SELECT IF(courseId='"+addBatchAction.getUserId()+"',batchId,courseId) FROM tbl_addbatch) FROM tbl_batch AS b LEFT JOIN tbl_addbatch AS abt on b.batchId = abt.batchId GROUP BY b.batchId";
			java.sql.PreparedStatement ps1 = con1.prepareStatement(sql);
			ResultSet getValue1 = ps1.executeQuery();
			while (getValue1.next()) {
				User user = new User();
				user.setBatchId(getValue1.getString("b.batchId"));
				user.setBatchName(getValue1.getString("b.batchName"));
				user.setAddBatcId(getValue1.getString("abt.batchId IN (SELECT IF(courseId='"+addBatchAction.getUserId()+"',batchId,courseId) FROM tbl_addbatch)"));
				addBatchAction.listsss.add(user);
				// set session
				addBatchAction.session.setAttribute("sessionbatchId",
						getValue1.getString("batchId"));
				addBatchAction.session.getAttribute("sessionbatchId");
				status = "success";
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static String addbatcin(AddBatchAction addBatchAction) {
		// TODO Auto-generated method stub
		String status = "fail";
		String insertSql = null;
		try {
			java.sql.Connection con12 = DataBaseConnection.getConnection();
			java.sql.PreparedStatement ps1 = con12
					.prepareStatement("DELETE FROM  tbl_addbatch WHERE  courseId =?");
			ps1.setString(1, addBatchAction.getSessioncourseId());
			int rs = ps1.executeUpdate();
			ps1.close();
			java.sql.Connection con = DataBaseConnection.getConnection();
			String cbox = addBatchAction.getCheckboxvalco();
			String cbocvalue[] = cbox.split(",");
			for (String str : cbocvalue) {
				insertSql = "INSERT INTO tbl_addbatch(courseId,batchId)VALUES (?,?)";
				PreparedStatement ps = con.prepareStatement(insertSql);
				ps.setString(1, addBatchAction.getSessioncourseId());
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