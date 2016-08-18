package com.srgtrujillo.quotes.base;


public interface MVP {

    interface View {
        void initUi();
    }

    interface Presenter<T extends View> {
        void init();
        void setView(T view);
        void onDestroy();
    }

}
