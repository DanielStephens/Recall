package com.apakgroup.recall.call;

import com.apakgroup.recall.memory.ProxyMemory;

public interface MethodRecaller<T> {

    void recall(ProxyMemory memory) throws Throwable;

    T proxy();

}

