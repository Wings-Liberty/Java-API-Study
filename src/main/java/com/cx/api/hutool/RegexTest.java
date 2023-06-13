package com.cx.api.hutool;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

public class RegexTest {

    @Test
    public void testExtractMulti(){
        String content = "ZZZaaabbbccc中文1234";
        String resultExtractMulti = ReUtil.extractMulti("(\\w)aa(\\w)", content, "$1-$2");
        System.out.println(resultExtractMulti);
    }


    @Test
    public void testGet(){
        String url = "https://172.16.23.68:9000/protectiohttp://n/upload/20230516/看技术打法和卡拉收费的克里夫-8eb5c4a77371b7fb91ead4c988098aca.docx";
        System.out.println(ReUtil.get(PatternPool.IPV4, url, 0));
        System.out.println(ReUtil.get(PatternPool.get("http\\w?:\\/\\/"), url, 0));

        String str = "branch_2_node_2";
        System.out.println(ReUtil.get("branch_\\w", str, 0));
    }

}
