package com.example.url_shortener.service;



import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UrlShortenerService {

    final RedisTemplate<String, String> redisTemplate;

    private final MessageDigest digest = MessageDigest.getInstance("SHA-256");

    public UrlShortenerService(RedisTemplate<String, String> redisTemplate) throws NoSuchAlgorithmException {
        this.redisTemplate = redisTemplate;
    }

    public String shorten(String url){
        String hash = hash(url);
        redisTemplate.opsForValue().set(hash,url);

        return hash;
    }

    private String hash(String url){
        byte[] bytes = digest.digest(url.getBytes());
        String hash = String.format("%32x", new BigInteger(1,bytes));

        return hash.substring(0,6);
    }

    public String resolve(String hash) {
        if(hash.isBlank()){
            throw new RuntimeException(hash + " not found.");
        }
       return redisTemplate.opsForValue().get(hash);
    }
}
