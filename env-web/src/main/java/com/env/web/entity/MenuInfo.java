package com.env.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 后台菜单表
 * </p>
 *
 * @author ${author}
 * @since 2020-04-06
 */
@TableName("menu_info")
public class MenuInfo extends Model<MenuInfo> {

    private static final long serialVersionUID=1L;

    /**
     * 表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 点击菜单点击的链接
     */
    private String link;

    /**
     * 菜单排序
     */
    private Integer sort;

    /**
     * 菜单样式
     */
    private String cssStyle;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 【0：启用】【1：停用】
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
        "id=" + id +
        ", menuName=" + menuName +
        ", link=" + link +
        ", sort=" + sort +
        ", cssStyle=" + cssStyle +
        ", parentId=" + parentId +
        ", state=" + state +
        ", createAt=" + createAt +
        "}";
    }
}
