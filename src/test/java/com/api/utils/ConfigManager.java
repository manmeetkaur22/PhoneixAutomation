package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static Properties prop = new Properties();
	private static String env;
	private static String path;

	private ConfigManager() {
	}

	static {
		try {
			env = System.getProperty("env","qa").toLowerCase().trim();
			System.out.println("Running test env ..........." + env);
			switch (env) {
			case "dev" -> path = "config/config.dev.properties";
			case "qa" -> path = "config/config.qa.properties";
			case "uat" -> path = "config/config.uat.properties";
			default ->	path = "config/config.qa.properties";
			}
			InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			if (input == null) {
				throw new RuntimeException("Cannot find the path " + path);
			}

			prop.load(input);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load" + e);
		}
	}

	public static String getProperty(String Key) throws IOException {
		// TODO Auto-generated method stub
		return prop.getProperty(Key);
	}

}
