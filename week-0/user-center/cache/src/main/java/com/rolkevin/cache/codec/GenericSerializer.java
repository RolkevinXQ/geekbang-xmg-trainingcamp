package com.rolkevin.cache.codec;

import java.nio.ByteBuffer;

public interface GenericSerializer<T> {

    T decode(ByteBuffer byteBuffer) throws IllegalArgumentException, NullPointerException;;

    ByteBuffer encode(T t) throws IllegalArgumentException, NullPointerException;;
}
