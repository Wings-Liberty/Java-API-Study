package com.cx.api.hutool;

import cn.hutool.core.lang.UUID;
import org.junit.jupiter.api.Test;

public class UuidUtilTest {

    @Test
    public void test(){
        System.out.println(UUID.fastUUID().toString(false).replace("-", "_"));
    }

}
