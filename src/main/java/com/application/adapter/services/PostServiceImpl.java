package com.application.adapter.services;

import com.application.adapter.Utilities.DateUtil;
import com.application.adapter.Utilities.MapperUtil;
import com.application.adapter.models.entities.PostEntity;
import com.application.adapter.models.request.Post;
import com.application.adapter.models.response.PostResponse;
import com.application.adapter.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostServiceImpl implements PostService<String, Post, PostResponse> {


    @Autowired
    private PostRepository repository;

    @Override
    public String createPost(Post post) {
        PostEntity entity = new PostEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedDate(DateUtil.getRecentDate());
        entity.setLastModifiedDate(DateUtil.getRecentDate());
        MapperUtil.post2Entity(entity, post);
        repository.save(entity);
        return entity.getId();
    }

    @Override
    public PostResponse getPostById(String id) {
        PostEntity entity = repository.getOne(id);
        if(id.equalsIgnoreCase(entity.getId())) {
            PostResponse response = new PostResponse();
            MapperUtil.entity2PostResponse(entity, response);
            return response;
        }
        throw new NullPointerException("Not found, the id is not correct!");
    }

}
