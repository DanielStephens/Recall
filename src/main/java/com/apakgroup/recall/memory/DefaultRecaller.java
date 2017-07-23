package com.apakgroup.recall.memory;

import java.util.List;

import com.apakgroup.recall.bimap.InstanceBiMap;
import com.apakgroup.recall.call.MethodRecaller;

public class DefaultRecaller implements Recaller {

    private final List<MethodRecaller<?>> calls;

    private final ProxyMemory proxyMemory;

    public DefaultRecaller(final List<MethodRecaller<?>> calls, final InstanceBiMap<Object> proxyMap) {
        this.calls = calls;
        proxyMemory = new DefaultProxyMemory(proxyMap);
    }

    @Override
    public void recall() throws Throwable {
        for (MethodRecaller<?> call : calls) {
            call.recall(proxyMemory);
        }
    }

}

