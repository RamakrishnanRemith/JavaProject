package Admin;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.opensymphony.xwork2.ActionSupport;

import common.CommonFunction;
import common.DataBaseConnection;

/**
 * AdminRegisterAction
 * 
 * @author Prem Kumar
 *
 */
public class AdminRegisterAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String adminId, userName, mailId, contactNo, password,
			confirmPassword;
	private String dob;
	private int gender;

	/**
	 * 
	 * Method register
	 */
	public String register() {
		int statusSuccess = 0, exsitingFlag = 0;
		String status = "fail", insertSql, existingDataSql, sql;
		String password = new String(CommonFunction.generateMd5(getPassword()));
		try {
			//existing validation
			java.sql.Connection con = DataBaseConnection.getConnection();
			existingDataSql = "SELECT id,userName,mailId FROM tbl_admin WHERE delFlag = 0";
			java.sql.PreparedStatement pselect = con
					.prepareStatement(existingDataSql);
			int id = 0;
			String label = "AD";
			ResultSet rs = pselect.executeQuery();
			String testmail = null;
			while (rs.next()) {
				testmail = rs.getString("mailId");
				if ((testmail.equals(getMailId()))) {
					exsitingFlag = 1;
				}
			}
			if (exsitingFlag == 0) {
				//21 years validation
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 21);
				String[] date = getDob().split("-");
				int year = Integer.parseInt(date[0]);
				int month = Integer.parseInt(date[1]);
				int day = Integer.parseInt(date[2]);
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, year);
				cal1.set(Calendar.MONTH, month);
				cal1.set(Calendar.DAY_OF_MONTH, day);
				boolean flag = calendar.getTime().before(cal1.getTime());
				if (flag == false) {
					// ID generation
					sql = "SELECT id FROM tbl_admin ORDER BY id DESC Limit 1";
					java.sql.PreparedStatement pselect1 = con
							.prepareStatement(sql);
					ResultSet rs1 = pselect1.executeQuery();
					if (rs1.next()) {
						id = rs1.getInt("id") + 1;
					}
					String userId = String.format(label + "%05d", id);
					if (getPassword().equals(getConfirmPassword())) {
						// insert process
						insertSql = "INSERT INTO tbl_admin(adminId,UserName, password, mailId,dob,contactNo,gender)VALUES (?,?,?,?,?,?,?)";
						java.sql.PreparedStatement ps = con
								.prepareStatement(insertSql);
						ps.setString(1, userId);
						ps.setString(2, getUserName());
						ps.setString(3, password);
						ps.setString(4, getMailId());
						ps.setString(5, getDob());
						ps.setString(6, getContactNo());
						ps.setInt(7, getGender());
						statusSuccess = ps.executeUpdate();
						if (statusSuccess == 1) {
							status = "success";
						} else {
							System.out.println("error");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Password And Confirm Password Are Not Same",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Please Enter above 20 years", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "MailID is Already Exist",
						"Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	/**
	 * 
	 * @return adminId
	 */
	public String getAdminId() {
		return adminId;
	}

	/**
	 * 
	 * @param adminId
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return mailId
	 */
	public String getMailId() {
		return mailId;
	}

	/**
	 * 
	 * @param mailId
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	/**
	 * 
	 * @return dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * 
	 * @param dob
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * 
	 * @return contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * 
	 * @param contactNo
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * 
	 * @param confirmPassword
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * 
	 * @return gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
}