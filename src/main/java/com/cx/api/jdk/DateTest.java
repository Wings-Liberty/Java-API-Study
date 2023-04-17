package com.cx.api.jdk;

import com.cx.bean.Dog;
import com.cx.bean.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class DateTest {

    @Test
    public void testOptional(){
        User user = new User(1L, "张三", "123", new Dog("小狗"));
        // 级联调用
        System.out.println(Optional.ofNullable(user).map(User::getDog).map(Dog::getName).orElse(null));
    }

}
