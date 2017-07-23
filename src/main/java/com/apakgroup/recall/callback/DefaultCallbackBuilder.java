package com.apakgroup.recall.callback;

import com.apakgroup.recall.call.MethodRecallerFactory;
import com.apakgroup.recall.defaults.DefaultResolver;
import com.apakgroup.recall.memory.MemoryAccumulator;
import com.apakgroup.recall.setup.Recall;

public class DefaultCallbackBuilder implements CallbackBuilder {

    private final Recall recall;

    private final DefaultResolver defaultBuilder;

    private final MethodRecallerFactory recallerFactory;

    public DefaultCallbackBuilder(final Recall recall, final DefaultResolver defaultBuilder,
            final MethodRecallerFactory recallerFactory) {
        this.recall = recall;
        this.defaultBuilder = defaultBuilder;
        this.recallerFactory = recallerFactory;
    }

    @Override
    public MemoriserCallback buildMemoriser(final MemoryAccumulator accumulator) {
        return new DefaultMemoriserCallback(recall, defaultBuilder, accumulator, recallerFactory);
    }

}

