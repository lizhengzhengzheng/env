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
@TableName("yinzi_info")
public class YinziInfo extends Model<YinziInfo> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 名称
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 因子编号
     */
    private String yinziNum;

    /**
     * 1类上限
     */
    private Double upperLimit1;

    /**
     * 1类下线
     */
    private Double lowerLimit1;

    /**
     * 2类上限
     */
    private Double upperLimit2;

    /**
     * 2类下线
     */
    private Double lowerLimit2;

    /**
     * 3类上限
     */
    private Double upperLimit3;

    /**
     * 3类下线
     */
    private Double lowerLimit3;

    /**
     * 4类上限
     */
    private Double upperLimit4;

    /**
     * 4类下线
     */
    private Double lowerLimit4;

    /**
     * 5类上限
     */
    private Double upperLimit5;

    /**
     * 5类下线
     */
    private Double lowerLimit5;

    /**
     * 状态【0：启用】【1：删除】
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    private Integer createUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getYinziNum() {
        return yinziNum;
    }

    public void setYinziNum(String yinziNum) {
        this.yinziNum = yinziNum;
    }

    public Double getUpperLimit1() {
        return upperLimit1;
    }

    public void setUpperLimit1(Double upperLimit1) {
        this.upperLimit1 = upperLimit1;
    }

    public Double getLowerLimit1() {
        return lowerLimit1;
    }

    public void setLowerLimit1(Double lowerLimit1) {
        this.lowerLimit1 = lowerLimit1;
    }

    public Double getUpperLimit2() {
        return upperLimit2;
    }

    public void setUpperLimit2(Double upperLimit2) {
        this.upperLimit2 = upperLimit2;
    }

    public Double getLowerLimit2() {
        return lowerLimit2;
    }

    public void setLowerLimit2(Double lowerLimit2) {
        this.lowerLimit2 = lowerLimit2;
    }

    public Double getUpperLimit3() {
        return upperLimit3;
    }

    public void setUpperLimit3(Double upperLimit3) {
        this.upperLimit3 = upperLimit3;
    }

    public Double getLowerLimit3() {
        return lowerLimit3;
    }

    public void setLowerLimit3(Double lowerLimit3) {
        this.lowerLimit3 = lowerLimit3;
    }

    public Double getUpperLimit4() {
        return upperLimit4;
    }

    public void setUpperLimit4(Double upperLimit4) {
        this.upperLimit4 = upperLimit4;
    }

    public Double getLowerLimit4() {
        return lowerLimit4;
    }

    public void setLowerLimit4(Double lowerLimit4) {
        this.lowerLimit4 = lowerLimit4;
    }

    public Double getUpperLimit5() {
        return upperLimit5;
    }

    public void setUpperLimit5(Double upperLimit5) {
        this.upperLimit5 = upperLimit5;
    }

    public Double getLowerLimit5() {
        return lowerLimit5;
    }

    public void setLowerLimit5(Double lowerLimit5) {
        this.lowerLimit5 = lowerLimit5;
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
        return "YinziInfo{" +
        "id=" + id +
        ", projectId=" + projectId +
        ", name=" + name +
        ", unit=" + unit +
        ", yinziNum=" + yinziNum +
        ", upperLimit1=" + upperLimit1 +
        ", lowerLimit1=" + lowerLimit1 +
        ", upperLimit2=" + upperLimit2 +
        ", lowerLimit2=" + lowerLimit2 +
        ", upperLimit3=" + upperLimit3 +
        ", lowerLimit3=" + lowerLimit3 +
        ", upperLimit4=" + upperLimit4 +
        ", lowerLimit4=" + lowerLimit4 +
        ", upperLimit5=" + upperLimit5 +
        ", lowerLimit5=" + lowerLimit5 +
        ", state=" + state +
        ", createAt=" + createAt +
        ", createUser=" + createUser +
        "}";
    }
}
