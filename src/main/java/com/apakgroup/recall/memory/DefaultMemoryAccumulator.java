package com.apakgroup.recall.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.apakgroup.recall.bimap.InstanceBiMap;
import com.apakgroup.recall.call.MethodRecaller;

public class DefaultMemoryAccumulator implements MemoryAccumulator {

    private final List<MethodRecaller<?>> calls = new ArrayList<>();

    private final InstanceBiMap<Object> proxyMap = new InstanceBiMap<>();

    @Override
    public <T> void assign(final T returnValue, final T realAnswer) {
        proxyMap.put(returnValue, realAnswer);
    }

    @Override
    public void append(final MethodRecaller<?> callback) {
        calls.add(callback);
    }

    @Override
    public Recaller buildSnapshot() {
        return new DefaultRecaller(Collections.unmodifiableList(calls), new InstanceBiMap<Object>(proxyMap));
    }

}

