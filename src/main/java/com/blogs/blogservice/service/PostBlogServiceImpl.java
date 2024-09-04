package com.blogs.blogservice.service;

import com.blogs.blogservice.entity.PostBlog;
import com.blogs.blogservice.entity.UserDto;
import com.blogs.blogservice.exception.UserNotFoundException;
import com.blogs.blogservice.repository.PostBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostBlogServiceImpl implements PostBlogService {

    @Autowired
    private PostBlogRepository postRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:8082/api/v1.0/blogsite/user/";

    @Override
    public PostBlog createPost(PostBlog post) {
       try{
           UserDto user = restTemplate.getForObject(USER_SERVICE_URL + post.getUserId(), UserDto.class);
           if (user != null) {
               return postRepository.save(post);
           }
       } catch (Exception e) {
           throw new UserNotFoundException("User with ID " + post.getUserId() + " not found.");

       }
       return null;
    }

    @Override
    public PostBlog findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<PostBlog> findByCategory(String category) {
        return postRepository.findByCategory(category);
    }


    @Override
    public List<PostBlog> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<PostBlog> findBytimestampofcreation(LocalDateTime from, LocalDateTime to) {
        return postRepository.findByTimestampofcreationBetween(from, to);
    }

    @Override
    public List<PostBlog> findByCategoryAndTimestampofcreation(String category, LocalDateTime from,LocalDateTime to) {
        return postRepository.findByCategoryAndTimestampofcreationBetween(category, from,to);
    }



    @Override
    public Boolean updatePostBlog(Long id, PostBlog updatedBlog) {
        if (postRepository.existsById(id)) {
            updatedBlog.setId(id);
             postRepository.save(updatedBlog);
            return true;
        } else {
            throw new UserNotFoundException("Blog not found with id " + id);

        }
    }

    @Override
    public Boolean deletePostBlog(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Blog not found with id " + id);
        }
        //return postRepository.existsById(id);
        return true;
    }


}
