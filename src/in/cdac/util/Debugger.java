package in.cdac.util;
import in.cdac.env.EnvironmentReader;
public class Debugger 
{
	/*
	 * private EnvironmentReader envReader = null ; private Boolean flag = null ;
	 */
	
	public Debugger() {
		
	}
	/*
	 * public Debugger (EnvironmentReader envReader) { this.envReader = envReader;
	 * this.flag = Boolean.parseBoolean( this.envReader.getDebugFlag() ); }
	 */

	public void printStackTrace(Exception excp, boolean debug)
	{
		if ( debug )
		{
			excp.printStackTrace();
		}
	}

	public void printLog(String log, boolean debug) 
	{
		
		if (debug)
		{
			System.out.println(log);
		}
		
	}
}
