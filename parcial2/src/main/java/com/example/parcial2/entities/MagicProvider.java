package com.example.parcial2.entities;

import com.example.parcial2.enums.MagicType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "providers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private MagicType type;

    @OneToMany(mappedBy = "provider")
    private List<MagicArticle> articles;
}
