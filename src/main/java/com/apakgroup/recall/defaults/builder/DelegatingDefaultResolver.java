package com.apakgroup.recall.defaults.builder;

import com.apakgroup.recall.defaults.DefaultOptionalResolver;
import com.apakgroup.recall.defaults.DefaultResolver;
import com.google.common.base.Optional;

public class DelegatingDefaultResolver implements DefaultResolver {

    private final DefaultOptionalResolver delegateOptionalResolver;

    private final DefaultResolver delegateResolver;
    
    public DelegatingDefaultResolver(final DefaultOptionalResolver delegateOptionalResolver,
            final DefaultResolver delegateResolver) {
        this.delegateOptionalResolver = delegateOptionalResolver;
        this.delegateResolver = delegateResolver;
    }

    @Override
    public <T> T generateDefaultFor(final Class<T> clazz) {
        Optional<T> answer = delegateOptionalResolver.generateDefaultFor(clazz);
        return answer.isPresent() ? answer.get() : delegateResolver.generateDefaultFor(clazz);
    }

}

