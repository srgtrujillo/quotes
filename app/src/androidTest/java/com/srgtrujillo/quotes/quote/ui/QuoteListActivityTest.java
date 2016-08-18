package com.srgtrujillo.quotes.quote.ui;

import android.support.test.rule.ActivityTestRule;
import com.srgtrujillo.quotes.R;
import com.srgtrujillo.quotes.testingtools.matchers.RecyclerViewItemsCountMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class QuoteListActivityTest {

    @Rule
    public ActivityTestRule<QuoteListActivity> activityTestRule =
            new ActivityTestRule<>(QuoteListActivity.class);

    @Test
    public void
    should_show_empty_case_when_there_are_not_quotes() throws Exception {

    }

    @Test
    public void
    should_show_network_error_when_there_are_not_connection() throws Exception {

    }

    @Test
    public void
    should_show_any_error_when_there_are_generic_error() throws Exception {

    }

    @Test
    public void
    should_show_sorted_quotes_list() throws Exception {

    }

    @Test
    public void
    should_show_all_quotes_list() throws Exception {
        onView(withId(R.id.recycler_view))
                .check(matches(RecyclerViewItemsCountMatcher.withItemCounts(4)));
    }

}