package com.example.url_shortener.controller;

import com.example.url_shortener.model.UrlRequest;
import com.example.url_shortener.model.UrlResponse;
import com.example.url_shortener.service.UrlShortenerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("/")
public class UrlShortenerController {

    final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public String shorten(@RequestBody UrlRequest urlRequest){

        String hash = urlShortenerService.shorten(urlRequest.getUrl());

        return "http://localhost:8080/" + hash;
    }

    @GetMapping("/{hash}")
    public ResponseEntity<HttpStatus> resolve(@PathVariable  String hash){
        String target = urlShortenerService.resolve(hash);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create(target)).header(HttpHeaders.CONNECTION, "close").build();
    }
}
