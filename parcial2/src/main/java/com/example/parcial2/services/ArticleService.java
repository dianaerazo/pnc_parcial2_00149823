package com.example.parcial2.services;

import com.example.parcial2.dto.request.ArticleRequest;
import com.example.parcial2.dto.response.ArticleResponse;
import com.example.parcial2.enums.MagicType;

import java.math.BigDecimal;
import java.util.List;

public interface ArticleService {

    ArticleResponse create(ArticleRequest dto);

    List<ArticleResponse> getAll();

    ArticleResponse getById(Long id);

    ArticleResponse update(Long id, ArticleRequest dto);

    void delete(Long id);

    List<ArticleResponse> filter(
            MagicType type,
            Long providerId,
            BigDecimal maxPrice
    );
}