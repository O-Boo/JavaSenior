package com.atguigu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * 实现TCP网络编程
 * 例子1：客户端发送信息给服务器，服务器将数据显示在控制台上
 *
 * @author Tonny
 * @version 2021.2
 * @date 2022/6/11 15:20
 */
public class TCPTest1 {

    //客户端
    @Test
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建Socket对象,指明服务器端的IP和端口号
            InetAddress inet=InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);
            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("我是客户端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            try {
                if (os!=null)
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket!=null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
    //服务端
    @Test
    public void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos= null;
        try {
            //1.创建服务器端的ServerSocket，指明端口号
            ss = new ServerSocket(8899);
            //2.调用accept()表示接受来自客户端的socket
            socket = ss.accept();
            //3.获取输入流
            is = socket.getInputStream();

            //不建议这样写，可能会乱码。
//        byte[] buffer=new byte[1024];
//        int len;
//        while ((len=is.read(buffer))!=-1){
//            String str=new String(buffer,0,len);
//            System.out.println(str);
//        }
            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int len;
            while ((len=is.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            System.out.println(baos.toString());
            System.out.println("收到了来自于"+socket.getInetAddress().getHostAddress()+"的数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            try {
                if (ss!=null)
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket!=null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is!=null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (baos!=null)
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
