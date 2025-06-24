package com.smokefree.service;

import com.smokefree.entity.Blog;
import com.smokefree.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(int authorId, String title, String content) {
        Blog blog = new Blog(authorId, title, content);
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}