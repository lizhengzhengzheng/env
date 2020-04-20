package com.env.io.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.env.io.common.SpringUtil;
import com.env.io.dao.MonitoringDataRepository;
import com.env.io.entity.MonitoringData;

@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiver {
	
	private MonitoringDataRepository monitoringDataRepository = SpringUtil.getBean(MonitoringDataRepository.class);
	
	@RabbitHandler
    public void process(MonitoringData monitoringData) {
		monitoringDataRepository.save(monitoringData);
    }
	
}
