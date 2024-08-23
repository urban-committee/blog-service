package com.blogs.blogservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Post_Blog_Details")
@Builder
public class PostBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String blogname;
    private String category;
    @Column(columnDefinition = "text")
    private String article;
    private String authorname;
    @Column(name = "timestampofcreation")
    private LocalDateTime timestampofcreation;
}
