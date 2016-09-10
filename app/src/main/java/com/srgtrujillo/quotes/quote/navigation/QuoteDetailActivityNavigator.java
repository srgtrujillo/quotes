package com.srgtrujillo.quotes.quote.navigation;

import android.content.Context;
import android.content.Intent;

import com.srgtrujillo.quotes.base.navigator.Navigator;
import com.srgtrujillo.quotes.quote.detail.QuoteDetailActivity;
import com.srgtrujillo.quotes.quote.model.Quote;

public class QuoteDetailActivityNavigator implements Navigator {
    private Context context;
    private String author;
    private String quoteText;
    private String imageUrl;
    private int likesCount;

    public QuoteDetailActivityNavigator(Context context, Quote quote) {
        this.context = context;
        this.author = quote.getAuthor();
        this.quoteText = quote.getQuote();
        this.imageUrl = quote.getImageUrl();
        this.likesCount = quote.getLikes().size();
    }

    @Override
    public void navigate() {
        context.startActivity(getIntent());
    }

    private Intent getIntent() {
        Intent intent = new Intent(context, QuoteDetailActivity.class);
        intent.putExtra(QuoteDetailActivity.AUTHOR, author);
        intent.putExtra(QuoteDetailActivity.QUOTE_TEXT, quoteText);
        intent.putExtra(QuoteDetailActivity.IMAGE_URL, imageUrl);
        intent.putExtra(QuoteDetailActivity.LIKES_COUNT, likesCount);
        return intent;
    }
}