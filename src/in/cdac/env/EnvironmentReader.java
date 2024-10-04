package in.cdac.env;

import java.util.Properties;

import in.cdac.model.RequestObject;

/**
 * This class loads the property file values
 * @author CDAC
 *
 */
public class EnvironmentReader
{
	private Properties prop = null ;
	RequestObject reqObj;
	public EnvironmentReader(String file) 
	{
		this.prop = new Properties();
		
		try 
		{
			//this.prop.load( getClass().getClassLoader().getResourceAsStream("config.properties") );
			this.prop.load( getClass().getClassLoader().getResourceAsStream(file));
		}
		catch ( Exception e )
		{
			System.out.println("Config File not found");
			System.exit(0);

		}
	}
	
	/**
	 * Get Vault URL
	 * @return
	 */
	public String getVaultURL() 
	{
		if ( !this.prop.containsKey("vaulturl") )
		{
			return null;
		}
		String getuidurl = (String) this.prop.get("vaulturl");
		return getuidurl;
	}
	
	/**
	 * Get proxy  URL
	 * @return
	 */
	public String getProxyURL() 
	{
		if ( !this.prop.containsKey("proxyURL") )
		{
			return null;
		}
		String getproxyurl = (String) this.prop.get("proxyURL");
		return getproxyurl;
	}
	
	/**
	 * Get proxy  port
	 * @return
	 */
	public String getProxyPort() 
	{
		if ( !this.prop.containsKey("proxyPort") )
		{
			return null;
		}
		String getproxyport = (String) this.prop.get("proxyPort");
		return getproxyport;
	}
	
	/**
	 * Flag to debug the connector
	 * @return
	 */
	public String getDebugFlag() 
	{
		if ( !this.prop.containsKey("debug") )
		{
			return null;
		}
		String key = (String) this.prop.get("debug");
		return key;
	}
	
	/**
	 * Flag to print the Valut XML
	 * @return
	 */
	public boolean getAC_SA_LKfromProp() 
	{
		if(prop == null || !prop.containsKey("usevalues"))
		{
			return false;
		}
		return Boolean.parseBoolean(prop.getProperty("usevalues"));
	}
	
	/**
	 * Set AC
	 * @return
	 */
	public String getAC() 
	{
		if ( !this.prop.containsKey("ac") )
		{
			return null;
		}
		String key = (String) this.prop.get("ac");
		return key;
	}
	
	/**
	 * Set sa
	 * @return
	 */
	public String getSA() 
	{
		if ( !this.prop.containsKey("sa") )
		{
			return null;
		}
		String key = (String) this.prop.get("sa");
		return key;
	}
	
	/**
	 * Set sa
	 * @return
	 */
	public String getLK() 
	{
		if ( !this.prop.containsKey("lk") )
		{
			return null;
		}
		String key = (String) this.prop.get("lk");
		return key;
	}
}