package com.srgtrujillo.quotes.quote.domain.model;

import java.util.List;

public class Comment {

    private String id;
    private String text;
    private User user;
    private String quoteId;
    private long timestamp;
    private List<String> likes;

    public Comment(String id, String text, User user, String quoteId, long timestamp, List<String> likes) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.quoteId = quoteId;
        this.timestamp = timestamp;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<String> getLikes() {
        return likes;
    }
}
