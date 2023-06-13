package com.cx.api.hutool;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.net.Ipv4Util;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void testPhone(){
        System.out.println(Validator.isMobile("13939103335"));
    }

    @Test
    public void testNumber() {
        System.out.println(Validator.isNumber("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"));
    }


}
