package com.example.parcial2.services;

import com.example.parcial2.dto.request.ProviderRequest;
import com.example.parcial2.dto.response.ProviderResponse;

import java.util.List;

public interface ProviderService {

    ProviderResponse create(ProviderRequest dto);

    List<ProviderResponse> getAll();

    ProviderResponse getById(Long id);

    ProviderResponse update(Long id, ProviderRequest dto);

    void delete(Long id);
}