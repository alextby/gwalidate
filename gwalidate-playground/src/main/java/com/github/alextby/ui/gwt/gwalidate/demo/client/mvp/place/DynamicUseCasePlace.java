package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Singleton;

public class DynamicUseCasePlace extends Place {

    private static final String VIEW_HISTORY_PREFIX = "dynamic_case";

    public DynamicUseCasePlace() {
    }

    @Prefix(value = VIEW_HISTORY_PREFIX)
    public static class Tokenizer implements PlaceTokenizer<DynamicUseCasePlace> {

        @Override
        public DynamicUseCasePlace getPlace(String token) {
            return new DynamicUseCasePlace();
        }

        @Override
        public String getToken(DynamicUseCasePlace place) {
            return "";
        }
    }
}
