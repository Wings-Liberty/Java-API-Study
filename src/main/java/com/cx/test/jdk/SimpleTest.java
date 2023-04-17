package com.cx.test.jdk;
import com.cx.bean.Dog;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cx.bean.User;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Function;

public class SimpleTest {

    @Test
    public void test(){
        Map<String, Long> map = Maps.newHashMap();
        long sum = map.values().stream().mapToLong(t -> t).sum();
        System.out.println(sum);
    }

}
