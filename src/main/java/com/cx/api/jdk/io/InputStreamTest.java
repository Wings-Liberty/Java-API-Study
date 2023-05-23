package com.cx.api.jdk.io;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class InputStreamTest {

    @Test
    public void testReadFile() throws IOException {
        // 如果指定的文件不存在就会抛异常。输入输出流和 File 文件不同，File 是抽象的，可以不对应到磁盘文件
        InputStream input = new FileInputStream("src/readme.txt");
        while (true){
            int data = input.read();
            if(data != -1){
                System.out.print(data);
            }else {
                break;
            }
        }
        input.close();
    }

    @Test
    public void testReadZip() throws IOException {
        ZipInputStream zipInput = new ZipInputStream(Files.newInputStream(Paths.get("D:\\tmp\\tmp.zip")), Charset.forName("GBK"));
        ZipEntry zipEntry = null;
        // getNextEntry 同时也是一个状态模式的调用，让 zip 切换到下一个文件，以便直接调用 输入流读写下一个文件
        while ((zipEntry = zipInput.getNextEntry()) != null){
            // zipEntry 本身不具备读写功能
            String zipEntryName = zipEntry.getName();
            System.out.println(zipEntryName);
            int data = 0;
            while ((data = zipInput.read()) != -1){
                System.out.print(data);
            }
        }
    }

}
