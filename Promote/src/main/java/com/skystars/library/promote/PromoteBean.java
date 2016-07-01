package com.skystars.library.promote;

/**
 * 文 件 名: PromoteBean
 * 创 建 人: TOO15
 * 创建日期: 2016/7/1 13:34
 * 修改时间：
 * 修改备注：
 */
public class PromoteBean {
    private String title;
    private String desc;
    private String icon;
    private String url;

    public PromoteBean(String title, String desc, String icon, String url) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String value) {
        this.desc = value;
    }

    public String getIcon() { return icon; }

    public void setIcon(String value) {
        this.icon = value;
    }

    public String getUrl() { return url; }

    public void setUrl(String value) {
        this.url = value;
    }
}