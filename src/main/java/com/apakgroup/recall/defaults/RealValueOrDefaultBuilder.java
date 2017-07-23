package com.apakgroup.recall.defaults;

import com.google.common.base.Optional;

public class RealValueOrDefaultBuilder implements DefaultOptionalResolver {

    private final Object realValue;

    public RealValueOrDefaultBuilder(final Object realValue) {
        this.realValue = realValue;
    }

    @Override
    public <T> Optional<T> generateDefaultFor(final Class<T> clazz) {
        if (clazz.isAssignableFrom(realValue.getClass())) {
            return Optional.of((T) realValue);
        }

        return Optional.absent();
    }

}

