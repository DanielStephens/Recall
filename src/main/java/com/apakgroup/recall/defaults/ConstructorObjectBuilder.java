package com.apakgroup.recall.defaults;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.apakgroup.recall.reflection.ConstructorResolver;
import com.google.common.base.Optional;

public class ConstructorObjectBuilder implements DefaultOptionalResolver {

    private final ConstructorResolver constructorResolver;

    private final DefaultResolver delegateBuilder;

    public ConstructorObjectBuilder(final ConstructorResolver constructorResolver,
            final DefaultResolver delegateBuilder) {
        this.constructorResolver = constructorResolver;
        this.delegateBuilder = delegateBuilder;
    }

    @Override
    public <T> Optional<T> generateDefaultFor(final Class<T> clazz) {
        for (Constructor<?> c : constructorResolver.resolveOrderedConstructors(clazz)) {
            try {
                return Optional.of((T) c.newInstance(generateDefaultsFor(delegateBuilder, c.getParameterTypes())));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                continue;
            }
        }

        return Optional.absent();
    }

    private Object[] generateDefaultsFor(final DefaultResolver builder, final Class<?>[] classes) {
        Object[] proxies = new Object[classes.length];

        for (int i = 0; i < classes.length; i++) {
            proxies[i] = builder.generateDefaultFor(classes[i]);
        }

        return proxies;
    }

}

