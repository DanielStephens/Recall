package com.apakgroup.recall.callback;

import com.apakgroup.recall.memory.MemoryAccumulator;

public interface CallbackBuilder {

    MemoriserCallback buildMemoriser(MemoryAccumulator accumulator);

}

