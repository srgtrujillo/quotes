package com.srgtrujillo.quotes.quote.view;

import com.srgtrujillo.quotes.base.MVP;
import com.srgtrujillo.quotes.quote.domain.model.Quote;

import java.util.List;

public interface QuoteListView extends MVP.View {

    void show(List<Quote> quoteList);
    void showEmptyCase();
    void showNetworkError();
    void showError();
    void hideProgressBar();
    void showProgressBar();
}
