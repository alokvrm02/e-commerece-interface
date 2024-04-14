package com.eCommerceInterface.config;

import com.eCommerceInterface.dto.CaffeineCacheDto;
import com.eCommerceInterface.repository.model.ShopperProductMapping;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableConfigurationProperties(value = CaffeineCacheDto.class)
public class CacheConfig {
    @Autowired
    CaffeineCacheDto caffeineCacheDto;

    @Bean
    public Cache<String, List<ShopperProductMapping>> shopperCache(){
        return Caffeine.newBuilder()
                .maximumSize(caffeineCacheDto.getShopperCache().getMaximumSize())
                .expireAfterWrite(Duration.ofMinutes(caffeineCacheDto.getShopperCache().getExpiryInMinutes())).build();
    }
}
