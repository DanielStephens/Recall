package com.apakgroup.recall.memory;

import com.apakgroup.recall.call.MethodRecaller;

public interface MemoryAccumulator {
    
    void append(MethodRecaller<?> callback);

    <T> void assign(T proxyAnswer, T realAnswer);

    Recaller buildSnapshot();

}

