package com.apakgroup.recall.memory;

import com.apakgroup.recall.bimap.InstanceBiMap;

public class DefaultProxyMemory implements ProxyMemory {

    private final InstanceBiMap<Object> proxyMap;

    public DefaultProxyMemory(final InstanceBiMap<Object> proxyMap) {
        this.proxyMap = proxyMap;
    }

    @Override
    public <T> void assign(final T returnValue, final T realAnswer) {
        proxyMap.put(returnValue, realAnswer);
    }

    @Override
    public boolean isEnhanced(final Object object) {
        return proxyMap.containsKey(object);
    }

    @Override
    public <T> T getParent(final T object) {
        return (T) proxyMap.get(object);
    }

}

