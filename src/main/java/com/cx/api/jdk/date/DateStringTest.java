package com.cx.api.jdk.date;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateStringTest {

    @Test
    public void testGetRangeByStr(){
        String start = "2023-06-05 00:00:00";
        String end = "2023-07-04 23:59:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startlocalDateTime = LocalDateTime.parse(start, formatter);
        LocalDate startLocalDate = startlocalDateTime.toLocalDate();
        LocalDateTime endLocalDateTime = LocalDateTime.parse(end, formatter);
        LocalDate endLocalDate = endLocalDateTime.toLocalDate();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (startLocalDate.compareTo(endLocalDate) <= 0){
            System.out.println(startLocalDate.format(dateTimeFormatter));
            startLocalDate = startLocalDate.plusDays(1L);
        }

    }

}
