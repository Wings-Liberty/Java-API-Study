package com.cx.api.jdk.spi;

import com.cx.helper.service.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

@Slf4j
public class SpiTest {

    @Test
    public void testLoadCustomInterface() {
        ServiceLoader<Search> serviceLoader = ServiceLoader.load(Search.class);
        Iterator<Search> serviceIterator = serviceLoader.iterator();
        while (serviceIterator.hasNext()) {
            Search next = serviceIterator.next();
            log.info("服务发现的实现类是 [{}]", next.getClass().getName());
        }
    }

    @Test
    public void testLoadDriver(){
        ServiceLoader<Driver> driverServiceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = driverServiceLoader.iterator();
        while (iterator.hasNext()) {
            Driver next = iterator.next();
            log.info("服务发现的 Driver 实现类是 [{}]", next.getClass().getName());
        }
    }

}
