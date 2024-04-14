package com.eCommerceInterface.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "caffeine-cache")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaffeineCacheDto {
    private Cache shopperCache;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cache{
        private String name;
        private  Long expiryInMinutes;
        private  Integer maximumSize;
    }

}
