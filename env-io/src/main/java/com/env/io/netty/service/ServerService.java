package com.env.io.netty.service;

import com.env.io.common.DeviceRequest;

import io.netty.channel.Channel;

public interface ServerService {

	void main(DeviceRequest msg, Channel channel);
}
