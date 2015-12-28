package ml.smart_ideas.smarthome.ws.rest;

import java.io.*;
import java.net.*;
import java.util.*;

public class Utils {

    private Utils() {

    }

    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }


    private boolean isHomeServer = true;

    public boolean isHomeServer() {
        return isHomeServer;
    }

    public void setHomeServer(boolean isHomeServer) {
        this.isHomeServer = isHomeServer;
    }

    private boolean isLastConnectionHome = true;

    public boolean isLastConnectionHome() {
        return isLastConnectionHome;
    }

    public void setLastConnectionHome(boolean isLastConnectionHome) {
        this.isLastConnectionHome = isLastConnectionHome;
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

}