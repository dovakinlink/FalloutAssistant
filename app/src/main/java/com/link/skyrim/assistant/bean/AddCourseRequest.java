package com.link.skyrim.assistant.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by inven on 2016/4/7.
 */
public class AddCourseRequest implements Serializable {

    private Integer record_id;

    private String name;

    private Date date;

    private String content;

    private List<AddCourseResBean> picture = new ArrayList<AddCourseResBean>();

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AddCourseResBean> getPicture() {
        return picture;
    }

    public void setPicture(List<AddCourseResBean> picture) {
        this.picture = picture;
    }

}
