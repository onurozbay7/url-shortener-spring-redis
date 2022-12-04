package com.example.url_shortener.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UrlRequest {
    private String url;

    public UrlRequest(String url) {
        this.url = url;
    }

    public UrlRequest() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
