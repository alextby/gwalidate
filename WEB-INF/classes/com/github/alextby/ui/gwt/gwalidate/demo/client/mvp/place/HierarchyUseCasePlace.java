package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class HierarchyUseCasePlace extends Place {

    private static final String VIEW_HISTORY_PREFIX = "hierarchy_case";

    public HierarchyUseCasePlace() {
    }

    @Prefix(value = VIEW_HISTORY_PREFIX)
    public static class Tokenizer implements PlaceTokenizer<HierarchyUseCasePlace> {

        @Override
        public HierarchyUseCasePlace getPlace(String token) {
            return new HierarchyUseCasePlace();
        }

        @Override
        public String getToken(HierarchyUseCasePlace place) {
            return "";
        }
    }
}
