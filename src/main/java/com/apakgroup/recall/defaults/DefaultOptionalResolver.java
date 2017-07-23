package com.apakgroup.recall.defaults;

import com.google.common.base.Optional;

public interface DefaultOptionalResolver {

    <T> Optional<T> generateDefaultFor(Class<T> clazz);

}

