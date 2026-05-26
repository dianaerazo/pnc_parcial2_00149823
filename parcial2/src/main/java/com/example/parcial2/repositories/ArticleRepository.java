package com.example.parcial2.repositories;

import com.example.parcial2.entities.MagicArticle;
import com.example.parcial2.entities.MagicProvider;
import com.example.parcial2.enums.MagicType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<MagicArticle, Long> {

    Optional<MagicArticle> findByNameIgnoreCase(String name);

    boolean existsByProvider(MagicProvider provider);

    List<MagicArticle> findByType(MagicType type);

    List<MagicArticle> findByProviderId(Long providerId);

    List<MagicArticle> findByPriceLessThanEqual(BigDecimal maxPrice);

    List<MagicArticle> findByTypeAndProviderIdAndPriceLessThanEqual(
            MagicType type,
            Long providerId,
            BigDecimal maxPrice
    );
}