package com.cx.api.jdk.math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    public void testScale(){
        BigDecimal bigDecimal = BigDecimal.valueOf(0, 4);
        System.out.println(bigDecimal.toString());
        if(bigDecimal.doubleValue() == 0.0){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }

}
