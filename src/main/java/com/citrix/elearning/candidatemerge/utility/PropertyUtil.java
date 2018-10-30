package com.citrix.elearning.candidatemerge.utility;

import java.util.ResourceBundle;

/**
 * This class for reading property files data.
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
	 *            key from property file.
	 * @return value from property file.
	 *
	 */
	public static String getProperty(String key) {
		return bundle.getString(key);
	}

}