package com.srgtrujillo.quotes.quote.ui;

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
import com.srgtrujillo.quotes.quote.domain.model.Quote;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private SortedList<Quote> quoteList;

    public QuoteAdapter() {
        quoteList = new SortedList<Quote>(Quote.class, new SortedListAdapterCallback<Quote>(this) {
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
    public void onBindViewHolder(QuoteViewHolder holder, int position) {
        Quote quote = quoteList.get(position);
        holder.render(quote);
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public void addAll(List<Quote> quoteList) {
        this.quoteList.addAll(quoteList);
        notifyDataSetChanged();
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


        QuoteViewHolder(View itemView) {
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
                likeCountTextView.setText(likes.size()+"");
            }
        }
    }
}
