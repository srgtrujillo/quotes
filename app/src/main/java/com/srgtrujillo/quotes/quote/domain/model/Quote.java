package com.srgtrujillo.quotes.quote.domain.model;

import java.util.List;

public class Quote {

    private String author;
    private String text;
    private String imageUrl;
    private List<String> likes;
    private long timestamp;

    public Quote(String author,
                 String text,
                 String imageUrl,
                 List<String> likes,
                 long timestamp) {
        this.author = author;
        this.text = text;
        this.imageUrl = imageUrl;
        this.likes = likes;
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getLikes() {
        return likes;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
