package common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Prem kumar
 *
 */
public class DateValidation {
	public static boolean dateValidation(int age ,Date selectedDate) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-age);
		boolean flag = calendar.getTime().before(selectedDate);
		return flag;
	}
}