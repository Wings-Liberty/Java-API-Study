package com.cx.api.jdk.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;

public class OutputStreamTest {

    @Test
    public void testWrite() throws IOException {
        OutputStream out = new FileOutputStream("src/readme.txt");
        out.write(257);
        out.write("hello".getBytes("UTF-8"));
        out.close();
    }

}
