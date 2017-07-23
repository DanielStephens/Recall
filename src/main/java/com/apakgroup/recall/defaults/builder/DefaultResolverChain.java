package com.apakgroup.recall.defaults.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.apakgroup.recall.defaults.DefaultOptionalResolver;
import com.apakgroup.recall.defaults.DefaultResolver;

public class DefaultResolverChain {

    private final List<DefaultOptionalResolver> optionalResolvers;

    private DefaultResolverChain() {
        optionalResolvers = new ArrayList<>();
    }

    public static DefaultResolverChain use(final DefaultOptionalResolver optionalResolver) {
        DefaultResolverChain chain = new DefaultResolverChain();
        chain.or(optionalResolver);
        return chain;
    }

    public DefaultResolverChain or(final DefaultOptionalResolver optionalResolver) {
        optionalResolvers.add(optionalResolver);
        return this;
    }

    public DefaultResolver or(final DefaultResolver resolver) {
        List<DefaultOptionalResolver> reversedResolvers = new ArrayList<>(this.optionalResolvers);
        Collections.reverse(reversedResolvers);

        DefaultResolver finalResolver = resolver;

        for (DefaultOptionalResolver optionalResolver : reversedResolvers) {
            finalResolver = new DelegatingDefaultResolver(optionalResolver, finalResolver);
        }

        return finalResolver;

    }

}

