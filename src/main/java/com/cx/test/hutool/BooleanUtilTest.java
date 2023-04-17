package com.cx.test.hutool;

import cn.hutool.core.util.BooleanUtil;
import org.junit.jupiter.api.Test;

public class BooleanUtilTest {

    @Test
    public void booleanUtilTest(){
        // (1 and 2) or (3 and 4) or (5)

        // step1: 用 or 分割得到 [ "1 and 2", "3 and 4", "5" ]
        boolean res1 = true;
        boolean res2 = true;
        boolean res3 = true;
        boolean res4 = true;
        boolean res5 = true;
        // step2: 用 and 分割得到 [ [1, 2], [3, 4], [5] ]
        boolean group1Result = BooleanUtil.and(res1, res2);
        boolean group2Result = BooleanUtil.and(res3, res4);
        boolean group3Result = BooleanUtil.and(res5);

        // step3: 用 or 算最终结果
        boolean result = BooleanUtil.or(group1Result, group2Result, group3Result);

        // 可以传入数组给可变参数
        boolean[] boolArray = new boolean[] {false, true};
        System.out.println(BooleanUtil.and(boolArray));
    }

}
