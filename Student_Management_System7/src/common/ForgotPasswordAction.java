package common;

import java.util.Random;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ForgotPasswordAction
 * @author Prem kumar
 *
 */
public class ForgotPasswordAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String mailId;
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
	/*
	 * forgotPassword
	 */
	public String forgotPassword() {
		String status ="fail",randomPassword,letters;
		int length = 5;
		try {
			//random password generation
			letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRXTUVWXYZ1234567890+!@#$%&";
			Random random_method = new Random();
			char password[] = new char[length];
			for (int i=0;i<length;i++) {
				password[i] = letters.charAt(random_method.nextInt(letters.length()));
			}
			randomPassword = new String(password);
			status = "success";
		} catch (Exception e) {
			System.out.println("Fail");
		}
		return status;
	}
	//validation process
	public void validate() {
		if(mailId.length()<1){
			addFieldError("mailId","Please Enter The MailId");
		} else {
			
		}
	}
}
