package com.env.io.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class GetIpUtil {
	
//	public static String getIpAddress() {
//	    try {
//	      Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//	      InetAddress ip = null;
//	      while (allNetInterfaces.hasMoreElements()) {
//	        NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//	        if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
//	          continue;
//	        } else {
//	          Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
//	          while (addresses.hasMoreElements()) {
//	            ip = addresses.nextElement();
//	            if (ip != null && ip instanceof Inet4Address) {
//	              return ip.getHostAddress();
//	            }
//	          }
//	        }
//	      }
//	    } catch (Exception e) {
//	    	System.err.println("IP地址获取失败" + e.toString());
//	    }
//	    return "";
//	  }
	
	public static String getIpAddress() {
		String ip = "";
        String chinaz = "http://ip.chinaz.com";
 
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
        }
        return ip;
	}
	
}
