package com.smokefree.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
    private int authorId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    // Constructors, getters, setters
    public Blog() {}
    public Blog(int authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public int getBlogId() { return blogId; }
    public void setBlogId(int blogId) { this.blogId = blogId; }
    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}