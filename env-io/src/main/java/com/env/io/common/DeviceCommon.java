package com.env.io.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

public class DeviceCommon {
	
	//----------------------------------------公共map--------------------------------------------------------------
	/**
	 * map<设备id,channel>
	 */
	public static final Map<String, Channel> deviceChannelMap = new HashMap<String, Channel>();
	
	//----------------------------------------设备交互状态码--------------------------------------------------------------
	/**
	 * 响应--正常
	 */
	public static final int OK = 1;
	
	/**
	 * 响应--错误
	 */
	public static final int ERR = 2;
	
	/**
	 * 主动发送
	 */
	public static final int SEND = 3;
	
	/**
	 * 代表设备状态
	 */
	public static final String state = "state";
	
	//----------------------------------------设备交互类型--------------------------------------------------------------
	/**
	 * 心跳
	 */
	public static final String XT = "T001";
	/**
	 * 监测数据
	 */
	public static final String JCSJ = "T002";
	/**
	 * 报警
	 */
	public static final String BJ = "T003";
	/**
	 * 开机
	 */
	public static final String KJ = "T004";
	/**
	 * 关机
	 */
	public static final String GJ = "T005";
	/**
	 * 重启
	 */
	public static final String CQ = "T006";
	/**
	 * 远程留样
	 */
	public static final String YCLY = "T007";
	/**
	 * 设置上传频率
	 */
	public static final String SCPL = "T008";
	/**
	 * 设备时间更新
	 */
	public static final String SJGX = "T009";
	/**
	 * 单次测量
	 */
	public static final String DCCL = "T010";
	/**
	 * 设备清洗
	 */
	public static final String SBQX = "T011";
	
	
	//----------------------------------------公共方法--------------------------------------------------------------
	/**
	 * 根据channel获取device
	 * @param map
	 * @param value
	 * @return
	 */
	public static String getDevice(Channel value){  
        String key="";  
        for (Entry<String, Channel> entry : deviceChannelMap.entrySet()) {  
            if(value.equals(entry.getValue())){  
                key=entry.getKey(); 
                return key; 
            }  
        }  
        return null;  
    } 
	
	/**
	 * 检测该设备是否有心跳
	 * @author lizheng
	 * @param @param msg
	 * @param @param channel
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isHeart(DeviceRequest msg, Channel channel) {
		Channel c = DeviceCommon.deviceChannelMap.get(msg.getId());
		if(c == null || !c.equals(channel)) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setId(msg.getId());
			serverResponse.setType(DeviceCommon.JCSJ);
			serverResponse.setStatus(DeviceCommon.ERR);
			serverResponse.setTime(sf.format(new Date()));
			serverResponse.setMsg("No heartbeat was detected");
			channel.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(serverResponse), CharsetUtil.UTF_8));
			return false;
		}
		return true;
	}
	
	/**
	 * 向客户端相应成功
	 * @author lizheng
	 * @param @param msg
	 * @param @param channel
	 * @return void 返回类型
	 * @throws
	 */
	public static void responseOk(DeviceRequest msg, Channel channel) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setId(msg.getId());
		serverResponse.setType(msg.getType());
		serverResponse.setStatus(DeviceCommon.OK);
		serverResponse.setTime(sf.format(new Date()));
		channel.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(serverResponse), CharsetUtil.UTF_8));
	}
	
	/**
	 * 向设备发送消息
	 * @author lizheng
	 * @param @param msg
	 * @param @param channel
	 * @return void 返回类型
	 * @throws
	 */
	public static void send(JSONObject msg) {
		Channel channel = DeviceCommon.deviceChannelMap.get(msg.get("id"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setId((String)msg.get("id"));
		serverResponse.setType((String)msg.get("type"));
		serverResponse.setStatus(DeviceCommon.SEND);
		serverResponse.setTime(sf.format(new Date()));
		serverResponse.setValue((String)msg.get("value"));
		serverResponse.setCilentId((String)msg.get("cilentId"));
		channel.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(serverResponse), CharsetUtil.UTF_8));
	}
	
	/**
	 * 向客户端返回错误信息
	 * @author lizheng
	 * @param @param msg
	 * @param @param channel
	 * @param @param info
	 * @return void 返回类型
	 * @throws
	 */
	public static void responseErr(DeviceRequest msg, Channel channel, String info) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setId(msg.getId());
		serverResponse.setType(DeviceCommon.JCSJ);
		serverResponse.setStatus(DeviceCommon.OK);
		serverResponse.setTime(sf.format(new Date()));
		serverResponse.setMsg(info);
		channel.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(serverResponse), CharsetUtil.UTF_8));
	}
}
