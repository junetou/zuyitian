package org.andy.work.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {
	
	private static final Properties prop = new Properties();
	private static final Logger log = Logger.getLogger(Config.class);
	static {
		try {
			log.info("配置：=======》"+Config.class.getResourceAsStream("config.properties"));
			prop.load(Config.class.getResourceAsStream("config.properties"));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	public static String get(String key) {
		return prop.getProperty(key);
	}
}
