package com.apakgroup.recall.defaults;

import java.lang.reflect.Method;

import com.apakgroup.recall.callback.CallbackBuilder;
import com.apakgroup.recall.callback.MemoriserCallback;
import com.apakgroup.recall.enhancer.RecallEnhancer;
import com.apakgroup.recall.memory.MemoryAccumulator;
import com.google.common.base.Optional;

import net.sf.cglib.proxy.MethodProxy;

public class InterfaceProxyBuilder implements DefaultOptionalResolver {

    private RecallEnhancer recallEnhancer;

    private CallbackBuilder callbackBuilder;

    private final MemoryAccumulator memoryAccumulator;
    
    public InterfaceProxyBuilder(final MemoryAccumulator memoryAccumulator) {
        this.memoryAccumulator = memoryAccumulator;
    }

    @Override
    public <T> Optional<T> generateDefaultFor(final Class<T> clazz) {
        if (!clazz.isInterface()) {
            return Optional.absent();
        }

        try {
            T t = recallEnhancer.enhance(clazz, new MemoriserCallback() {

                @Override
                public Object intercept(final Object obj, final Method method, final Object[] args,
                        final MethodProxy proxy) throws Throwable {
                    return null;
                }
            });
            return Optional.fromNullable(t);
        } catch (Exception e) {
            return Optional.absent();
        }
    }
    
    public void setRecallEnhancer(final RecallEnhancer recallEnhancer) {
        this.recallEnhancer = recallEnhancer;
    }

    public void setCallbackBuilder(final CallbackBuilder callbackBuilder) {
        this.callbackBuilder = callbackBuilder;
    }

}

