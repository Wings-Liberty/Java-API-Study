package com.cx.api.jdk.io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class RandomAccessFileTest {

    public static RandomAccessFile raf;

    static {
        try {
            raf = new RandomAccessFile("src/readme.txt", "rwd");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读字节数组
     * @throws IOException
     */
    @Test
    public void testReadByte() throws IOException {
        System.out.println("file pointer at " + raf.getFilePointer());
        System.out.println("file byte count is " + raf.length());
        raf.seek(5L);
        byte[] data = new byte[1024];
        int hasRead = 0;
        System.out.println(raf.readLine());
        while ((hasRead = raf.read(data)) != -1) {
            System.out.println(new String(data, 0, hasRead, StandardCharsets.UTF_8));
        }
    }

    /**
     * 读行
     * @throws IOException
     */
    @Test
    public void testReadLine() throws IOException {
        String line;
        while ((line = raf.readLine()) != null){
            System.out.println(line);
        }
    }

    /**
     * 写
     */
    @Test
    public void testWrite() throws IOException {
        raf.seek(raf.length());
        raf.writeUTF("This is append text.\n");
    }

}
