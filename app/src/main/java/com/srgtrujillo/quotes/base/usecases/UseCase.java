package com.srgtrujillo.quotes.base.usecases;

import rx.Observable;
import rx.Scheduler;

public abstract class UseCase<T> {

    protected final Scheduler uiThread;
    protected final Scheduler executorThread;

    protected UseCase(Scheduler uiThread, Scheduler executorThread) {
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    public Observable<T> execute() {
        return buildUseCaseObservable();
    }

    protected abstract Observable<T> buildUseCaseObservable();
}
