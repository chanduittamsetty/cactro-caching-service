package com.example.cactro.cache_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cache")
public class CacheItem {
    @Id
    @NotBlank(message = "Key cannot be blank")
    @Column(name = "cache_key")
    private String key;

    @NotBlank(message = "Value cannot be blank")
    @Column(name = "cache_value")
    private String value;
}
