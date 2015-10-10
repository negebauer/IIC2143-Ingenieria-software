package Tools.Others;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilities {

	/**
	 * Returns a Date objected parsed from a given string representing a date.
	 * <pre>
	 * {@code
	 * Date date = getDateFromString("10.09.1993 18:58")
	 * System.out.println(date.toString());
	 * >> Fri Sep 10 18:58:00 CLT 1993
	 * }
	 * </pre>
	 * @param dateString The string representing a date in the format "dd.MM.yyyy HH:mm".
	 * @return A date object
	 * @throws ParseException
	 */
	public static Date getDateFromString(String dateString) {
		SimpleDateFormat dateFormat =  new SimpleDateFormat ("dd.MM.yyyy HH:mm");
		try { 
			Date date = dateFormat.parse(dateString); 
			return date;
		} catch (ParseException e) { 
			System.out.println(dateString + " is unparseable using " + dateFormat); 
		}
		return new Date();
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringFromDate(Date date) {
		SimpleDateFormat dateFormat =  new SimpleDateFormat ("EEEE dd.MM.yyyy 'at' HH:mm", Locale.ENGLISH);
		return dateFormat.format(date);
	}
}
