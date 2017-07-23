package com.apakgroup.recall.callback;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.apakgroup.recall.call.MethodRecaller;
import com.apakgroup.recall.call.MethodRecallerFactory;
import com.apakgroup.recall.defaults.DefaultResolver;
import com.apakgroup.recall.memory.MemoryAccumulator;
import com.apakgroup.recall.setup.Recall;

import net.sf.cglib.proxy.MethodProxy;

public class DefaultMemoriserCallback implements MemoriserCallback {

    private final Recall recall;

    private final DefaultResolver defaultBuilder;

    private final MemoryAccumulator accumulator;

    private final MethodRecallerFactory recallerFactory;

    public DefaultMemoriserCallback(final Recall recall, final DefaultResolver defaultBuilder,
            final MemoryAccumulator accumulator,
            final MethodRecallerFactory recallerFactory) {
        this.recall = recall;
        this.defaultBuilder = defaultBuilder;
        this.accumulator = accumulator;
        this.recallerFactory = recallerFactory;
    }


    @Override
    public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy methodProxy)
            throws Throwable {
        Object outputProxy = defaultBuilder.generateDefaultFor(method.getReturnType());

        MethodRecaller<?> recaller = recallerFactory.build(obj, args, methodProxy, outputProxy);

        accumulator.append(recaller);
        if (outputProxy != null && !Modifier.isFinal(outputProxy.getClass().getModifiers())) {
            outputProxy = recall.enhance(outputProxy);
        }

        return outputProxy;
    }

}

