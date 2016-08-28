package com.srgtrujillo.quotes.quote.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.srgtrujillo.quotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuoteDetailActivity extends AppCompatActivity {

    public static final String AUTHOR = "author";
    public static final String IMAGE_URL = "image_url";
    public static final String QUOTE_TEXT = "quote_text";
    public static final String LIKES_COUNT = "likes_count";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_view_quote)
    ImageView imageViewQuote;
    @BindView(R.id.text_view_quote_text)
    TextView textViewQuoteText;
    @BindView(R.id.text_view_author)
    TextView textViewAuthor;
    @BindView(R.id.like_image)
    ImageView imageViewLike;
    @BindView(R.id.likes_count)
    TextView textViewLikesCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_detail);
        ButterKnife.bind(this);

        String imageUrl = getIntent().getStringExtra(IMAGE_URL);
        String quoteText = getIntent().getStringExtra(QUOTE_TEXT);
        String author = getIntent().getStringExtra(AUTHOR);
        int likesCount = getIntent().getIntExtra(LIKES_COUNT, 0);

        initToolbar(author);
        showImage(imageUrl);
        showQuoteText(quoteText);
        showAuthor(author);
        showLike(likesCount);
    }

    private void initToolbar(String name) {
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showImage(String imageUrl) {
        Picasso.with(getApplicationContext()).load(imageUrl).into(imageViewQuote);
    }

    private void showQuoteText(String quoteText) {
        textViewQuoteText.setText("\"" + quoteText + ".\"");
    }

    private void showAuthor(String author) {
        textViewAuthor.setText("- " + author);
    }

    private void showLike(long likesCount) {
        if (likesCount > 0) {
            imageViewLike.setImageResource(R.drawable.ic_like_fill);
        }
        textViewLikesCount.setText(likesCount + "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
