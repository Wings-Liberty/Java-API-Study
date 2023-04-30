package com.cx.api.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * java.lang.reflect.Method类 的三种操作
 * 1. 获取方法对象
 * 2. 获取方法对象的元数据
 * 3. 执行方法
 */
public class MethodTest {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // 获取类的方法Method对象
        listMethods();
    }

    public static void listMethods() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Class.forName("test.User");

        // 1. 获取所有共有方法
        // 2. 获取所有方法
        // 3. 获取指定共有方法
        // 4. 获取指定方法

        Method method = c.getMethod("show", String.class, String.class);
        System.out.println(method);

        System.out.print(Modifier.toString(method.getModifiers()) + " ");
        System.out.print(method.getReturnType().getName() + " ");
        System.out.println(method.getName());
        System.out.println("参数列表：");

        Class<?>[] parameterTypes = method.getParameterTypes();

        for (Class parameterType : parameterTypes) {
            System.out.print(parameterType.getName() + "  ");
        }

        System.out.println();
        System.out.println("执行方法");

        Constructor constructor = c.getConstructor();
        Object o = constructor.newInstance();

        Object[] args = new Object[]{
                "sdgfas", "asf"
        };
        method.invoke(o, args);
    }
}