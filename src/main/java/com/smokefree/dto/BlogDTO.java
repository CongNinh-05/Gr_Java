package com.smokefree.dto;

public class BlogDTO {
    private int authorId;
    private String title;
    private String content;

    // Constructors, getters, setters
    public BlogDTO() {}
    public BlogDTO(int authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    // Getters and setters
    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}