package com.apakgroup.recall.reflection;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConstructorResolver {

    public List<Constructor<?>> resolveOrderedConstructors(final Class<?> clazz) {
        List<Constructor<?>> constructors = Arrays.asList(clazz.getConstructors());

        Collections.sort(constructors, new Comparator<Constructor<?>>() {

            @Override
            public int compare(final Constructor<?> o1, final Constructor<?> o2) {
                if (o1.isAccessible() != o2.isAccessible()) {
                    return o1.isAccessible() ? 1 : -1;
                }

                return Integer.compare(o1.getParameterTypes().length, o2.getParameterTypes().length);
            }

        });

        return constructors;
    }
}

