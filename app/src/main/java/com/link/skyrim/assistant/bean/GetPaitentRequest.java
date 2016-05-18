package com.link.skyrim.assistant.bean;

import java.io.Serializable;

/**
 * Created by inven on 2016/3/29.
 */
public class GetPaitentRequest implements Serializable {


    private PageInfo pageInfo = new PageInfo();

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
