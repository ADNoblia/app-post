package com.noblia.app.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noblia.app.dtos.PageRequest;
import com.noblia.app.dtos.PageResponse;
import com.noblia.app.services.PageService;

import lombok.AllArgsConstructor;

@RestController // Exponer Apis Rest
@RequestMapping(path = "page") // Indica el path para acceder al servicio
@AllArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping(path="{title}")
    public ResponseEntity<PageResponse> getPage(@PathVariable String title) {
        return ResponseEntity.ok(this.pageService.readByTitle(title));
    }

    @PostMapping
    public ResponseEntity<?> postPage(@RequestBody PageRequest page) {
        page.setTitle(this.normalizeTitle(page.getTitle()));
        final var uri = this.pageService.create(page).getTitle();
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @PutMapping
    public ResponseEntity<?> updatePage() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePage() {
        return null;
    }

    private String normalizeTitle(String title) {
        if(title.contains("")) {
            return title.replace(" ", "");
        } else {
            return title;
        }
    }

}
