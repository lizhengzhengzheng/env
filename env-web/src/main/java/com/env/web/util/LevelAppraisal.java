package com.env.web.util;

import java.util.Collection;
import com.env.web.entity.YinziInfo;
import com.env.web.vo.MonitoringDataVo;
import com.env.web.vo.SiteInfoVo;


/**
 * 判断站点水质级别
 * @author lz
 *
 */
public class LevelAppraisal {
	
	public static String Level(SiteInfoVo siteInfo, Collection<YinziInfo> yinzi) {
		
		//级别
		int a = 0, level = 0;
		String lev = "";
		
		for(int i = 0; i<siteInfo.getData().size(); i++) {
			for (YinziInfo item : yinzi) {
				if(siteInfo.getData().get(i).getYinziId() == item.getId()) {
					a = LevelAppraisal.LevelInt(siteInfo.getData().get(i).getYinziValue(),item);
					if(a >= level)
						level = a;
				}
			}
		}
		
        if(level == 1)
			lev = "Ⅰ类";
		else if(level == 2)
			lev = "Ⅱ类";
		else if(level == 3)
			lev = "Ⅲ类";
		else if(level == 4)
			lev = "Ⅳ类";
		else if(level == 5)
			lev = "Ⅴ类";
		return lev;
	}
	
	public static int LevelInt(Double yinziValue,YinziInfo yinzi) {
		int lev = 5;
		if(yinziValue == null) {
			return lev;
		}else {
			if(yinziValue <= yinzi.getUpperLimit1() && yinziValue >= yinzi.getLowerLimit1())
				lev = 1;
			if(yinziValue <= yinzi.getUpperLimit2() && yinziValue >= yinzi.getLowerLimit2())
				lev = 2;
			if(yinziValue <= yinzi.getUpperLimit3() && yinziValue >= yinzi.getLowerLimit3())
				lev = 3;
			if(yinziValue <= yinzi.getUpperLimit4() && yinziValue >= yinzi.getLowerLimit4())
				lev = 4;
			if(yinziValue <= yinzi.getUpperLimit5() && yinziValue >= yinzi.getLowerLimit5())
				lev = 5;
		}
		return lev;
	}

	public static String Level(MonitoringDataVo data, YinziInfo yinzi) {
		String level = "-";
		if(data.getYinziValue() >= yinzi.getLowerLimit1() && data.getYinziValue() <= yinzi.getUpperLimit1()) {
			level = "Ⅰ类";
		}else if(data.getYinziValue() >= yinzi.getLowerLimit2() && data.getYinziValue() <= yinzi.getUpperLimit2()) {
			level = "Ⅱ类";
		}else if(data.getYinziValue() >= yinzi.getLowerLimit3() && data.getYinziValue() <= yinzi.getUpperLimit3()) {
			level = "Ⅲ类";
		}else if(data.getYinziValue() >= yinzi.getLowerLimit4() && data.getYinziValue() <= yinzi.getUpperLimit4()) {
			level = "Ⅳ类";
		}else if(data.getYinziValue() >= yinzi.getLowerLimit5() && data.getYinziValue() <= yinzi.getUpperLimit5()) {
			level = "Ⅴ类";
		}
		return level;
	}

}
