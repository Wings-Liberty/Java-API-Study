package com.cx.api.jdk;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SimpleTest {

    @Test
    public void test(){
        String str = "22/tcp 3306/tcp 443/tcp 80/tcp 9001/tcp 5155/udp 8303/tcp 8300/tcp 8087/tcp 20008/tcp 23489/tcp 16666/tcp 16667/tcp 16668/tcp 9999/tcp 514/udp 5154/udp 5156/udp 5044/tcp 80/udp 162/udp 8333/tcp 8334/tcp 8335/tcp 5002/tcp 5001/tcp 911/tcp 19001/tcp 19010/tcp 19222/tcp 8888/tcp 8012/tcp 8443/tcp 9095/tcp 21/tcp 6443/tcp 8123/tcp 5601/tcp 8083/tcp 8080/tcp 8848/tcp 19000/tcp 8081/tcp 9002/tcp 7001/tcp 9003/tcp 7009/tcp 8301/tcp 9092/tcp 9669/tcp 39999/tcp 9000/tcp 8403/tcp 5155/tcp 9190/tcp 9301/tcp 8200/tcp 8100/tcp";
        String[] services = str.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append("firewall-cmd --permanent ");
        for (String service : services) {
            sb.append("--add-port=").append(service).append(" ");
        }

//        ArrayList<String> list = Lists.newArrayList(services);
//        Collections.reverse(list);
//        list.forEach(System.out::println);

        sb.append(" && firewall-cmd --reload");
        System.out.println(sb.toString());

    }

}
