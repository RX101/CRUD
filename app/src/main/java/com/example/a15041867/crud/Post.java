package com.example.a15041867.crud;

import java.io.Serializable;

/**
 * Created by 15041867 on 6/9/2017.
 */

public class Post implements Serializable {
    private int id;
    private String title;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return title;
    }

    public Post(int id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }
}
