package com.rainbow.web.netty;


import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CoreUtil {
    public static final String TRACE_ID = "TRACE_ID";
    public static final String REQUEST_IP = "REQUEST_IP";
    public static final String COMMA = ",";

    //本机IP
    private static String LocalIP = "";
    //进程ID
    private static int PID = 0;

    //初始化
    static {
        LocalIP = getMachineIP();
        PID = getPID();
    }

    public static List<Integer> strArray2List(String str){
        List<Integer> ids = new ArrayList<>();
        if(!StringUtils.isEmpty(str)){
            for (String id : str.split(COMMA)) {
                ids.add(Integer.parseInt(id));
            }
        }
        return ids;

    }

    /**
     * 压缩参数(Compression parameters)
     * 通过MD5摘要算法起到压缩作用
     * */
    public static String cp(String ... s){
        String str = "";
        for (String s1 : s) {
            str += s1;
            str += ',';
        }
        return getMd5Result(str);
    }

    /**
     * 将字符串加密
     * @param plainText 被加密字符串
     * @author xlizy
     * */
    public static String getMd5Result(String plainText){
        String str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if(i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    //获取本机IP
    private static String getMachineIP(){
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = (InetAddress) address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    public static int getPID(){
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }
}
