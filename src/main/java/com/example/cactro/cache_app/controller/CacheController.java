package com.example.cactro.cache_app.controller;

import com.example.cactro.cache_app.dto.CacheDto;
import com.example.cactro.cache_app.entity.CacheItem;
import com.example.cactro.cache_app.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    @PostMapping
    public ResponseEntity<CacheDto> createCache(@RequestBody CacheDto item) {
        CacheDto cacheDto = cacheService.createCache(item);
        return ResponseEntity.ok(cacheDto);
    }

    @GetMapping("/{key}")
    public ResponseEntity<CacheDto> getCache(@PathVariable String key) {
        CacheDto cacheDto = cacheService.getCache(key);
        return ResponseEntity.ok(cacheDto);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<CacheDto> deleteCache(@PathVariable String key) {
        CacheDto cacheDto = cacheService.deleteCache(key);
        return ResponseEntity.ok(cacheDto);
    }
}
