package com.env.io.netty.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.env.io.common.DeviceCommon;
import com.env.io.common.DeviceRequest;
import com.env.io.common.MsgPushUtil;
import com.env.io.dao.AlarmInfoRepository;
import com.env.io.dao.DictionaryTypeRepository;
import com.env.io.dao.ProjectInfoRepository;
import com.env.io.dao.SiteInfoRepository;
import com.env.io.dao.YinziInfoRepository;
import com.env.io.entity.AlarmInfo;
import com.env.io.entity.DictionaryType;
import com.env.io.entity.SiteInfo;
import com.env.io.entity.YinziInfo;

import io.netty.channel.Channel;

/**
 * 设备报警
 * @author lizheng
 * @date 2020年3月28日
 *
 */
@Service("T003")
public class T003Service implements ServerService {
	
	@Resource
	private SiteInfoRepository siteInfoRepository;
	
	@Resource
	private YinziInfoRepository yinziInfoRepository;
	
	@Resource
	private AlarmInfoRepository alarmInfoRepository;
	
	@Resource
	private DictionaryTypeRepository dictionaryTypeRepository;
	
	@Resource
	private ProjectInfoRepository projectInfoRepository;

	@Override
	public void main(DeviceRequest msg, Channel channel) {
		// TODO Auto-generated method stub
		if(!DeviceCommon.isHeart(msg,channel)) {
			return;
		}else {
			SiteInfo siteInfo = siteInfoRepository.findByAccessCodeAndState(msg.getId(),0);
			int siteId = siteInfo.getId();
			int projectId = siteInfo.getProjectId();
			AlarmInfo alarmInfo = new AlarmInfo();
			alarmInfo.setSiteId(siteId);
			msg.getMap().forEach((key, value) -> {
				String info = "";
				if(key.equals("")) {
					//站点报警
					DictionaryType dictionaryType = dictionaryTypeRepository.findByTypeAndAlarmNumAndProjectIdAndState(2, (String)value, projectId, 0);
					if(dictionaryType == null) {
						DeviceCommon.responseErr(msg, channel, "The "+key+" does not exist");
						return;
					}
					alarmInfo.setAlarmType(2);
					alarmInfo.setAlarmId(dictionaryType.getId());
					info = siteInfo.getSiteName() + ":" + dictionaryType.getName();
				}else {
					//设备报警
					YinziInfo yinzi = yinziInfoRepository.findByYinziNumAndStateAndProjectId(key, 0, projectId);
					alarmInfo.setYinziId(yinzi.getId());
					DictionaryType dictionaryType = dictionaryTypeRepository.findByTypeAndAlarmNumAndProjectIdAndState(1, (String)value, projectId, 0);
					if(dictionaryType == null) {
						DeviceCommon.responseErr(msg, channel, "The \"+key+\" does not exist");
						return;
					}
				    alarmInfo.setAlarmType(1);
				    alarmInfo.setAlarmId(dictionaryType.getId());
				    info = siteInfo.getSiteName() + ":" + yinzi.getName() + ":" + dictionaryType.getName();
				}
				alarmInfoRepository.save(alarmInfo);
				String url = projectInfoRepository.findById(projectId).getPushUrl();
				MsgPushUtil.pushToDd(info, url);
			});
			DeviceCommon.responseOk(msg, channel);
		}
	}

}
