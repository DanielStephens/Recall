package com.apakgroup.recall.proxy;

import com.apakgroup.recall.callback.MemoriserCallback;
import com.apakgroup.recall.enhancer.RecallEnhancer;
import com.apakgroup.recall.memory.MemoryAccumulator;

public class ProxyContext<T> {

    private final T original;

    private final MemoriserCallback callback;

    public ProxyContext(final T original, final MemoriserCallback callback) {
        this.original = original;
        this.callback = callback;
    }

    public ProxyHolder<T> build(final RecallEnhancer enhancer, final MemoryAccumulator accumulator) {
        T proxy = enhancer.enhance(original, callback);
        ProxyHolder<T> holder = new ProxyHolder<>(proxy, original);
        accumulator.assign(proxy, original);
        return holder;
    }
}

