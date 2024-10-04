package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.model.Post;

public interface PostService {

    List<Post> findAllPost();
    Optional<Post> findById(String uuid);
    Post save(Post post);
    void delete(String id);
}
