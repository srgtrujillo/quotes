package com.srgtrujillo.quotes;

import android.app.Application;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class QuotesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initCalligraphy();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
