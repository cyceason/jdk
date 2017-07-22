package com.cyc.utils;

import org.apache.commons.lang3.StringUtils;

public class CamelUtils {
	public static final char UNDERLINE = '_';

	/**
	 * 大写转_
	 * 
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (StringUtils.isBlank(param)) {
			return null;
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * _转大写
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if (StringUtils.isBlank(param)) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
