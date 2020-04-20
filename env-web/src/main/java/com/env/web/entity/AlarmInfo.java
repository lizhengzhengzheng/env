package com.env.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
@TableName("alarm_info")
public class AlarmInfo extends Model<AlarmInfo> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 站点id
     */
    private Integer siteId;

    /**
     * 因子id
     */
    private Integer yinziId;

    /**
     * 报警类型id
     */
    private Integer alarmId;

    /**
     * 报警类型1设备报警2站点报警
     */
    private Integer alarmType;

    /**
     * 报警时间
     */
    private LocalDateTime addTime;
    
    /**
     * 状态0未处理 1删除 2已处理
     */
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getYinziId() {
        return yinziId;
    }

    public void setYinziId(Integer yinziId) {
        this.yinziId = yinziId;
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
    
    

    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AlarmInfo{" +
        "id=" + id +
        ", siteId=" + siteId +
        ", yinziId=" + yinziId +
        ", alarmId=" + alarmId +
        ", alarmType=" + alarmType +
        ", addTime=" + addTime +
        "}";
    }
}
