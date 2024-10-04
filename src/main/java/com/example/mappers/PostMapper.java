package com.example.mappers;

import java.util.logging.Logger;

import com.example.model.Post;
import com.example.util.DateUtils;

public class PostMapper implements Mapper<Post> {

    private static final Logger logger = Logger.getLogger(PostMapper.class.getCanonicalName());

    @Override
    public String mapToJson(Post entity) {
        return String.format("{\"id\":\"%s\",\"title\":\"%s\",\"content\":\"%s\",\"pubTime\":\"%s\"}",
                entity.getId(), entity.getTitle(), entity.getContent().replaceAll("\n", "<br/>"), DateUtils.format(entity.getPubTime()));
    }

    @Override
    public Post mapToEntity(String jsonContent) {
        jsonContent = jsonContent.trim().substring(1, jsonContent.length() - 1);

        // Split the JSON string by commas to get key-value pairs
        String[] pairs = jsonContent.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        String id = null, title = null, content = null, pubTimeString = null;

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim().replace("\"", "");
            switch (key) {
                case "id":
                    id = value;
                    break;
                case "title":
                    title = value;
                    break;
                case "content":
                    content = value;
                    break;
                case "pubTime":
                    pubTimeString = value;
                    break;
            }

        }
        return new Post(id, title, content, DateUtils.from(pubTimeString));
    }
}
