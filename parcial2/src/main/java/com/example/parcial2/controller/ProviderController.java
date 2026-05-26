package com.example.parcial2.controller;

import com.example.parcial2.dto.request.ProviderRequest;
import com.example.parcial2.dto.response.ProviderResponse;
import com.example.parcial2.services.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService service;

    @PostMapping
    public ProviderResponse create(
            @Valid @RequestBody ProviderRequest dto
    ) {

        return service.create(dto);
    }

    @GetMapping
    public List<ProviderResponse> getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProviderResponse getById(
            @PathVariable Long id
    ) {

        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ProviderResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ProviderRequest dto
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