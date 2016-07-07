package com.yinchuan.ycbus.entity;

/**
 * Created by Administrator on 2016/6/30.
 */
public class Adv {
    private String pic_url;
    private String linked_url;

    public Adv(String pic_url,String linked_url) {
        this.linked_url = linked_url;
        this.pic_url = pic_url;
    }

    public String getLinked_url() {
        return linked_url;
    }

    public void setLinked_url(String linked_url) {
        this.linked_url = linked_url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
