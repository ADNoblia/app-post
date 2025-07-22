package com.noblia.app.services;

import org.springframework.stereotype.Service;

import com.noblia.app.dtos.PageRequest;
import com.noblia.app.dtos.PageResponse;
import com.noblia.app.dtos.PostRequest;

@Service
public interface PageService {

    PageResponse create(PageRequest page);
    PageResponse readByTitle(String title);
    PageResponse update(PageRequest page, String title);
    void delete(String title);

    PageResponse createPost(PostRequest post);
    PageResponse deletePost(Long idPost);
}
