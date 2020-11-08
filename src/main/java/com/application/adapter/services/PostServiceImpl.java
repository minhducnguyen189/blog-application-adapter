package com.application.adapter.services;

import com.application.adapter.utilities.DateUtil;
import com.application.adapter.utilities.MapperUtil;
import com.application.adapter.models.env.PageEnv;
import com.application.adapter.models.entities.PostEntity;
import com.application.adapter.models.request.Post;
import com.application.adapter.models.response.PostResponse;
import com.application.adapter.repositories.PostPagingRepository;
import com.application.adapter.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService<String, Post, PostResponse, Integer, List<PostResponse>> {

    private ApplicationContext context;
    private PostRepository repository;
    private PostPagingRepository pagingRepository;

    @Autowired
    public PostServiceImpl(ApplicationContext context, PostRepository repository, PostPagingRepository pagingRepository) {
        this.context = context;
        this.repository = repository;
        this.pagingRepository = pagingRepository;
    }

    @Override
    public String createPost(Post post) {
        PostEntity entity = new PostEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedDate(DateUtil.getRecentDate());
        entity.setLastModifiedDate(DateUtil.getRecentDate());
        MapperUtil.convertObject(post, entity);
        repository.save(entity);
        return entity.getId();
    }

    @Override
    public PostResponse getPostById(String id) {
        PostEntity entity = repository.getOne(id);
        if(id.equalsIgnoreCase(entity.getId())) {
            PostResponse response = new PostResponse();
            MapperUtil.convertObject(entity, response);
            return response;
        }
        throw new NullPointerException("Not found, the id is not correct!");
    }

    @Override
    public void updatePostById(String id, Post post) {
        PostEntity entity = repository.getOne(id);
        entity.setLastModifiedDate(DateUtil.getRecentDate());
        MapperUtil.convertObject(post, entity);
        repository.save(entity);
    }

    @Override
    public List<PostResponse> getPosts(Integer startNumber, String sortKey) {
        PageEnv pageEnv = context.getBean(PageEnv.class);
        Pageable pageable;
        if(Optional.ofNullable(sortKey).isPresent()) {
             pageable = PageRequest.of(startNumber, pageEnv.getSize(), Sort.by(sortKey).descending());
        } else {
             pageable = PageRequest.of(startNumber, pageEnv.getSize(), Sort.by(pageEnv.getDefaultKey()).descending());
        }
        Page<PostEntity> page = pagingRepository.findAll(pageable);
        List<PostEntity> entities = page.getContent();
        return entities.stream().map(e -> MapperUtil.mappingObject(e, new PostResponse())).collect(Collectors.toList());
    }

}
