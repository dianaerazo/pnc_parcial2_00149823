package com.example.parcial2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parcial2.dto.request.ArticleRequest;
import com.example.parcial2.dto.response.ArticleResponse;
import com.example.parcial2.enums.MagicType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/artefacts")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @PostMapping
    public ArticleResponse create(
            @Valid @RequestBody ArticleRequest dto
    ) {

        return service.create(dto);
    }

    @GetMapping
    public List<ArticleResponse> filter(
            @RequestParam MagicType category,
            @RequestParam Long providerId,
            @RequestParam BigDecimal maxPrice
    ) {

        return service.filter(
                category,
                providerId,
                maxPrice
        );
    }

    @GetMapping("/{id}")
    public ArticleResponse getById(
            @PathVariable Long id
    ) {

        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ArticleResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequest dto
    ) {

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {

        service.delete(id);
    }
}