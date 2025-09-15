package com.utils;

import jakarta.servlet.http.HttpServletResponse;

public class CacheControl {
	public static void clearCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	}
}
