package com.srgtrujillo.quotes;

import android.app.Application;
import com.srgtrujillo.quotes.base.di.QuoteInjection;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class QuotesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initCalligraphy();

        QuoteInjection.load(new QuoteInjection());
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
