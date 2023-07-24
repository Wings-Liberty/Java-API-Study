package com.cx.api.jsch;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ssh.ChannelType;
import cn.hutool.extra.ssh.JschRuntimeException;
import cn.hutool.extra.ssh.JschUtil;
import com.google.common.collect.Lists;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ShellCommandTest {

    @Test
    public void testChannelShell() throws JSchException, IOException, InterruptedException {
        Session session = JschUtil.createSession("172.16.23.66", 22, "root", "Linsec@2018");
        String[] commands = new String[]{"pwd", "cd /tmp", "pwd"};
        ChannelShell channel = (ChannelShell) JschUtil.createChannel(session, ChannelType.SHELL);
        //换行符
        String nlStr = new String(new byte[]{10}, "UTF-8");
        OutputStream outputStream = channel.getOutputStream();

        // 回车符
        String crStr = new String(new byte[]{13}, "UTF-8");
        TimeUnit.MILLISECONDS.sleep(500);
        for (String command : commands) {
            // 每行命令添加回车
            command = (nlStr + crStr) + command + (nlStr + crStr);
            // 传输执行单行命令
            outputStream.write(command.getBytes("GB18030"));
            outputStream.flush();
            TimeUnit.MILLISECONDS.sleep(500);
        }

        // 获取打印信息
        List<String> resultList = Lists.newArrayList();
        InputStream inputStream = channel.getInputStream();
        while (inputStream.available() > 0) {
            byte[] buffer = new byte[1024];
            int read = inputStream.read(buffer);
            if (read > 0) {
                resultList.add(new String(buffer));
            }
        }
        resultList.forEach(System.out::println);
    }

    @Test
    public void testChannelExec() throws IOException, JSchException {
        JSch jsch = new JSch();
        Session session;
        session = jsch.getSession("root", "172.16.23.66", 22);
        session.setPassword("linsec@2023");
        // 设置是否提示将连接主机加入到known_hosts中
        session.setConfig("StrictHostKeyChecking", "no");
        // 连接
        session.connect(1000);

        String[] commands = new String[]{"echo aaa", "pwd", "cd /tmp", "pwd"};

        //换行符
        String nlStr = new String(new byte[]{10}, "UTF-8");
        // 回车符
        String crStr = new String(new byte[]{13}, "UTF-8");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChannelExec channel = null;

        for (String command : commands) {
            channel = (ChannelExec) session.openChannel("exec");
            // 每行命令添加回车
            command = (nlStr + crStr) + command + (nlStr + crStr);
            // 输入输出流
            PipedInputStream channelInputStream = new PipedInputStream();
            PipedOutputStream channelOutputStream = new PipedOutputStream();
            channel.setInputStream(new PipedInputStream(channelOutputStream));
            channel.setOutputStream(new PipedOutputStream(channelInputStream));

            channel.setCommand(command);
            channel.connect();


            //读取通道的输出
            InputStream in = channel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            //存放命令的执行结果，如果结果有很多行，则每次line的值不同
            String line;
            //lines用来拼接line结果
            StringBuffer lines = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                // 去除头尾的空格
                line.trim();
                lines = lines.append(line);
            }
            //如果命令执行没有返回值，则直接输出没有返回值
            if ("".equals(String.valueOf(lines))) {
                log.info("命令 {} 执行成功,但没有返回值", command);
            } else {
                //否则将每行返回直接存入到list中
                log.info("结果：{}", lines);
            }
            reader.close();
            channel.disconnect();


        }


    }

    @Test
    public void testSignalCommand() throws JSchException, IOException {
        JSch jsch = new JSch();
        Session session;
        session = jsch.getSession("root", "172.16.23.66", 22);
        session.setPassword("linsec@2023");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        List<String> commands = Lists.newArrayList("pwd", "cd /tmp", "pwd");
        for (String command : commands) {
            ChannelExec channel = (ChannelExec) session.openChannel(ChannelType.EXEC.getValue());
            channel.setCommand(command);
            channel.setInputStream(null);
            channel.setErrStream(null);
            channel.connect();
            InputStream in = channel.getInputStream();
            System.out.println(IoUtil.read(in, CharsetUtil.CHARSET_UTF_8));
        }
    }

}
