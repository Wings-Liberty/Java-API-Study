package com.cx.helper.service.impl;

import com.cx.helper.service.Search;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileSearch implements Search {
    @Override
    public String search(String keyword) {
        log.info("模拟从[文件]里搜索数据，得到的搜索关键字是 {}", keyword);
        return "result from file";
    }
}
