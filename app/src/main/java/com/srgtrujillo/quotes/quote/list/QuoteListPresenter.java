package com.srgtrujillo.quotes.quote.list;

import android.util.Log;
import com.srgtrujillo.quotes.base.MVP;
import com.srgtrujillo.quotes.quote.usecases.GetQuotes;
import com.srgtrujillo.quotes.quote.model.Quote;
import rx.Subscription;
import rx.functions.Action1;

import java.net.UnknownHostException;
import java.util.List;


public class QuoteListPresenter implements MVP.Presenter<QuoteListPresenter.View> {

    interface View extends MVP.View {
        void show(List<Quote> quoteList);
        void showEmptyCase();
        void showNetworkError();
        void showError();
        void hideProgressBar();
        void showProgressBar();
    }

    private final String TAG = QuoteListPresenter.class.getSimpleName();

    private GetQuotes useCase;
    private View view;
    private Subscription quotesSubscription;

    public QuoteListPresenter(GetQuotes useCase) {
        this.useCase = useCase;
    }

    @Override
    public void init() {
        view.initUi();
        view.showProgressBar();
        askForQuotes();
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        quotesSubscription.unsubscribe();
    }

    private void askForQuotes() {
        quotesSubscription = useCase.execute().subscribe(new Action1<List<Quote>>() {
            @Override
            public void call(List<Quote> quotes) {
                onQuotesReceived(quotes);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                onError(throwable);
            }
        });
    }

    private void onQuotesReceived(List<Quote> quotes) {
        Log.i(TAG, "onQuotesReceived: " + quotes.toString());
        if (quotes.isEmpty()) {
            view.showEmptyCase();
        } else {
            view.show(quotes);
        }
        view.hideProgressBar();
    }

    private void onError(Throwable error) {
        Log.e(TAG, "onError: ", error);
        if (error instanceof UnknownHostException) {
            view.showNetworkError();
        } else {
            view.showError();
        }
        view.hideProgressBar();
    }
}
