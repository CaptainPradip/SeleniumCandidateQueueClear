package com.citrix.elearning.candidatemerge.utility;

import java.util.ResourceBundle;

/**
 * This Class for read property file data.
 *
 * @author Pradip.Nemane
 *
 */
public class PropertyUtil {

	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	/**
	 * Method for get value from property file using key.
	 *
	 * @param key
	 * @return value
	 */
	public static String getProperty(String key) {
		return PropertyUtil.bundle.getString(key);
	}

}