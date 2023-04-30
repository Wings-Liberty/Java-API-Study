package com.cx.api.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * java.lang.reflect.Constructor类 的操作分为以下三类
 * 1. 使用类对象获取Constructor对象（一个类可能有多个构造器）
 * 2. 使用Constructor对象获取构造器属性（访问修饰符，构造方法名，参数表中参数类型，获取构造方法上的注解）
 * 3. 使用Constructor对象创建实例对象
 */
public class ConstructorTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // 获取构造器
//        listConstructors();

        // 获取构造器中的元数据（访问修饰符，方法名，参数表参数类型，参数数量，构造器上的注解，参数上的注解等）
//        listConstructorMetadatas();

        // 使用构造器对象new实例对象
        newInstance();
    }

    // 获取所有构造器
    public static void listConstructors() throws ClassNotFoundException, NoSuchMethodException {
        Class c = Class.forName("test.User");

        // 获取所有共有构造器
        Constructor[] constructors = c.getConstructors();

        // 获取所有构造方法
        Constructor[] declaredConstructors = c.getDeclaredConstructors();

        // 根据参数类型获取共有构造器。获取不到就抛异常
        Constructor constructor1 = c.getConstructor(String.class, String.class);

        // 根据参数表参数类型获取构造器
        Constructor constructor2 = c.getDeclaredConstructor(String.class);

    }

    public static void listConstructorMetadatas() throws ClassNotFoundException, NoSuchMethodException {
        Class c = Class.forName("test.User");

        Constructor constructor = c.getConstructor(String.class, String.class);

        // 获取访问修饰符。修饰符包含public private ... static final等
        System.out.print(Modifier.toString(constructor.getModifiers()) + " ");

        System.out.print(constructor.getName() + " ( ");

        Class[] parameterTypes = constructor.getParameterTypes();

        for(Class parameterType : parameterTypes){
            System.out.print(parameterType.getName() + " ");
        }

        System.out.println(");");

    }

    public static void newInstance() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Class.forName("test.User");

        Constructor constructor = c.getDeclaredConstructor(String.class);

        System.out.println("是否有权限使用此构造器：" + constructor.isAccessible());

        if(!constructor.isAccessible()){
            System.out.println("此构造器是私有构造器，在此设置使用权限为true");
            constructor.setAccessible(true);
        }

        Object instance = constructor.newInstance("3113");

        System.out.println(instance);

    }

}
