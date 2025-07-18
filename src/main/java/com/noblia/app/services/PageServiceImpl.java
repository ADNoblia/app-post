package com.noblia.app.services;

import com.noblia.app.dtos.PageRequest;
import com.noblia.app.dtos.PageResponse;
import com.noblia.app.dtos.PostRequest;
import com.noblia.app.entities.PageEntity;
import com.noblia.app.repositories.PageRepository;
import com.noblia.app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final UserRepository userRepository;

    @Override
    public PageResponse create(PageRequest page) {
        final var entity = new PageEntity(); // Craemos un objeto e la base de datos
        BeanUtils.copyProperties(page, entity); // Copiamos los argumentos del argumento PAGE en ENTITY

        final var user = this.userRepository.findById(page.getUserId())
                .orElseThrow( () -> new IllegalArgumentException("Usuario no encontrado con ID: " + page.getUserId()));
        entity.setDateCreation(LocalDateTime.now());
        entity.setUser(user);
        entity.setPosts(new ArrayList<>());

        var pageCreated = this.pageRepository.save(entity);
        final var response = new PageResponse();
        BeanUtils.copyProperties(pageCreated, response);
        return response;
    }

    @Override
    public PageResponse readByTitle(String title) {
        return null;
    }

    @Override
    public PageResponse update(PageRequest page, String title) {
        return null;
    }

    @Override
    public void delte(String title) {

    }

    @Override
    public PageResponse createPost(PostRequest post) {
        return null;
    }

    @Override
    public PageResponse deletePost(Long idPost) {
        return null;
    }
}
