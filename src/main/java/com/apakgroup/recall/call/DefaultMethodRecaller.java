package com.apakgroup.recall.call;

import com.apakgroup.recall.memory.ProxyMemory;
import com.apakgroup.recall.retrieval.ProxyRetriever;

import net.sf.cglib.proxy.MethodProxy;

public class DefaultMethodRecaller<T> implements MethodRecaller<T> {

    private final T topic;

    private final Object[] arguments;

    private final MethodProxy method;

    private final Object returnValue;

    private final ProxyRetriever retriever;

    public DefaultMethodRecaller(final T topic, final Object[] arguments, final MethodProxy method, final Object returnValue,
            final ProxyRetriever retriever) {
        this.topic = topic;
        this.arguments = arguments;
        this.method = method;
        this.returnValue = returnValue;
        this.retriever = retriever;
    }

    @Override
    public void recall(final ProxyMemory memory) throws Throwable {
        T realObject = retriever.iterativelyRetrieveRealValue(memory, topic);
        Object[] realArguments = retriever.iterativelyRetrieveRealValues(memory, arguments);

        Object realAnswer = method.invoke(realObject, realArguments);
        memory.assign(returnValue, realAnswer);
    }

    @Override
    public T proxy() {
        return topic;
    }

    @Override
    public String toString() {
        return method.getSignature().toString();
    }


}

