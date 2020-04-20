package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Device1 {
private static final ThreadLocal<Socket> threadConnect = new ThreadLocal<Socket>(); 
    
    private static final String HOST = "192.168.0.107";

    private static final int PORT = 8087;
    
    private static Socket client;
    
    private static OutputStream outStr = null;
    
    private static InputStream inStr = null;
    
    private static Thread tRecv = new Thread(new RecvThread());
    private static Thread tKeep = new Thread(new KeepThread());
    private static Thread tAlarm = new Thread(new AlarmThread());
    private static Thread tData = new Thread(new DataThread());

    public static void connect() throws UnknownHostException, IOException  {
        client = threadConnect.get();
        if(client == null){
            client = new Socket(HOST, PORT);
            threadConnect.set(client);
            tKeep.start();
            System.out.println("========设备1连接成功！========");
        }
        outStr = client.getOutputStream();
        inStr = client.getInputStream();
    }
    
    public static void disconnect() {
        try {
            outStr.close();
            inStr.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static class KeepThread implements Runnable {
        public void run() {
            try {
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("设备1发送心跳数据包");
                    outStr.write("{\"type\":\"T001\",\"state\":\"3\",\"id\":\"AGS-1501\",\"map\":{\"state\":\"water\"}}$$".getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    private static class RecvThread implements Runnable {
        public void run() {
            try {
                while (true) {
                    byte[] b = new byte[1024];
                    int r = inStr.read(b);
                    if(r>-1){
                        String str = new String(b);
                        System.out.println("设备1收到数据包");
                        System.out.println( str );
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    public static void main(String[] args) {
        try {
        	Device1.connect();
            tRecv.start();
            tAlarm.start();
            tData.start();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
