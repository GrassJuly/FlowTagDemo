package com.lhx.flowtagdemo.bean;

/**
 * Created by LJZ
 * on 2019-11-19
 * Describe:
 */
public class TestBean {

    private String tagId;
    private String tagName;

    public TestBean(String tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
