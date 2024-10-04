package in.cdac.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author CDAC This class reads the property file and provides methods to
 *         access the values in the property file.
 */
public class PropertyHandler {

	private String filename = "connector.properties";
	private static  Properties prop = null;
	private InputStream input = null;

	

	/**
	 * Constructor This reads the property file.
	 */
	public PropertyHandler() {
		try {
			input = PropertyHandler.class.getClassLoader().getResourceAsStream(filename);
			if (input != null) {
				prop = new Properties();
				prop.load(input);
				input.close();
			} else {
				System.out.println("Unable to find " + filename + " file.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return;
		}
	}

	/**
	 * This method returns the OTP version (1.6)
	 * 
	 * @return String
	 */
	
	public  static String geturl() {
		if (prop == null || !prop.containsKey("url")) {
			return null;
		}
		return prop.getProperty("url").trim();
	}
	  
	 /**
		 * This method returns Terminal Id for Otp request (public).
		 * 
		 * @return String
		 */
	
		public  static String getLk() {
			if (prop == null || !prop.containsKey("lk")) {
				return null;
			}
			return prop.getProperty("lk").trim();
		}
		
	 /**
		 * This method returns AUA code
		 * 
		 * @return String
		 */
	
		public static String getAUACode() {
			if (prop == null || !prop.containsKey("auaCode")) {
				return null;
			}
			return prop.getProperty("auaCode").trim();
		}
	  
		public static String getSubAUACode() {
			if (prop == null || !prop.containsKey("subAuaCode")) {
				return null;
			}
			return prop.getProperty("subAuaCode").trim();
		}
			 
}