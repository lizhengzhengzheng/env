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
@TableName("monitoring_data")
public class MonitoringData extends Model<MonitoringData> {

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
     * 数据值
     */
    private Double yinziValue;

    /**
     * 数据上传流水号
     */
    private String serialNum;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 状态【0：正常】【1：删除】
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

    public Double getYinziValue() {
        return yinziValue;
    }

    public void setYinziValue(Double yinziValue) {
        this.yinziValue = yinziValue;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MonitoringData{" +
        "id=" + id +
        ", siteId=" + siteId +
        ", yinziId=" + yinziId +
        ", yinziValue=" + yinziValue +
        ", serialNum=" + serialNum +
        ", createAt=" + createAt +
        ", state=" + state +
        "}";
    }
}
