package com.example.parcial2.services.impl;

import com.example.parcial2.dto.request.ProviderRequest;
import com.example.parcial2.dto.response.ProviderResponse;
import com.example.parcial2.entities.MagicProvider;
import com.example.parcial2.exceptions.*;
import com.example.parcial2.repositories.ArticleRepository;
import com.example.parcial2.repositories.ProviderRepository;
import com.example.parcial2.services.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl
        implements ProviderService {

    private final ProviderRepository repository;
    private final ArticleRepository articleRepository;

    @Override
    public ProviderResponse create(ProviderRequest dto) {

        repository.findByNameIgnoreCase(dto.getName())
                .ifPresent(provider -> {

                    throw new ConflictException(
                            "Provider name already exists"
                    );
                });

        MagicProvider provider = MagicProvider.builder()
                .name(dto.getName())
                .type(dto.getType())
                .build();

        repository.save(provider);

        return mapToResponse(provider);
    }

    @Override
    public List<ProviderResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProviderResponse getById(Long id) {

        MagicProvider provider = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Provider not found"
                        ));

        return mapToResponse(provider);
    }

    @Override
    public ProviderResponse update(
            Long id,
            ProviderRequest dto
    ) {

        MagicProvider provider = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Provider not found"
                        ));

        repository.findByNameIgnoreCase(dto.getName())
                .ifPresent(existing -> {

                    if(!existing.getId().equals(id)) {

                        throw new ConflictException(
                                "Provider name already exists"
                        );
                    }
                });

        provider.setName(dto.getName());
        provider.setType(dto.getType());

        repository.save(provider);

        return mapToResponse(provider);
    }

    @Override
    public void delete(Long id) {

        MagicProvider provider = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Provider not found"
                        ));

        if(articleRepository.existsByProvider(provider)) {

            throw new ConflictException(
                    "Provider has associated articles"
            );
        }

        repository.delete(provider);
    }

    private ProviderResponse mapToResponse(
            MagicProvider provider
    ) {

        return ProviderResponse.builder()
                .id(provider.getId())
                .name(provider.getName())
                .type(provider.getType())
                .build();
    }
}