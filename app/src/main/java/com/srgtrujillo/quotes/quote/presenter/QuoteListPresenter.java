package com.srgtrujillo.quotes.quote.presenter;

import android.util.Log;
import com.srgtrujillo.quotes.base.MVP;
import com.srgtrujillo.quotes.quote.domain.interactor.GetQuotesUseCase;
import com.srgtrujillo.quotes.quote.domain.model.Quote;
import com.srgtrujillo.quotes.quote.view.QuoteListView;
import rx.Subscription;
import rx.functions.Action1;

import java.net.UnknownHostException;
import java.util.List;


public class QuoteListPresenter implements MVP.Presenter<QuoteListView> {

    private final String TAG = QuoteListPresenter.class.getSimpleName();

    private GetQuotesUseCase useCase;
    private QuoteListView view;

    private Subscription quotesSubscription;

    public QuoteListPresenter(GetQuotesUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void init() {
        view.initUi();
        view.showProgressBar();
        askForQuotes();
    }

    @Override
    public void setView(QuoteListView view) {
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
        Log.i(TAG, "onQuotesReceived: "+quotes.toString());
        view.show(quotes);
        view.hideProgressBar();
    }

    private void onError(Throwable error) {
        if (error instanceof UnknownHostException) {
            view.showNetworkError();
        } else {
            view.showError();
        }
        view.hideProgressBar();
        Log.e(TAG, "onError: ", error);
    }
}
