package com.cx.api.jdk.reflect;

import com.cx.bean.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * java.lang.reflect.Field类 的操作分为3类
 * 1. 获取成员变量的Field对象
 * 2. 使用Field对象获取成员变量的元数据（修饰符，声明类型，变量名）
 * 3. 使用Field对象获取和修改成员变量的值
 */
public class FieldTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // 获取类的成员变量
//        listFields();

        // 获取成员变量的Field对象的元数据
//        listFieldMetadata();

        // 获取和修改成员变量的值
        setGetVal();
    }

    public static void listFields() throws ClassNotFoundException, NoSuchFieldException {
        Class c = Class.forName("test.User");

        // 获取所有共有成员变量
        Field[] fields = c.getFields();

        // 获取所有成员变量
        Field[] declaredFields = c.getDeclaredFields();

        // 获取指定的共有成员变量
        Field field1 = c.getField("id");
        System.out.println(field1);

        // 获取指定的成员变量
        Field field2 = c.getDeclaredField("name");
        System.out.println(field2);
    }

    public static void listFieldMetadata() throws ClassNotFoundException, NoSuchFieldException {
        Class c = Class.forName("test.User");

        Field field = c.getField("id");

        System.out.print(Modifier.toString(field.getModifiers()) + " ");
        System.out.print(field.getType().getName() + " ");
        System.out.println(field.getName());
    }

    public static void setGetVal() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Class.forName("test.User");

        Constructor constructor = c.getConstructor(String.class, String.class);

        User instance = (User)constructor.newInstance("42133", "zhangsan");

        Field field = c.getDeclaredField("name");
        // 设置私有变量的访问权限为true
        field.setAccessible(true);
        System.out.println("修改前，变量值为：" + field.get(instance));
        field.set(instance, "lisi");
        System.out.println("修改后，变量值为：" + field.get(instance));


        // 获取变量值的方法还有很多
//        field.getShort();
//        field.getBoolean();

    }

}
