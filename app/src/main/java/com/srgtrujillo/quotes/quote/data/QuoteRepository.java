package com.srgtrujillo.quotes.quote.data;

import android.util.Log;
import com.google.gson.Gson;
import com.srgtrujillo.quotes.quote.domain.model.Quote;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class QuoteRepository {

    private final String TAG = QuoteRepository.class.getSimpleName();

    private OkHttpClient client;
    private Gson gson;

    public QuoteRepository(Gson gson, OkHttpClient client) {
        this.client = client;
        this.gson = gson;
    }

    protected StringBuffer getQuotesFromUrl(String endPoint) throws Exception {
        Request request = new Request.Builder()
                .url(endPoint)
                .build();
        Response response = client.newCall(request).execute();
        return new StringBuffer(response.body().string());
    }

    public Observable<List<Quote>> getAll() {
        return Observable.fromCallable(new Callable<List<Quote>>() {
            @Override
            public List<Quote> call() throws Exception {
                StringBuffer response = getQuotesFromUrl(
                        "https://raw.githubusercontent.com/srgtrujillo/quotes/dev/api/quotes.json");
                String json = response.toString();
                Log.i(TAG, json);
                return Arrays.asList(gson.fromJson(json, Quote[].class));
            }
        });
    }
}
