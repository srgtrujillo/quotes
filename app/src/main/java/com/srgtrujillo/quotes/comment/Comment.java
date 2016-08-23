package com.srgtrujillo.quotes.comment;

import com.srgtrujillo.quotes.user.User;

import java.util.List;

public class Comment {

    private String id;
    private String comment;
    private User user;
    private String quoteId;
    private long timestamp;
    private List<String> likes;

    public Comment(String id, String comment, User user, String quoteId, long timestamp, List<String> likes) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.quoteId = quoteId;
        this.timestamp = timestamp;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
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
