package com.cx.test.jdk.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

    @Test
    public void getTodayTime(){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm:ss");
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).format(f));
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).format(f));

    }

}
