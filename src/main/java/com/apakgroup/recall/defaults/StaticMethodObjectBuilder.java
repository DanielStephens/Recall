package com.apakgroup.recall.defaults;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class StaticMethodObjectBuilder implements DefaultOptionalResolver {

    private final DefaultResolver delegateBuilder;

    public StaticMethodObjectBuilder(final DefaultResolver delegateBuilder) {
        this.delegateBuilder = delegateBuilder;
    }

    @Override
    public <T> Optional<T> generateDefaultFor(final Class<T> clazz) {
        for (Method method : staticBuildingMethods(clazz)) {
            boolean accessibility = method.isAccessible();
            try {
                method.setAccessible(true);

                return Optional
                        .of((T) method.invoke(null, generateDefaultsFor(delegateBuilder, method.getParameterTypes())));

            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                continue;
            } finally {
                method.setAccessible(accessibility);
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

    private <T> Collection<Method> staticBuildingMethods(final Class<T> clazz){
        Collection<Method> allMethods = Arrays.asList(clazz.getDeclaredMethods());
        return Collections2.filter(allMethods, staticBuildMethod(clazz));
    }

    private <T> Predicate<Method> staticBuildMethod(final Class<T> clazz) {
        return new Predicate<Method>() {

            @Override
            public boolean apply(final Method input) {
                return Modifier.isStatic(input.getModifiers()) && clazz.isAssignableFrom(input.getReturnType());
            }

        };
    }

}

