package com.srgtrujillo.quotes.quote.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Quote implements Comparable<Quote> {

    private String id;
    private String author;
    private String quote;
    private String imageUrl;
    private List<String> likes;
    private long timestamp;

    public Quote(String id,
                 String author,
                 String quote,
                 String imageUrl,
                 List<String> likes,
                 long timestamp) {
        this.id = id;
        this.author = author;
        this.quote = quote;
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

    public String getQuote() {
        return quote;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getLikes() {
        return new ArrayList<>(likes);
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
        if (!this.quote.equals(quote.quote)) return false;
        if (!imageUrl.equals(quote.imageUrl)) return false;
        return likes != null ? likes.equals(quote.likes) : quote.likes == null;

    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + quote.hashCode();
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

    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", text='" + quote + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", likes=" + likes +
                ", timestamp=" + timestamp +
                '}';
    }
}
