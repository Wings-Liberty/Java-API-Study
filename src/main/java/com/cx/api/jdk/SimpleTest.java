package com.cx.api.jdk;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class SimpleTest {

    @Test
    public void test(){
        String[] strs = {"111", "222"};
        Logger logger = LoggerFactory.getLogger(SimpleTest.class);
        logger.info(Arrays.toString(strs));
    }

}
