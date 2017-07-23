package com.apakgroup.recall.defaults;


public class NullBuilder implements DefaultResolver {

    @Override
    public <T> T generateDefaultFor(final Class<T> clazz) {
        return null;
    }
}

