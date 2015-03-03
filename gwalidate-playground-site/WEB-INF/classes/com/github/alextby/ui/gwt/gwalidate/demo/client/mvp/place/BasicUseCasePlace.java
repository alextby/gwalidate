package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Singleton;

public class BasicUseCasePlace extends Place {

    private static final String VIEW_HISTORY_PREFIX = "basic_case";

    public BasicUseCasePlace() {
    }

    @Prefix(value = VIEW_HISTORY_PREFIX)
    public static class Tokenizer implements PlaceTokenizer<BasicUseCasePlace> {

        @Override
        public BasicUseCasePlace getPlace(String token) {
            return new BasicUseCasePlace();
        }

        @Override
        public String getToken(BasicUseCasePlace place) {
            return "";
        }
    }
}
