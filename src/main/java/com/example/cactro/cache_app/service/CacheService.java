package com.example.cactro.cache_app.service;

import com.example.cactro.cache_app.dto.CacheDto;
import com.example.cactro.cache_app.entity.CacheItem;
import com.example.cactro.cache_app.exceptions.ResourceNotFoundException;
import com.example.cactro.cache_app.exceptions.RuntimeConflictException;
import com.example.cactro.cache_app.repository.CacheRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheRepository cacheRepository;
    private final ModelMapper modelMapper;

    @Value("${cache.max-size}")
    private int maxCacheSize;

    @Transactional
    public CacheDto createCache(CacheDto cacheDto) {
        try {
            boolean keyExists = cacheRepository.existsById(cacheDto.getKey());

            if (!keyExists) {
                long currentCacheSize = cacheRepository.count();
                if (currentCacheSize >= maxCacheSize) {
                    throw new RuntimeConflictException("Cache limit reached. Cannot add more items.");
                }
            }

            CacheItem cacheItem = new CacheItem();
            cacheItem.setKey(cacheDto.getKey());
            cacheItem.setValue(cacheDto.getValue());

            CacheItem savedCache = cacheRepository.save(cacheItem);
            return modelMapper.map(savedCache,CacheDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating cache item: " + e.getMessage(), e);
        }
    }

    public CacheDto getCache(String key) {
        try {
            CacheItem cacheItem = cacheRepository.findById(key)
                    .orElseThrow(() -> new ResourceNotFoundException("Cache item not found for key: " + key));
            return modelMapper.map(cacheItem,CacheDto.class);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred.", e);
        }
    }

    public CacheDto deleteCache(String key) {
        try {
            CacheDto cacheDto = getCache(key);
            cacheRepository.deleteById(key);
            return cacheDto;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting cache item: " + e.getMessage(), e);
        }
    }
}
