package com.example.parcial2.repositories;

import com.example.parcial2.entities.MagicProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository
        extends JpaRepository<MagicProvider, Long> {

    Optional<MagicProvider> findByNameIgnoreCase(String name);
}
