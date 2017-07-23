package com.apakgroup.recall.enhancer;

import java.lang.reflect.Constructor;

import com.apakgroup.recall.defaults.DefaultResolver;
import com.apakgroup.recall.reflection.ConstructorResolver;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class RecallEnhancer {

    private final ConstructorResolver constructorResolver;

    private final DefaultResolver defaultBuilder;

    public RecallEnhancer(final ConstructorResolver constructorResolver, final DefaultResolver defaultBuilder) {
        this.constructorResolver = constructorResolver;
        this.defaultBuilder = defaultBuilder;
    }

    public <T> T enhance(final T obj, final Callback callback) {
        return (T) enhance(obj.getClass(), callback);
    }

    public <T> T enhance(final Class<T> clazz, final Callback callback) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(unEnhance(clazz));

        if (clazz.isInterface()) {
            enhancer.setInterfaces(new Class[] { clazz });
        } else {
            enhancer.setInterfaces(clazz.getInterfaces());
        }

        enhancer.setCallback(callback);

        for (Constructor<?> constructor : constructorResolver.resolveOrderedConstructors(clazz)) {
            return (T) enhancer.create(constructor.getParameterTypes(),
                    generateDefaultsFor(defaultBuilder, constructor.getParameterTypes()));
        }

        return (T) enhancer.create();
    }

    private static Class<?> unEnhance(final Class<?> clazz) {
        Class<?> k = clazz;

        while (Enhancer.isEnhanced(k) || org.mockito.cglib.proxy.Enhancer.isEnhanced(k)
                || org.assertj.core.internal.cglib.proxy.Enhancer.isEnhanced(clazz)) {
            k = k.getSuperclass();
        }

        return k;
    }

    private Object[] generateDefaultsFor(final DefaultResolver builder, final Class<?>[] classes) {
        Object[] proxies = new Object[classes.length];

        for (int i = 0; i < classes.length; i++) {
            proxies[i] = builder.generateDefaultFor(classes[i]);
        }

        return proxies;
    }

}

