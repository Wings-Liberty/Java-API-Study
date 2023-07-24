package com.cx.api.hutool;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

public class IdUtilTest {

    @Test
    public void generatorId(){
        Snowflake snowflake = IdUtil.createSnowflake(1L, 1L);
        for (int i = 0; i < 10; i++) {
            System.out.println(snowflake.nextId());
        }
    }

}
