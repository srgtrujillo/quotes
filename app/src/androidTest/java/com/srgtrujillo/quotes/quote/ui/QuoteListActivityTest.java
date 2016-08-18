package com.srgtrujillo.quotes.quote.ui;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import com.srgtrujillo.quotes.R;
import com.srgtrujillo.quotes.base.di.QuoteInjection;
import com.srgtrujillo.quotes.quote.data.QuoteRepository;
import com.srgtrujillo.quotes.quote.domain.model.Quote;
import com.srgtrujillo.quotes.testingtools.matchers.RecyclerViewItemsCountMatcher;
import com.srgtrujillo.quotes.testingtools.viewassertions.recyclerview.RecyclerSortedViewAssertion;
import org.junit.Rule;
import org.junit.Test;
import rx.Observable;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class QuoteListActivityTest {

    @Rule
    public ActivityTestRule<QuoteListActivity> activityTestRule =
            new ActivityTestRule<>(QuoteListActivity.class, true, false);

    @Test
    public void
    should_show_empty_case_when_there_are_not_quotes() throws Exception {
        QuoteRepository stub = mock(QuoteRepository.class);
        given(stub.getAll()).willReturn(getEmptyObservable());

        QuoteInjection.config(stub);

        activityTestRule.launchActivity(null);

        onView(withId(R.id.information_text))
                .check(matches(withText(getString(R.string.empty_quotes))));
    }

    @NonNull
    private String getString(@StringRes int stringResource) {
        return InstrumentationRegistry.getTargetContext().getString(stringResource);
    }

    @Test
    public void
    should_show_network_error_when_there_are_not_connection() throws Exception {
        QuoteRepository stub = mock(QuoteRepository.class);
        given(stub.getAll()).willReturn(getNetworkErrorObservable());

        QuoteInjection.config(stub);

        activityTestRule.launchActivity(null);

        onView(withId(R.id.information_text))
                .check(matches(withText(getString(R.string.connection_error))));
    }

    @Test
    public void
    should_show_any_error_when_there_are_generic_error() throws Exception {
        QuoteRepository stub = mock(QuoteRepository.class);
        given(stub.getAll()).willReturn(getAnyErrorObservable());

        QuoteInjection.config(stub);

        activityTestRule.launchActivity(null);

        onView(withId(R.id.information_text))
                .check(matches(withText(getString(R.string.quotes_error))));
    }

    @Test
    public void
    should_show_sorted_quotes_list() throws Exception {
        QuoteRepository stub = mock(QuoteRepository.class);
        given(stub.getAll()).willReturn(getFourObservable());

        QuoteInjection.config(stub);

        activityTestRule.launchActivity(null);

        onView(withId(R.id.recycler_view))
                .check(RecyclerSortedViewAssertion.isSorted(new RecyclerSortedViewAssertion.WithAdapter<Quote>() {
                    @Override
                    public List<Quote> itemsToSort(RecyclerView recyclerView) {
                        QuoteAdapter adapter = (QuoteAdapter) recyclerView.getAdapter();
                        return adapter.getItems();
                    }
                }));

    }

    @Test
    public void
    should_show_all_quotes_list() throws Exception {
        QuoteRepository stub = mock(QuoteRepository.class);
        given(stub.getAll()).willReturn(getFourObservable());

        QuoteInjection.config(stub);

        activityTestRule.launchActivity(null);

        onView(withId(R.id.recycler_view))
                .check(matches(RecyclerViewItemsCountMatcher.withItemCounts(4)));
    }

    public Observable<List<Quote>> getEmptyObservable() {
        return Observable.fromCallable(new Callable<List<Quote>>() {
            @Override
            public List<Quote> call() throws Exception {
                return Collections.emptyList();
            }
        });
    }

    public Observable<List<Quote>> getNetworkErrorObservable() {
        return Observable.fromCallable(new Callable<List<Quote>>() {
            @Override
            public List<Quote> call() throws Exception {
                throw new UnknownHostException();
            }
        });
    }

    public Observable<List<Quote>> getAnyErrorObservable() {
        return Observable.fromCallable(new Callable<List<Quote>>() {
            @Override
            public List<Quote> call() throws Exception {
                throw new Exception();
            }
        });
    }

    public Observable<List<Quote>> getFourObservable() {
        return Observable.fromCallable(new Callable<List<Quote>>() {
            @Override
            public List<Quote> call() throws Exception {
                return Arrays.asList(new Quote("", "", "", "http://algo.com", Collections.<String>emptyList(), 1),
                        new Quote("", "", "", "http://algo.com", Collections.<String>emptyList(), 2),
                        new Quote("", "", "", "http://algo.com", Collections.<String>emptyList(), 3),
                        new Quote("", "", "", "http://algo.com", Collections.<String>emptyList(), 4));
            }
        });
    }
}