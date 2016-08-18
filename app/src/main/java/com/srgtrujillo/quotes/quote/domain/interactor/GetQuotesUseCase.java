package com.srgtrujillo.quotes.quote.domain.interactor;

import com.srgtrujillo.quotes.base.interactor.UseCase;
import com.srgtrujillo.quotes.quote.data.QuoteRepository;
import com.srgtrujillo.quotes.quote.domain.model.Quote;
import rx.Observable;
import rx.Scheduler;

import java.util.List;

public class GetQuotesUseCase extends UseCase<List<Quote>> {
    private QuoteRepository repository;

    public GetQuotesUseCase(QuoteRepository repository, Scheduler uiThread, Scheduler executorThread) {
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
