package com.env.io.common;

import com.alibaba.fastjson.JSON;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MsgPushUtil {
	
	public static String pushToDd(String msg, String url) {
		// 请求的JSON数据，这里我用map在工具类里转成json格式
        Map<String,Object> json=new HashMap<>();
        Map<String,Object> text=new HashMap<>();
        json.put("msgtype","text");
        text.put("content",msg);
        json.put("text",text);
        // 发送post请求
        return sendPostByMap(url, json);
	}
		
	/**
	 * 发送POST请求，参数是Map, contentType=x-www-form-urlencoded
	 * @author lizheng
	 * @param @param url
	 * @param @param mapParam
	 * @param @return
	 * @return String 返回类型
	 * @throws
	 */
	public static String sendPostByMap(String url, Map<String, Object> mapParam) {
		Map<String, String> headParam = new HashMap<>();
        headParam.put("Content-type", "application/json;charset=UTF-8");
        return sendPost(url, mapParam, headParam);
	}
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * @author lizheng
	 * @param @param url
	 * @param @param param
	 * @param @param headParam
	 * @param @return
	 * @return String 返回类型
	 * @throws
	 */
	public static String sendPost(String url, Map<String, Object> param, Map<String, String> headParam) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性 请求头
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Fiddler");

            if (headParam != null) {
                for (Entry<String, String> entry : headParam.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(JSON.toJSONString(param));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
