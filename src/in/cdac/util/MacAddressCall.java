package in.cdac.util;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MacAddressCall 
{

	public static MacObj getMacAddress() 
	{
		MacObj macObj= new MacObj();
		try 
		{
			InetAddress currentIp = getLocalHostLANAddress();
			macObj.setCurrentIp(currentIp.getHostAddress());
			NetworkInterface network = NetworkInterface.getByInetAddress(currentIp);
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) 
			{
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			macObj.setMacAddress(sb.toString());

			return macObj;
		} 
		catch (UnknownHostException e1) 
		{
			e1.printStackTrace();
		} 
		catch (SocketException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	@SuppressWarnings("rawtypes")
	public static InetAddress getLocalHostLANAddress() throws UnknownHostException 
	{
		try 
		{
			InetAddress candidateAddress = null;
			for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) 
			{
				NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
				for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) 
				{
					InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
					if (!inetAddr.isLoopbackAddress()) 
					{
						if (inetAddr.isSiteLocalAddress())
							return inetAddr;
						else if (candidateAddress == null) 
							candidateAddress = inetAddr;
					}
				}
			}
			if (candidateAddress != null) 
			{
				return candidateAddress;
			}
			InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
			if (jdkSuppliedAddress == null) 
			{
				throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
			}
			return jdkSuppliedAddress;
		} 
		catch (Exception e) 
		{
			UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + e);
			unknownHostException.initCause(e);
			throw unknownHostException;
		}
	}

}
