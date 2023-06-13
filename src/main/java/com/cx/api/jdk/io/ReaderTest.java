package com.cx.api.jdk.io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class ReaderTest {

    /**
     * 从文本文件里读取字符
     */
    @Test
    public void testReadChar() throws IOException {
        Reader reader = new FileReader("src/readme.txt");
        while (true) {
            int n = reader.read();
            if (n == -1) {
                break;
            }
            System.out.print((char) n);
        }
        reader.close();
    }

    @Test
    public void testInputToReader() throws FileNotFoundException, UnsupportedEncodingException {
        // 持有InputStream:
        InputStream input = new FileInputStream("src/readme.txt");
        // 变换为Reader:
        Reader reader = new InputStreamReader(input, "UTF-8");
    }

}
