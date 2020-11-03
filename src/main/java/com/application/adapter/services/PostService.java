package com.application.adapter.services;

public interface PostService<T, P, R, N, L> {
    T createPost(P post);
    R getPostById(T id);
    void updatePostById(T id, P post);
    L getPosts(N startNumber, T key);
}
