package com.cx.api.hutool;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.net.Ipv4Util;
import org.junit.jupiter.api.Test;

public class Ipv4UtilTest {

    @Test
    public void testIp(){
        String ip = "172.16.23.68/24";
        String[] ipAndMask = ip.split("/");
        System.out.println(Ipv4Util.getBeginIpStr(ipAndMask[0], Integer.valueOf(ipAndMask[1])));
        System.out.println(Ipv4Util.getEndIpStr(ipAndMask[0], Integer.valueOf(ipAndMask[1])));
    }

    @Test
    public void testIpv4ToLong(){
        long ip = Ipv4Util.ipv4ToLong(" 172.21.10.215");
        System.out.println(ip);
    }

    @Test
    public void testIsIp(){
        String ip = "172.16.23.68/24";

        System.out.println(Validator.isIpv4(ip));
    }

}
