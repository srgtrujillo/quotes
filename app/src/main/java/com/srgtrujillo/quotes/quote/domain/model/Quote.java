package com.srgtrujillo.quotes.quote.domain.model;

import android.support.annotation.NonNull;

import java.util.List;

public class Quote implements Comparable<Quote> {

    private String id;
    private String author;
    private String text;
    private String imageUrl;
    private List<String> likes;
    private long timestamp;

    public Quote(String id,
                 String author,
                 String text,
                 String imageUrl,
                 List<String> likes,
                 long timestamp) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.imageUrl = imageUrl;
        this.likes = likes;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        if (timestamp != quote.timestamp) return false;
        if (!author.equals(quote.author)) return false;
        if (!text.equals(quote.text)) return false;
        if (!imageUrl.equals(quote.imageUrl)) return false;
        return likes != null ? likes.equals(quote.likes) : quote.likes == null;

    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + imageUrl.hashCode();
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    @Override
    public int compareTo(@NonNull Quote quote) {
        long quoteTimestamp = quote.getTimestamp();

        if (timestamp > quoteTimestamp) {
            return 1;
        } else if (timestamp < quoteTimestamp) {
            return -1;
        }

        return 0;
    }
}
