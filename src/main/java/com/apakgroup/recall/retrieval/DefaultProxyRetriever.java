package com.apakgroup.recall.retrieval;

import com.apakgroup.recall.memory.ProxyMemory;

public class DefaultProxyRetriever implements ProxyRetriever {

    @Override
    public <T> T iterativelyRetrieveRealValue(final ProxyMemory memory, final T object) {
        T realValue = object;

        while (memory.isEnhanced(realValue)) {
            realValue = memory.getParent(realValue);
        }

        return realValue;
    }

    @Override
    public Object[] iterativelyRetrieveRealValues(final ProxyMemory memory, final Object[] objects) {
        Object[] actualValues = new Object[objects.length];

        for (int i = 0; i < objects.length; i++) {
            if (!memory.isEnhanced(objects[i]) && objects[i].getClass().isArray()) {
                actualValues[i] = iterativelyRetrieveRealValues(memory, (Object[]) objects[i]);
            } else {
                actualValues[i] = iterativelyRetrieveRealValue(memory, objects[i]);
            }
        }

        return actualValues;
    }

}

