package com.github.alextby.ui.gwt.gwalidate.core.convert;

/**
 * Default empty converter provider for extension types.
 */
public class ConverterProvider {

    public <T extends TextConverter> T byClazz(Class<T> clazzOfConverter) {
        // provide nothing by default
        return null;
    }
}
