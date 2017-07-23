package com.apakgroup.recall.retrieval;

import com.apakgroup.recall.memory.ProxyMemory;

public interface ProxyRetriever {

    <T> T iterativelyRetrieveRealValue(ProxyMemory memory, T object);

    Object[] iterativelyRetrieveRealValues(ProxyMemory memory, Object[] objects);

}

