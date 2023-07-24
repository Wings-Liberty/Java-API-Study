package com.cx.helper.service.impl;

import com.cx.helper.service.Search;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseSearch implements Search {
    @Override
    public String search(String keyword) {
        log.info("模拟从[数据库]里搜索数据，得到的搜索关键字是 {}", keyword);
        return "result from database";
    }
}
