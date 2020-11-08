package com.application.adapter.controller;

import com.application.adapter.models.request.Post;
import com.application.adapter.models.response.PostResponse;
import com.application.adapter.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@Controller
public class ApiController {

    @Autowired
    private PostService<String, Post, PostResponse, Integer, List<PostResponse>> postService;


    @RequestMapping(method = RequestMethod.GET, path = "blog/post/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("id") String id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "blog/post")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "blog/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable("id") String id, @RequestBody Post post) {
        postService.updatePostById(id, post);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "blog/post")
    public ResponseEntity<List<PostResponse>> getPosts(@PathParam("page") int page, @PathParam("sortKey") @Nullable String key) {
        return new ResponseEntity<>(postService.getPosts(page, key), HttpStatus.OK);
    }
}
