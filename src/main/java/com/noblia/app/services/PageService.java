package com.noblia.app.services;

import com.noblia.app.dtos.PageRequest;
import com.noblia.app.dtos.PageResponse;
import com.noblia.app.dtos.PostRequest;
import com.noblia.app.dtos.PostResponse;
import org.springframework.stereotype.Service;

@Service
public interface PageService {

    PageResponse create(PageRequest page);
    PageResponse readByTitle(String title);
    PageResponse update(PageRequest page, String title);
    void delte(String title);

    PageResponse createPost(PostRequest post);
    PageResponse deletePost(Long idPost);
}
