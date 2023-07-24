package com.cx.api.hutool;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

public class StrUtilTest {

    @Test
    public void test(){
        System.out.println(ReUtil.isMatch(PatternPool.IPV4.toString() + "(\\/([0-9]$|[0-2][0-9]$|3[0-2]$))?", "1.1.1.1"));

    }

}
