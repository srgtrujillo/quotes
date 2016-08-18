package com.srgtrujillo.quotes.quote.ui;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.srgtrujillo.quotes.R;
import com.srgtrujillo.quotes.base.di.QuoteInjection;
import com.srgtrujillo.quotes.quote.domain.model.Quote;
import com.srgtrujillo.quotes.quote.presenter.QuoteListPresenter;
import com.srgtrujillo.quotes.quote.view.QuoteListView;

import java.util.List;

public class QuoteListActivity extends AppCompatActivity implements QuoteListView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.content_loading_progress_bar)
    ContentLoadingProgressBar progressBar;
    @BindView(R.id.information_text)
    TextView informationTextView;

    private QuoteAdapter adapter;
    private QuoteListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        QuoteRepository repository = new QuoteRepository(new Gson(), new OkHttpClient());
//        GetQuotesUseCase useCase = new GetQuotesUseCase(repository,
//                AndroidSchedulers.mainThread(),
//                Schedulers.newThread());
//        presenter = new QuoteListPresenter(useCase);

        presenter = QuoteInjection.injectQuoteListPresent();
        presenter.setView(this);
        presenter.init();
    }

    @Override
    public void show(List<Quote> quoteList) {
        adapter.addAll(quoteList);
    }

    @Override
    public void showEmptyCase() {
        informationTextView.setText(R.string.empty_quotes);
    }

    @Override
    public void showNetworkError() {
        informationTextView.setText(R.string.connection_error);
    }

    @Override
    public void showError() {
        informationTextView.setText(R.string.quotes_error);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void initUi() {
        adapter = new QuoteAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }
}
