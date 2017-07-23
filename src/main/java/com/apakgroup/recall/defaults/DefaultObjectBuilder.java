package com.apakgroup.recall.defaults;

import com.google.common.base.Optional;

public class DefaultObjectBuilder implements DefaultOptionalResolver {

    @Override
    public <T> Optional<T> generateDefaultFor(final Class<T> clazz) {
        return Optional.fromNullable(generatePrimitiveOrNull(clazz));
    }

    private <T extends A, A> T generatePrimitiveOrNull(final Class<A> clazz) {
        if (clazz.equals(void.class)) {
            return null;
        } else if (clazz.equals(boolean.class)) {
            return (T) new Boolean(true);
        } else if (clazz.equals(byte.class)) {
            return (T) new Byte((byte) 0);
        } else if (clazz.equals(short.class)) {
            return (T) new Short((short) 0);
        } else if (clazz.equals(int.class)) {
            return (T) new Integer(0);
        } else if (clazz.equals(long.class)) {
            return (T) new Long(0);
        } else if (clazz.equals(float.class)) {
            return (T) new Float(0);
        } else if (clazz.equals(double.class)) {
            return (T) new Double(0);
        } else {
            return null;
        }
    }


}

