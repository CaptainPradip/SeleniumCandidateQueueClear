package com.citrix.elearning.candidatemerge.utility;

import java.util.ResourceBundle;

public class PropertyUtil {

	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	public static String getProperty(String key) {
		return bundle.getString(key);
	}

}