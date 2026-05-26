package com.example.parcial2.services.impl;

import com.example.parcial2.dto.request.ArticleRequest;
import com.example.parcial2.dto.response.ArticleResponse;
import com.example.parcial2.dto.response.ProviderResponse;
import com.example.parcial2.entities.MagicArticle;
import com.example.parcial2.entities.MagicProvider;
import com.example.parcial2.enums.MagicType;
import com.example.parcial2.exceptions.*;
import com.example.parcial2.repositories.ArticleRepository;
import com.example.parcial2.repositories.ProviderRepository;
import com.example.parcial2.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl
        implements ArticleService {

    private final ArticleRepository repository;
    private final ProviderRepository providerRepository;

    @Override
    public ArticleResponse create(ArticleRequest dto) {

        repository.findByNameIgnoreCase(dto.getName())
                .ifPresent(article -> {

                    throw new ConflictException(
                            "Article name already exists"
                    );
                });

        MagicProvider provider =
                providerRepository.findById(dto.getProviderId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider not found"
                                ));

        if(provider.getType() != dto.getType()) {

            throw new UnprocessableEntityException(
                    "Provider type does not match article type"
            );
        }

        MagicArticle article = MagicArticle.builder()
                .name(dto.getName())
                .type(dto.getType())
                .price(dto.getPrice())
                .provider(provider)
                .build();

        repository.save(article);

        return mapToResponse(article);
    }

    @Override
    public List<ArticleResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ArticleResponse getById(Long id) {

        MagicArticle article = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Article not found"
                        ));

        return mapToResponse(article);
    }

    @Override
    public ArticleResponse update(
            Long id,
            ArticleRequest dto
    ) {

        MagicArticle article = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Article not found"
                        ));

        repository.findByNameIgnoreCase(dto.getName())
                .ifPresent(existing -> {

                    if(!existing.getId().equals(id)) {

                        throw new ConflictException(
                                "Article name already exists"
                        );
                    }
                });

        MagicProvider provider =
                providerRepository.findById(dto.getProviderId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Provider not found"
                                ));

        if(provider.getType() != dto.getType()) {

            throw new UnprocessableEntityException(
                    "Provider type does not match article type"
            );
        }

        article.setName(dto.getName());
        article.setType(dto.getType());
        article.setPrice(dto.getPrice());
        article.setProvider(provider);

        repository.save(article);

        return mapToResponse(article);
    }

    @Override
    public void delete(Long id) {

        MagicArticle article = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Article not found"
                        ));

        repository.delete(article);
    }

    @Override
    public List<ArticleResponse> filter(
            MagicType type,
            Long providerId,
            BigDecimal maxPrice
    ) {

        return repository
                .findByTypeAndProviderIdAndPriceLessThanEqual(
                        type,
                        providerId,
                        maxPrice
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ArticleResponse mapToResponse(
            MagicArticle article
    ) {

        ProviderResponse provider =
                ProviderResponse.builder()
                        .id(article.getProvider().getId())
                        .name(article.getProvider().getName())
                        .type(article.getProvider().getType())
                        .build();

        return ArticleResponse.builder()
                .id(article.getId())
                .name(article.getName())
                .type(article.getType())
                .price(article.getPrice())
                .provider(provider)
                .build();
    }
}