package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp;

import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.AboutPlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.BasicUseCasePlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.DynamicUseCasePlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({
    BasicUseCasePlace.Tokenizer.class,
    DynamicUseCasePlace.Tokenizer.class,
    AboutPlace.Tokenizer.class
})
public interface GWalidateDemoPlaceHistoryMapper extends PlaceHistoryMapper {
}
