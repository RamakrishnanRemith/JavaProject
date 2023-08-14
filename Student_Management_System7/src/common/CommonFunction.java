package common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

/**
 * CommonFunction
 * @author Prem Kumar
 *
 */
public class CommonFunction {
	public static String generateMd5(String password) {
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance("MD5");
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hashInBytes=md.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuilder sb=new StringBuilder(2*hashInBytes.length);
		for(byte b:hashInBytes) {
			sb.append(String.format("%02x",b ));
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	// feesFormat method used to change fees format
	public static String feesFormat(int fee) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("##,##,###");
		String fees = df.format(fee);
		return fees;
	}
}