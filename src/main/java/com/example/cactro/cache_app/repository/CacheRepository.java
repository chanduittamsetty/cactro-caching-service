package com.example.cactro.cache_app.repository;

import com.example.cactro.cache_app.entity.CacheItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CacheRepository extends JpaRepository<CacheItem, String> {
}
