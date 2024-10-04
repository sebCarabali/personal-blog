package com.example.services.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.example.model.Post;
import com.example.repositories.CrudRepository;
import com.example.services.PostService;

@RequestScoped
public class PostServiceImpl implements PostService {

    @Inject
    private CrudRepository<Post> crudRepository;

    @Override
    public List<Post> findAllPost() {
        return crudRepository.findAll();
    }

    @Override
    public Optional<Post> findById(String uuid) {
        return crudRepository.findOneBy(p -> p.getId().equals(uuid));
    }

    @Override
    public Post save(Post post) {
        return crudRepository.save(post);
    }
}
