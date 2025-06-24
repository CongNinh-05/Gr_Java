package com.smokefree.repository;

import com.smokefree.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Query("SELECT b FROM Blog b WHERE b.authorId = :authorId ORDER BY b.createdAt DESC")
    List<Blog> findByAuthorId(@Param("authorId") int authorId);
}