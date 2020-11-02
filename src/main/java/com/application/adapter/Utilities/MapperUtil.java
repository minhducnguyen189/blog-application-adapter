package com.application.adapter.Utilities;

import com.application.adapter.models.entities.PostEntity;
import com.application.adapter.models.request.Post;
import com.application.adapter.models.response.PostResponse;
import org.springframework.beans.BeanUtils;

public class MapperUtil {

    public static void post2Entity(PostEntity entity,Post post) {
        BeanUtils.copyProperties(post, entity);
    }

    public static void entity2PostResponse(PostEntity entity, PostResponse response) {
        BeanUtils.copyProperties(entity, response);
    }

}
