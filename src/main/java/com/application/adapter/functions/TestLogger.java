package com.application.adapter.functions;

import com.application.adapter.models.request.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component("TestLogger")
public class TestLogger implements BiConsumer<String, Post> {


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void accept(String s, Post post) {
        System.out.println(s);
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
