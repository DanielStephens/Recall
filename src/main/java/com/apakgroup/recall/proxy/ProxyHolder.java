package com.apakgroup.recall.proxy;

public class ProxyHolder<T> {

    private final T proxy;

    private final T original;

    public ProxyHolder(final T proxy, final T original) {
        this.proxy = proxy;
        this.original = original;
    }

    public T getProxy() {
        return proxy;
    }

    public T getOriginal() {
        return original;
    }

}

