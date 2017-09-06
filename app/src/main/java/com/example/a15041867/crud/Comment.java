package com.example.a15041867.crud;

/**
 * Created by 15041867 on 6/9/2017.
 */

public class Comment {
    private int id;
    private String comment_desc;
    private int post_id;

    public Comment(int id, String comment_desc, int post_id) {
        this.id = id;
        this.comment_desc = comment_desc;
        this.post_id = post_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_desc() {
        return comment_desc;
    }

    public void setComment_desc(String comment_desc) {
        this.comment_desc = comment_desc;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return comment_desc;
    }
}
