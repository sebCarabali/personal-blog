package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Post implements Serializable {

    private String id;
    private String title;
    private String content;
    private LocalDateTime pubTime;

    public Post() {
    }

    public Post(String id, String title, String content, LocalDateTime pubTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.pubTime = pubTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPubTime() {
        return pubTime;
    }

    public void setPubTime(LocalDateTime pubTime) {
        this.pubTime = pubTime;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", pubTime=" + pubTime + "]";
    }
}
