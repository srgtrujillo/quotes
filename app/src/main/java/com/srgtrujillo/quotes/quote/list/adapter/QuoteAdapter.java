package com.srgtrujillo.quotes.quote.list.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import com.srgtrujillo.quotes.R;
import com.srgtrujillo.quotes.quote.detail.QuoteDetailActivity;
import com.srgtrujillo.quotes.quote.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private Activity activity;
    private SortedList<Quote> quoteList;

    public QuoteAdapter(Activity activity) {
        this.activity = activity;
        quoteList = new SortedList(Quote.class, new SortedListAdapterCallback<Quote>(this) {
            @Override
            public int compare(Quote o1, Quote o2) {
                return o1.compareTo(o2);
            }

            @Override
            public boolean areContentsTheSame(Quote oldItem, Quote newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Quote item1, Quote item2) {
                return item1.getId().equals(item2.getId());
            }
        });
    }

    @Override
    public QuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuoteViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.quote_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(final QuoteViewHolder holder, final int position) {
        final Quote quote = quoteList.get(position);
        holder.render(quote);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToDetailActivity(holder, quote);
            }
        });
    }

    private void moveToDetailActivity(QuoteViewHolder viewHolder, Quote quote) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, viewHolder.itemView, QuoteDetailActivity.AUTHOR_IMAGE);
        Intent intent = new Intent(viewHolder.itemView.getContext(), QuoteDetailActivity.class);
        intent.putExtra(QuoteDetailActivity.AUTHOR, quote.getAuthor());
        intent.putExtra(QuoteDetailActivity.IMAGE_URL, quote.getImageUrl());
        intent.putExtra(QuoteDetailActivity.QUOTE_TEXT, quote.getQuote());
        intent.putExtra(QuoteDetailActivity.AUTHOR, quote.getAuthor());
        intent.putExtra(QuoteDetailActivity.LIKES_COUNT, quote.getLikes().size());
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public void addAll(List<Quote> quoteList) {
        this.quoteList.addAll(quoteList);
        notifyDataSetChanged();
    }

    public List<Quote> getItems() {
        List<Quote> quotes = new ArrayList<>();
        for (int i = 0, size = quoteList.size(); i < size; i++) {
            quotes.add(quoteList.get(i));
        }
        return quotes;
    }

    class QuoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quote_image)
        ImageView image;
        @BindView(R.id.like_image)
        ImageView likeImage;
        @BindView(R.id.quote_text)
        TextView quoteTextView;
        @BindView(R.id.author)
        TextView authorTextView;
        @BindView(R.id.likes_count)
        TextView likeCountTextView;

        private QuoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void render(Quote quote) {
            quoteTextView.setText("\"" + quote.getQuote() + ".\"");
            authorTextView.setText("- " + quote.getAuthor());
            renderImage(quote);
            renderLike(quote.getLikes());
        }

        private void renderImage(Quote quote) {
            Picasso.with(image.getContext())
                    .load(quote.getImageUrl())
                    .into(image);
        }

        private void renderLike(List<String> likes) {
            if (!likes.isEmpty()){
                likeImage.setImageResource(R.drawable.ic_like_fill);
                likeCountTextView.setText(likes.size() + "");
            }
        }
    }
}
