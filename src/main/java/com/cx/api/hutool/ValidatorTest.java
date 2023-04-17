package com.cx.api.hutool;

import cn.hutool.core.lang.Validator;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void testPhone(){
        System.out.println(Validator.isMobile("13939103335"));
    }

}
