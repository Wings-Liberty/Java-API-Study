package com.cx.api.jdk.io;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterTest {

    @Test
    public void testWrite() throws IOException {
        try (Writer writer = new FileWriter("src/readme.txt", true)){
            // 写入单个字符'H'
            writer.write('H');
            // 写入char[]
            writer.write("Hello".toCharArray());
            // 写入String
            writer.write("Hello");
        }
    }

}
