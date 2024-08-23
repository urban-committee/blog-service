package com.blogs.blogservice.cotroller;


import com.blogs.blogservice.entity.PostBlog;
import com.blogs.blogservice.exception.MessageResponse;
import com.blogs.blogservice.service.PostBlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/blogsite/blogs")
public class PostBlogController {

    @Autowired
    private PostBlogService postService;

    @PostMapping("/add")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostBlog post) {
        postService.createPost(post);
        return ResponseEntity.ok(new MessageResponse("Blog created successfully"));
    }

    @GetMapping("/{id}")
    public PostBlog getPostById(@PathVariable Long id) {
        return postService.findById(id);
    }
    @GetMapping("/getall")
    public List<PostBlog> findAll() {
        return postService.findAll();
    }
    @GetMapping("/info/{category}")
    public List<PostBlog> getByCategory(@PathVariable("category") String category) {
        return postService.findByCategory(category);
    }
    @GetMapping("/get/{startDuration}/{endDuration}")
    public List<PostBlog> getBytimestampofcreation(@PathVariable("startDuration") LocalDateTime startDuration,
                                                   @PathVariable("endDuration") LocalDateTime endDuration) {
        return postService.findBytimestampofcreation(startDuration,endDuration);
    }

    @GetMapping("/get/{category}/{startDuration}/{endDuration}")
    public List<PostBlog> getByCategoryAndTimestampofcreationBefore(@PathVariable("category") String category,
                                                                    @PathVariable("startDuration") LocalDateTime startDuration,
                                                                    @PathVariable("endDuration") LocalDateTime endDuration) {
        return postService.findByCategoryAndTimestampofcreation(category,startDuration,endDuration);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateBlog(@PathVariable Long id, @RequestBody PostBlog updatedBlog) {
       Boolean updated = postService.updatePostBlog(id, updatedBlog);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBlog(@PathVariable Long id) {
        Boolean updated = postService.deletePostBlog(id);
        return ResponseEntity.ok(updated);
    }

}
