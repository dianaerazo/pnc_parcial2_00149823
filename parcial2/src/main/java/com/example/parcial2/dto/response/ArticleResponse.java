package com.example.parcial2.dto.response;

import com.example.parcial2.enums.MagicType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {

    private Long id;
    private String name;
    private MagicType type;
    private BigDecimal price;

    private ProviderResponse provider;
}








