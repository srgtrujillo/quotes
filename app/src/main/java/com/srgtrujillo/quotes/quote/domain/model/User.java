package com.srgtrujillo.quotes.quote.domain.model;

public class User {

    private String name;
    private String imageUrl;

    public User(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
