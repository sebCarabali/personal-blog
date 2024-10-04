package com.example.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import com.example.constants.Constants;
import com.example.exeptions.BlogException;
import com.example.mappers.PostMapper;
import com.example.model.Post;
import com.example.util.FileUtils;

public class BlogFileRepository implements CrudRepository<Post> {

    @Inject
    private FileUtils fileUtils;
    @Inject
    private PostMapper postMapper;

    @Override
    public Post save(Post entity) {
        // generate and set id
        if (entity.getId() == null || entity.getId().isBlank()) {
            entity.setId(UUID.randomUUID().toString());
        }
        // generate filename
        String fileName = entity.getId() + "_" + entity.getPubTime().toInstant(ZoneOffset.UTC).toEpochMilli()
                + Constants.ARTICLES_FILE_EXTENSION;
        String filePath = Constants.ARTICLES_DIR_PATH + fileName;
        // map post to json
        String jsonPost = postMapper.mapToJson(entity);
        // save post int the generated file
        fileUtils.writeToFile(filePath, jsonPost);
        return entity;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try {
            Files.list(Paths.get(Constants.ARTICLES_DIR_PATH)).filter(Files::isRegularFile).forEach(file -> {
                posts.add(readPostFile(file));
            });
        } catch (IOException e) {
            throw new BlogException(e.getMessage(), e);
        }
        return posts;
    }

    private Post readPostFile(Path file) {
        try {
            return postMapper.mapToEntity(Files.readString(file));
        } catch (IOException e) {
            throw new BlogException(e.getMessage(), e);
        }
    }

    @Override
    public List<Post> findBy(Matcher<Post> matcher) {
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public Optional<Post> findOneBy(Matcher<Post> matcher) {
        return findAll().stream().filter(p -> matcher.matches(p)).findFirst();
    }

    @Override
    public Post update(Post entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Matcher<Post> matcher) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
