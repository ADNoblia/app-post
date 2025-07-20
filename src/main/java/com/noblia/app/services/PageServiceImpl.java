package com.noblia.app.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.noblia.app.dtos.PostResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noblia.app.dtos.PageRequest;
import com.noblia.app.dtos.PageResponse;
import com.noblia.app.dtos.PostRequest;
import com.noblia.app.entities.PageEntity;
import com.noblia.app.repositories.PageRepository;
import com.noblia.app.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
        final var entityResponse = this.pageRepository.findByTitle(title)
            .orElseThrow( () -> new IllegalArgumentException("No se encuenta el titulo"));
            
        final var response = new PageResponse();
        BeanUtils.copyProperties(entityResponse, response);
        final List<PostResponse> listPostsResponse = entityResponse.getPosts()
                .stream()
                .map( post ->
                    PostResponse
                            .builder()
                            .img(post.getImg())
                            .content(post.getContent())
                            .dateCreation(post.getDateCreation())
                            .build()
                ).toList();

        response.setPosts(listPostsResponse);
        return response;
    }

    @Override
    public PageResponse update(PageRequest page, String title) {
        final var entityResponse = this.pageRepository.findByTitle(title)
                .orElseThrow( () -> new IllegalArgumentException("No se encuenta el titulo"));
        entityResponse.setTitle(page.getTitle());
        var pageCreated = this.pageRepository.save(entityResponse);
        final var response = new PageResponse();
        BeanUtils.copyProperties(pageCreated, response);
        return response;
    }

    @Override
    public void delte(String title) {
        if (this.pageRepository.existsByTitle(title)) {
            log.info("Pagina Elimada");
            this.pageRepository.deleteByTitle(title);
        } else {
            throw new IllegalArgumentException("No se encuentra el titulo");
        }
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
