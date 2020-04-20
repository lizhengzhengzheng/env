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
@TableName("site_yinzi")
public class SiteYinzi extends Model<SiteYinzi> {

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
     * 最大值
     */
    private Double maxValue;

    /**
     * 最小值
     */
    private Double minValue;

    /**
     * 分析方法
     */
    private String analysisMethod;

    /**
     * 状态【0：启用】【1：删除】
     */
    private Integer state;

    /**
     * 添加时间
     */
    private LocalDateTime createAt;

    /**
     * 添加人id
     */
    private Integer createUser;


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

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public String getAnalysisMethod() {
        return analysisMethod;
    }

    public void setAnalysisMethod(String analysisMethod) {
        this.analysisMethod = analysisMethod;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SiteYinzi{" +
        "id=" + id +
        ", siteId=" + siteId +
        ", yinziId=" + yinziId +
        ", maxValue=" + maxValue +
        ", minValue=" + minValue +
        ", analysisMethod=" + analysisMethod +
        ", state=" + state +
        ", createAt=" + createAt +
        ", createUser=" + createUser +
        "}";
    }
}
