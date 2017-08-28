package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	//java.util.Date->java.sql.Date
	public static java.sql.Date convertDate(java.util.Date d){
		java.sql.Date d2 = new java.sql.Date(d.getTime());
		return d2;
	}
	
	//String -> java.sql.Date
	public static java.sql.Date stringToDate(String sdate){
		java.sql.Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date d2 = sdf.parse(sdate);
			d = convertDate(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
}
