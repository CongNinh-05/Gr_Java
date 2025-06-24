package com.smokefree.controller;

import com.smokefree.dto.BlogDTO;
import com.smokefree.entity.Blog;
import com.smokefree.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/blogs")
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDTO dto) {
        Blog blog = blogService.createBlog(dto.getAuthorId(), dto.getTitle(), dto.getContent());
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }
}