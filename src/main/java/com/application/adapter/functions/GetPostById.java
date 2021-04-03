package com.application.adapter.functions;

import com.application.adapter.models.entities.PostEntity;
import com.application.adapter.models.response.PostResponse;
import com.application.adapter.repositories.PostRepository;
import com.application.adapter.utilities.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GetPostById implements Function<String, PostResponse> {

    @Autowired
    private PostRepository repository;


    @Override
    public PostResponse apply(String id) {
        PostEntity entity = repository.getOne(id);
        if(id.equalsIgnoreCase(entity.getId())) {
            PostResponse response = new PostResponse();
            MapperUtil.convertObject(entity, response);
            return response;
        }
        throw new NullPointerException("Not found, the id is not correct!");
    }
}
