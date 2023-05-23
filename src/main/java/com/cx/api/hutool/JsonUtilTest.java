package com.cx.api.hutool;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

public class JsonUtilTest {

    @Test void testComment(){
        String jsonStr = "{\n" +
                "    \"comment\":\"#(comment)\",\n" +
                "    \"isAction\": true,\n" +
                "    \"assetId\":null,\n" +
                "    \"port\":null,\n" +
                "    \"disposalType\":2,\n" +
                "    \"whitelistType\":1,\n" +
                "    \"termOfValidity\":\"#(termOfValidity)\",\n" +
                "    \"alarmWhiteListDTOList\":null,\n" +
                "    \"assetVulWhiteListDTOList\":null,\n" +
                "    \"baseLineWhiteListDTOList\":[\n" +
                "      {\n" +
                "        \"checkTheEntry\":\"#(checkTheEntry)\", // 检查项\n" +
                "        \"assetId\":\"#(assetId)\", // 资产id\n" +
                "        \"checkType\":\"#(checkType)\", // 检查项类型\n" +
                "        \"riskGrade\":\"#(riskGrade)\", // 风险等级\n" +
                "        \"distinctKey\":\"#(distinctKey)\" // distinctKey\n" +
                "      }\n" +
                "    ],\n" +
                "    \"weakPwdWhiteListDTOList\":null,\n" +
                "    \"websiteVulWhiteListDTOList\":null\n" +
                "}";
        System.out.println(jsonStr);
        jsonStr = JSONUtil.parseObj(jsonStr, true).toString();
        System.out.println(jsonStr);
    }

}
