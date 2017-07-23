package com.apakgroup.recall.defaults;


public interface DefaultResolver {

    <T> T generateDefaultFor(Class<T> clazz);
}

