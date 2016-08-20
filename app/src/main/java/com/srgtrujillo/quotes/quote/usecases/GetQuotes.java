package com.srgtrujillo.quotes.quote.usecases;

import com.srgtrujillo.quotes.base.usecases.UseCase;
import com.srgtrujillo.quotes.quote.data.QuoteRepository;
import com.srgtrujillo.quotes.quote.model.Quote;
import rx.Observable;
import rx.Scheduler;

import java.util.List;

public class GetQuotes extends UseCase<List<Quote>> {

    private QuoteRepository repository;

    public GetQuotes(QuoteRepository repository, Scheduler uiThread, Scheduler executorThread) {
        super(uiThread, executorThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<Quote>> buildUseCaseObservable() {
        return repository.getAll()
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
