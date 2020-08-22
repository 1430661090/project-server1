package com.mycode.javaIo;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TestIo {

    //复制文本到文件---字符流
    @Test
    //字符缓冲流一次读写一个字符串
    public void CopyFileDemo1() throws IOException {
        String srcString = "d:\\a.txt";
        String destString = "e:\\b.txt";
        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
        String line = null;
        while ((line = br.readLine()) != null){
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }

    @Test
    //字符缓冲流一次读写一个字符数组
    public void CopyFileDemo2() throws IOException {
        String srcString = "d:\\a.txt";
        String destString = "e:\\b.txt";
        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));

        //定义一个char类型的数组
        char[] chs = new char[1024];
        int len = 0;
        while ((len = br.read(chs)) != -1){
            bw.write(chs, 0, len);
        }

        bw.close();
        br.close();

    }
}
