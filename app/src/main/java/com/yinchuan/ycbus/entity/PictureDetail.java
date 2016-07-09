package com.yinchuan.ycbus.entity;

/**
 * Created by zhouwei on 2016/7/9.
 */
public class PictureDetail {
    private String imageid;
    private String group_id;
    private String pic_url;
    private String pic_pageurl;
    private String pic_title;
    private String pic_desc;
    private String qhimg_url;
    private String qhimg_thumb_url;
    private String downurl;

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getPic_desc() {
        return pic_desc;
    }

    public void setPic_desc(String pic_desc) {
        this.pic_desc = pic_desc;
    }

    public String getPic_pageurl() {
        return pic_pageurl;
    }

    public void setPic_pageurl(String pic_pageurl) {
        this.pic_pageurl = pic_pageurl;
    }

    public String getPic_title() {
        return pic_title;
    }

    public void setPic_title(String pic_title) {
        this.pic_title = pic_title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getQhimg_thumb_url() {
        return qhimg_thumb_url;
    }

    public void setQhimg_thumb_url(String qhimg_thumb_url) {
        this.qhimg_thumb_url = qhimg_thumb_url;
    }

    public String getQhimg_url() {
        return qhimg_url;
    }

    public void setQhimg_url(String qhimg_url) {
        this.qhimg_url = qhimg_url;
    }
}
