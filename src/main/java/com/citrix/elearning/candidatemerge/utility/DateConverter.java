package com.citrix.elearning.candidatemerge.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pradip.Nemane
 *
 */
public class DateConverter {
	/**
	 *
	 * @param dateFormat
	 *            ex. dd/mm/yyyy
	 * @param dateInString
	 *            ex 04/12/2018
	 * @return {@link Date}
	 * @throws ParseException
	 *             {@link ParseException}}
	 */
	public static Date convertTextToDate(String dateFormat, String dateInString) throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		return sdf.parse(dateInString);

	}

	public static String millisecondsToTime(long milliseconds) {
		long minutes = (milliseconds / 1000) / 60;
		long seconds = (milliseconds / 1000) % 60;
		String secondsStr = Long.toString(seconds);
		String secs;
		if (secondsStr.length() >= 2) {
			secs = secondsStr.substring(0, 2);
		} else {
			secs = "0" + secondsStr;
		}

		return minutes + ":" + secs + " sec";
	}
}
