package in.cdac.util;
import java.net.InetAddress;

public class MACAddressUtil {

	public static void main(String[] args) {
		
		MacAddressCall call = new MacAddressCall();
		MacObj mac = new MacObj();
		 mac =call.getMacAddress();
	}

}
