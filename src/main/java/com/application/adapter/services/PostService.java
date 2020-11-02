package com.application.adapter.services;

public interface PostService<T, P, R> {
    T createPost(P post);
    R getPostById(T id);
}
