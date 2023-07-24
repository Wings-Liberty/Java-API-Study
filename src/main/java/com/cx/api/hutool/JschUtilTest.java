package com.cx.api.hutool;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ssh.JschUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JschUtilTest {

    @Test
    public void testSession() {
        Session session = JschUtil.getSession("172.16.23.66", 22, "root", "Linsec@2018");

//        String result = JschUtil.exec(session, "pwd && cd /tmp && pwd", StandardCharsets.UTF_8);
        String result = JschUtil.execByShell(session, "pwd && cd /tmp && pwd", StandardCharsets.UTF_8);
        System.out.println(result);
        result = JschUtil.exec(session, "cd /tmp", StandardCharsets.UTF_8);
        System.out.println(result);
        result = JschUtil.exec(session, "pwd", StandardCharsets.UTF_8);
        System.out.println(result);
    }

    @Test
    public void testShell() throws Exception {
        Session session = JschUtil.getSession("172.16.23.66", 22, "root", "Linsec@2018");
        String[] cmds = new String[]{"pwd", "cd /tmp", "pwd", "exit"};
        ChannelShell shell = JschUtil.openShell(session);
        // 开始连接
        shell.setPty(true);
        OutputStream out = null;
        InputStream in = null;
        final StringBuilder result = StrUtil.builder();
        out = shell.getOutputStream();
        in = shell.getInputStream();
        try {
            for (String cmd : cmds) {
                out.write(StrUtil.bytes(cmd, StandardCharsets.UTF_8));
                out.flush();
                while (in.available() > 0) {
                    result.append(IoUtil.read(in, StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            IoUtil.close(out);
            IoUtil.close(in);
            JschUtil.close(shell);
        }
        System.out.println(result);

//        // 态感
//        Session session = JschUtil.getSession("172.16.23.66", 22, "root", "linsec@2023");
//        String[] cmds = new String[]{"pwd", "cd /tmp", "pwd", "exit"};
//        // 防火墙
////        Session session = JschUtil.getSession("172.16.23.13", 22, "administrator", "administrator123");
////        String[] cmds = new String[]{"configure", "acl 11111 src-ip net 1.1.1.1 dst-ip net 2.2.2.2 deny"};
//
//        ChannelShell shell = JschUtil.openShell(session);
//        Set<String> cmdSet = Sets.newHashSet(cmds);
//        // 开始连接
//        shell.setPty(true);
//        OutputStream out = null;
//        InputStream in = null;
//        List<String> resultLineList = Lists.newArrayList();
//        //换行符
//        String nlStr = new String(new byte[]{10}, "UTF-8");
//        // 回车符
//        String crStr = new String(new byte[]{13}, "UTF-8");
//        TimeUnit.SECONDS.sleep(1);
//        try {
//            out = shell.getOutputStream();
//            in = shell.getInputStream();
//
//            for (String cmd : cmds) {
//                cmd = (nlStr + crStr) + cmd + (nlStr + crStr);
//                out.write(StrUtil.bytes(cmd, StandardCharsets.UTF_8));
//                out.flush();
//                TimeUnit.MILLISECONDS.sleep(500);
//            }
//            TimeUnit.SECONDS.sleep(1);
//            while (in.available() > 0) {
//                String result = IoUtil.read(in, StandardCharsets.UTF_8);
//                List<String> resultLines = Arrays.stream(result.split(nlStr)).flatMap(line -> Arrays.stream(line.split(crStr))).collect(Collectors.toList());
//                resultLineList.addAll(resultLines);
//            }
//        } catch (IOException e) {
//            throw new IORuntimeException(e);
//        } finally {
//            IoUtil.close(out);
//            IoUtil.close(in);
//            JschUtil.close(shell);
//            JschUtil.close(session);
//        }
//        // 过滤掉空行，和第一行命令之前的所有行
//        // 命令提示符
//        Set<String> cmdPromptSet = Sets.newHashSet();
//        Iterator<String> iterator = resultLineList.iterator();
//        boolean findCmdLine = false;
//        while (iterator.hasNext()) {
//            String line = iterator.next();
//            String hasCmd = hasCmd(line, cmdSet);
//            if (ObjectUtil.isNotEmpty(hasCmd)) {
//                findCmdLine = true;
//                // 获取命令提示符
//                String cmdPrompt = line.replace(hasCmd, "").trim();
//                cmdPromptSet.add(cmdPrompt);
//            } else if (!findCmdLine) {
//                iterator.remove();
//            }
//        }
//        // 再次遍历，去掉只有命令提示符的行或空行
//        Iterator<String> iteratorSecond = resultLineList.iterator();
//        List<String> activeLineList = Lists.newArrayList();
//        while (iteratorSecond.hasNext()) {
//            String line = iteratorSecond.next();
//            String newLine = line;
//            for (String cmdPrompt : cmdPromptSet) {
//                newLine = newLine.replace(cmdPrompt, "").trim();
//            }
//            if(ObjectUtil.isEmpty(newLine)){
//                iteratorSecond.remove();
//                continue;
//            }
//            activeLineList.add(line);
//        }
//        System.out.println("===== 有效行 ======");
//        activeLineList.forEach(System.out::println);
//
//        // 去掉带有命令提示符的行，只保留输出结果
//        List<String> outputLineList = activeLineList.stream().filter(line->{
//            for (String cmdPrompt : cmdPromptSet) {
//                if(line.contains(cmdPrompt)){
//                    return false;
//                }
//            }
//            return true;
//        }).collect(Collectors.toList());
//        System.out.println("===== 结果行 =====");
//        outputLineList.forEach(System.out::println);
    }

    private String hasCmd(String line, Set<String> cmdSet) {
        for (String cmd : cmdSet) {
            if (line.contains(cmd)) {
                return cmd;
            }
        }
        return "";
    }

    @Test
    public void testExe() {
        Session session = JschUtil.getSession("172.16.23.66", 22, "root", "linsec@2023");
//        ChannelShell shell = JschUtil.openShell(session);
        String result = JschUtil.execByShell(session, "pwd", StandardCharsets.UTF_8);
        System.out.println(result);
    }

}
