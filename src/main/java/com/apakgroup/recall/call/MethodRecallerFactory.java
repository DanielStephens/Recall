package com.apakgroup.recall.call;

import com.apakgroup.recall.retrieval.ProxyRetriever;

import net.sf.cglib.proxy.MethodProxy;

public class MethodRecallerFactory {

    private final ProxyRetriever retriever;

    public MethodRecallerFactory(final ProxyRetriever retriever) {
        this.retriever = retriever;
    }

    public <T> MethodRecaller<T> build(final T topic, final Object[] arguments, final MethodProxy method,
            final Object returnValue) {
        return new DefaultMethodRecaller<>(topic, arguments, method, returnValue, retriever);
    }
}

