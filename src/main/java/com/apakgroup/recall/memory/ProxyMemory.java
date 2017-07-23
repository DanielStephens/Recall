package com.apakgroup.recall.memory;

public interface ProxyMemory {

    <T> void assign(T returnValue, T realAnswer);

    boolean isEnhanced(Object object);

    <T> T getParent(T object);

}

