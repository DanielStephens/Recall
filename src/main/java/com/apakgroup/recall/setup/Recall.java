package com.apakgroup.recall.setup;

import com.apakgroup.recall.call.MethodRecallerFactory;
import com.apakgroup.recall.callback.CallbackBuilder;
import com.apakgroup.recall.callback.DefaultCallbackBuilder;
import com.apakgroup.recall.defaults.ConstructorObjectBuilder;
import com.apakgroup.recall.defaults.DefaultObjectBuilder;
import com.apakgroup.recall.defaults.DefaultResolver;
import com.apakgroup.recall.defaults.InterfaceProxyBuilder;
import com.apakgroup.recall.defaults.NullBuilder;
import com.apakgroup.recall.defaults.builder.DefaultResolverChain;
import com.apakgroup.recall.enhancer.RecallEnhancer;
import com.apakgroup.recall.memory.DefaultMemoryAccumulator;
import com.apakgroup.recall.memory.MemoryAccumulator;
import com.apakgroup.recall.proxy.ProxyContext;
import com.apakgroup.recall.proxy.ProxyHolder;
import com.apakgroup.recall.reflection.ConstructorResolver;
import com.apakgroup.recall.retrieval.DefaultProxyRetriever;
import com.apakgroup.recall.retrieval.ProxyRetriever;

public class Recall {

    private final ConstructorResolver constructorResolver = new ConstructorResolver();

    private final DefaultResolver nullBuilder = new NullBuilder();


    private ProxyRetriever proxyRetriever = new DefaultProxyRetriever();

    private MethodRecallerFactory recallerFactory = new MethodRecallerFactory(proxyRetriever);

    private MemoryAccumulator accumulator = new DefaultMemoryAccumulator();


    private InterfaceProxyBuilder interfaceProxyBuilder = new InterfaceProxyBuilder(accumulator);


    private final DefaultResolver defaultBuilder = DefaultResolverChain.use(new DefaultObjectBuilder())
            .or(new ConstructorObjectBuilder(constructorResolver, nullBuilder))
            .or(interfaceProxyBuilder).or(nullBuilder);

    private CallbackBuilder callbackBuilder = new DefaultCallbackBuilder(this, defaultBuilder, recallerFactory);

    private RecallEnhancer recallEnhancer = new RecallEnhancer(constructorResolver, defaultBuilder);

    {
        interfaceProxyBuilder.setRecallEnhancer(recallEnhancer);
        interfaceProxyBuilder.setCallbackBuilder(callbackBuilder);
    }

    public <T> T enhance(final T object) {
        ProxyContext<T> context = new ProxyContext<>(object, callbackBuilder.buildMemoriser(accumulator));
        ProxyHolder<T> holder = context.build(recallEnhancer, accumulator);
        return holder.getProxy();
    }

    public <T> T enhanceClass(final Class<T> clazz, final DefaultResolver defaultResolver) {
        return enhance(defaultResolver.generateDefaultFor(clazz));
    }

    public void playback() throws Throwable {
        accumulator.buildSnapshot().recall();
    }

}

