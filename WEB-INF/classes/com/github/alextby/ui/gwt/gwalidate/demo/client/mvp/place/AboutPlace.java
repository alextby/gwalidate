package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class AboutPlace extends Place {

    private static final String VIEW_HISTORY_PREFIX = "about";

    public AboutPlace() {
    }

    @Prefix(value = VIEW_HISTORY_PREFIX)
    public static class Tokenizer implements PlaceTokenizer<AboutPlace> {

        @Override
        public AboutPlace getPlace(String token) {
            return new AboutPlace();
        }

        @Override
        public String getToken(AboutPlace place) {
            return "";
        }
    }
}
