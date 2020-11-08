package com.application.adapter.controller;

import com.application.adapter.models.request.Post;
import com.application.adapter.models.response.PostResponse;
import com.application.adapter.services.PostService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ApiControllerTest {

    @InjectMocks
    private ApiController apiController;

    @Mock
    private PostService<String, Post, PostResponse, Integer, List<PostResponse>> postService;

    private static final String MOCK_ID = "aa3615e5-29b5-4626-9079-af7eefd0e800";
    private static final String MOCK_KEY = "lastModifiedDate";
    private static final int MOCK_PAGE = 0;


    private PostResponse postResponse;
    private Post post;
    private List<PostResponse> postResponses;

    @Before
    public void initMock() {
        postResponses = new ArrayList<>();
        postResponse = new PostResponse();
        postResponses.add(postResponse);
        post = new Post();

    }


    @Test
    public void test_getPostById() {
        Mockito.when(postService.getPostById(Mockito.anyString())).thenReturn(postResponse);
        Assert.assertEquals(HttpStatus.OK, apiController.getPostById(MOCK_ID).getStatusCode());
    }

    @Test
    public void test_createPost() {
        Mockito.when(postService.createPost(Mockito.any())).thenReturn(MOCK_ID);
        Assert.assertEquals(HttpStatus.CREATED, apiController.createPost(post).getStatusCode());
    }

    @Test
    public void test_updatePost() {
        Mockito.doNothing().when(postService).updatePostById(Mockito.any(), Mockito.any());
        Assert.assertEquals(HttpStatus.OK, apiController.updatePost(MOCK_ID, post).getStatusCode());
    }

    @Test
    public void test_getPosts() {
        Mockito.when(postService.getPosts(Mockito.any(), Mockito.any())).thenReturn(postResponses);
        Assert.assertEquals(HttpStatus.OK, apiController.getPosts(MOCK_PAGE, MOCK_KEY).getStatusCode());
    }

}
