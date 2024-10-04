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
import java.util.logging.Logger;

import javax.inject.Inject;

import com.example.constants.Constants;
import com.example.exeptions.BlogException;
import com.example.mappers.PostMapper;
import com.example.model.Post;
import com.example.util.FileUtils;
import com.example.util.StringUtils;

public class BlogFileRepository implements CrudRepository<Post> {

    @Inject
    private FileUtils fileUtils;
    @Inject
    private PostMapper postMapper;
    private static final Logger logger = Logger.getLogger(BlogFileRepository.class.getCanonicalName());

    @Override
    public Post save(Post entity) {
        // generate and set id
        if (entity.getId() == null || entity.getId().isBlank()) {
            entity.setId(UUID.randomUUID().toString());
        }
        // generate filename
        String fileName = entity.getId()
                + Constants.ARTICLES_FILE_EXTENSION;
        String filePath = Constants.ARTICLES_DIR_PATH + fileName;
        // map post to json
        entity.setContent(entity.getContent().replaceAll("\\r\\n|\\r|\\n", " "));
        String jsonPost = postMapper.mapToJson(entity);
        // save post int the generated file
        fileUtils.writeToFile(filePath, StringUtils.escapeCommasInQuotes(jsonPost));
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
    public boolean delete(String id) {
        String fileName = id
                + Constants.ARTICLES_FILE_EXTENSION;
        String filePath = Constants.ARTICLES_DIR_PATH + fileName;
        try {
            return Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            throw new BlogException(e.getMessage(), e);
        }
    }
}
