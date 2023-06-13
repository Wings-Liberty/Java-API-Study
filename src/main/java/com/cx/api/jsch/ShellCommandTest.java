package com.cx.api.jsch;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ssh.ChannelType;
import cn.hutool.extra.ssh.JschRuntimeException;
import com.google.common.collect.Lists;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ShellCommandTest {

    @Test
    public void testChannelShell() throws JSchException, IOException {

        JSch jsch = new JSch();
        Session session;
        session = jsch.getSession("root", "172.16.23.66", 22);
        session.setPassword("linsec@2023");
        // 设置是否提示将连接主机加入到known_hosts中
        session.setConfig("StrictHostKeyChecking", "no");
        // 连接
        session.connect(1000);

        OutputStream execCommandStream = null;
        ChannelShell channel = null;
        String[] commands = new String[]{"pwd", "cd /tmp", "pwd"};

        // 开启通道(一个线程)
        channel = (ChannelShell) session.openChannel("shell");
        // 输入输出流
        PipedInputStream channelInputStream = new PipedInputStream();
        PipedOutputStream channelOutputStream = new PipedOutputStream();
        channel.setInputStream(new PipedInputStream(channelOutputStream));
        channel.setOutputStream(new PipedOutputStream(channelInputStream));
        channel.connect(100);
        execCommandStream = channel.getOutputStream();
        // 将命令中存在动态参数的赋值
        // 执行命令
        int maxWidth = 80;
        for (String sshCommand : commands) {
            maxWidth = Math.max(maxWidth, sshCommand.length());
        }
        maxWidth += 100;
        log.info("jsch 模拟终端的参数为 col: {}, row: {}，是否创建连接 {}，命令内容是: {}", maxWidth, commands.length * 11, channel.isConnected(), Arrays.toString(commands));
        // 后 3 个参数参考 com.jcraft.jsch.ChannelSession 的默认值，col 表示 jsch 模拟的终端里一行可容纳字符数，如果一条命令因为超过这个字符数的限制，jsch就会为其自动换行或截断命令，这可能会导致终端不能正确识别命令
        // 长度再加一些是因为返回的结果里会还会携带命令提示符，期望窗口最大宽度应该至少能容纳命令提示符和每行完整的命令（返回结果里若有比最长命令行还长的则暂时不在考虑范围内）
        channel.setPtySize(maxWidth, commands.length * 11, 640, 480);
        //换行符
        String nlStr = new String(new byte[]{10}, "UTF-8");

        // 回车符
        String crStr = new String(new byte[]{13}, "UTF-8");

        // 等待一小会让终端正常运行起来，否则有小概率出现这种情况：连上了终端，但终端还没正常运行起来，导致输入的前几条命令没有在终端执行，这会导致一些问题（比如第二条命令是建立在第一条命令正常执行后才能正常执行，结果第一条命令没有在终端执行）
        // eg: 防火墙执行 acl 前需要先执行 configure 让终端进入配置状态，但终端没正常运行起来时就输入 configure 导致终端没有进入配置状态，在后续执行 acl 命令时提示找不到此命令（acl 命令只有在终端进入配置状态后才能识别）
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String command : commands) {
            // 每行命令添加回车
            command = (nlStr + crStr) + command + (nlStr + crStr);
            // 传输执行单行命令
            execCommandStream.write(command.getBytes("GB18030"));
            execCommandStream.flush();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 获取打印信息

        BufferedReader reader = new BufferedReader(new InputStreamReader(channelInputStream));
        String commandPrompt = reader.readLine().trim();
        log.info("命令提示符: {}", commandPrompt);

        // 假设没有 error
        reader.lines().filter(line -> !line.trim().equals(commandPrompt)).forEach(line -> {
            // 如果命令以命令提示符开头，说明命令被执行了
            // 如果有命令没有提示符，说明命令没有被执行
            // 否则就是命令的返回结果（可能是正常结果，也可能是异常信息）
        });

        byte[] buffer = new byte[1024];
        int read = channelInputStream.read(buffer);
        log.info("执行完命令");
        String result = null;
        if (read > 0) {
            result = new String(buffer, 0, read, StandardCharsets.UTF_8);
            log.info("SSH远程命令执行 结果:\n{}", result);
        }

        channelInputStream.close();
        channelOutputStream.close();

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
            ChannelExec channel = (ChannelExec)session.openChannel(ChannelType.EXEC.getValue());
            channel.setCommand(command);
            channel.setInputStream(null);
            channel.setErrStream(null);
            channel.connect();
            InputStream in = channel.getInputStream();
            System.out.println(IoUtil.read(in, CharsetUtil.CHARSET_UTF_8));
        }
    }

}
