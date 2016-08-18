package com.srgtrujillo.quotes.quote.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.srgtrujillo.quotes.R;
import com.srgtrujillo.quotes.quote.domain.model.Quote;
import com.srgtrujillo.quotes.quote.view.QuoteListView;

import java.util.List;

public class QuoteListActivity extends AppCompatActivity implements QuoteListView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void initUi() {

    }
}
