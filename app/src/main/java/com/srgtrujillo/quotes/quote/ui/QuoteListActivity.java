package com.srgtrujillo.quotes.quote.ui;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.srgtrujillo.quotes.R;
import com.srgtrujillo.quotes.quote.domain.model.Quote;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void show(List<Quote> quoteList) {

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

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void initUi() {
        adapter = new QuoteAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }
}
