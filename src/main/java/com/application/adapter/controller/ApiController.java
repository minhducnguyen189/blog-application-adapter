package com.application.adapter.controller;

import com.application.adapter.models.request.Post;
import com.application.adapter.models.response.PostResponse;
import com.application.adapter.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;


@Controller
public class ApiController {

    @Autowired
    private PostService<String, Post, PostResponse> postService;


    @RequestMapping(method = RequestMethod.GET, path = "blog/post/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("id") String id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "blog/post")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }
}
