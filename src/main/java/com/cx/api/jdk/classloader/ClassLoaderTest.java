package com.cx.api.jdk.classloader;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ReflectUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;

public class ClassLoaderTest {

    /**
     * 加载外部的类
     *
     * 这个类里 import 的类必须是类加载器所在项目里又的类，否则可能会报错
     * @throws Exception
     */
    @Test
    public void testLoadExternalClass() throws Exception {
        Class<?> userClass = ClassLoaderUtil.loadClass(
                new File("D:\\workspace\\IDEA-workspace\\study\\Boot-Study\\target\\classes"),
                "com.example.bean.User");
        Object user = ReflectUtil.newInstance(userClass, "root", "pass");
        Object username = ReflectUtil.invoke(user, "getUsername");
        // 输出 root
        System.out.println(username);
    }


}
