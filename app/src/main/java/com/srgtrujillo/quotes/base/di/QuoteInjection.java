package com.srgtrujillo.quotes.base.di;

import com.google.gson.Gson;
import com.srgtrujillo.quotes.quote.data.QuoteRepository;
import com.srgtrujillo.quotes.quote.domain.interactor.GetQuotesUseCase;
import com.srgtrujillo.quotes.quote.presenter.QuoteListPresenter;
import okhttp3.OkHttpClient;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuoteInjection {

    private static QuoteInjection injector;

    private QuoteListPresenter quoteListPresenter;
    private GetQuotesUseCase getQuotesUseCase;
    private QuoteRepository quoteRepository;
    private Scheduler executorThread;
    private Scheduler uiThread;


    private QuoteListPresenter quoteListPresenter() {
        if (quoteListPresenter == null) {
            return new QuoteListPresenter(getQuotesUseCase());
        }

        return quoteListPresenter;
    }

    public GetQuotesUseCase getQuotesUseCase() {
        if (getQuotesUseCase == null) {
            return new GetQuotesUseCase(quoteRepository(), uiThread(), executorThread());
        }

        return getQuotesUseCase;
    }

    private Scheduler executorThread() {
        if (executorThread == null) {
            return Schedulers.newThread();
        }

        return executorThread;
    }

    private Scheduler uiThread() {
        if (uiThread == null) {
            return AndroidSchedulers.mainThread();
        }

        return uiThread;
    }

    private QuoteRepository quoteRepository() {
        if (quoteRepository == null) {
            return new QuoteRepository(new Gson(), new OkHttpClient());
        }

        return quoteRepository;
    }


    /////////////////////////////
    ////      Public API     ////
    /////////////////////////////

    public static void load(QuoteInjection injectorLocator) {
        injector = injectorLocator;
    }

    public static void config(QuoteRepository quoteRepository) {
        injector.configService(quoteRepository);
    }

    private void configService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public static void config(GetQuotesUseCase getQuotesUseCase) {
        injector.configService(getQuotesUseCase);
    }

    private void configService(GetQuotesUseCase getQuotesUseCase) {
        this.getQuotesUseCase = getQuotesUseCase;
    }

    public static void configExecutor(Scheduler executorThread) {
        injector.configServiceEx(executorThread);
    }

    private void configServiceEx(Scheduler executorThread) {
        this.executorThread = executorThread;
    }

    public static void configUi(Scheduler uiThread) {
        injector.configServiceUI(uiThread);
    }

    private void configServiceUI(Scheduler uiThread) {
        this.uiThread = uiThread;
    }

    public static void config(QuoteListPresenter quoteListPresenter) {
        injector.configService(quoteListPresenter);
    }

    private void configService(QuoteListPresenter quoteListPresenter) {
        this.quoteListPresenter = quoteListPresenter;
    }

    public static QuoteListPresenter injectQuoteListPresent() {
        return injector.quoteListPresenter();
    }
}

