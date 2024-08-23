package com.blogs.blogservice.service;

import com.blogs.blogservice.entity.PostBlog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostBlogService {
    public PostBlog createPost(PostBlog post);
    public PostBlog findById(Long id);
    List<PostBlog> findByCategory(String category);
    List<PostBlog> findAll();
    List<PostBlog> findBytimestampofcreation(LocalDateTime from, LocalDateTime to);
    List<PostBlog> findByCategoryAndTimestampofcreation(String category, LocalDateTime from,LocalDateTime to);
    Boolean deletePostBlog(Long id);
    Boolean updatePostBlog(Long id, PostBlog updatedBlog);
}
