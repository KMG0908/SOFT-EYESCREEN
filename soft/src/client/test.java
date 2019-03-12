package client;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

public class test {
	public static void main(String[] args) {
		try {
			for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
		        //Perhaps networkInterface.isVirtual() will help you identify the correct one?
				/*System.out.println(networkInterface.getName() + "\t" + networkInterface.getDisplayName());
				System.out.println(networkInterface.getInterfaceAddresses());
				System.out.println();*/
				if(networkInterface.getName().equals("wlan0")) {
					Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
					InetAddress ia = inetAddresses.nextElement();
					System.out.println(ia.getHostAddress());
					break;
				}
		    }
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*String ip = "";
		
		Enumeration en = null;
		try {
			en = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        while(en.hasMoreElements()) {
             NetworkInterface ni = (NetworkInterface) en.nextElement();
            
             Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
             while(inetAddresses.hasMoreElements()) {
                 InetAddress ia = inetAddresses.nextElement();
                 if (ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
                     byte[] address = ia.getAddress();
                     if(address[0]==127) continue;
                    
                     ip = ia.getHostAddress();
                     break;
                 }
             }
             if(ip.length()>0) break;
        }
        
        System.out.println(ip);*/
	}
}
