package com.cx.api.jdk.string;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    public void testStrFormat(){
        String cmdTemplate = "firewall-cmd --add-rich-rule='rule family=\"ipv4\" source NOT ipset=\"{}\" port port=\"{}\" protocol=\"{}\" reject'";
        String format = StrUtil.format(cmdTemplate, "qwe", "5155", "udp");
        System.out.println(format);
    }

}
