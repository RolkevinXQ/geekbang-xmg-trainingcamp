package com.rolkevin.cache.codec;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class StringSerializer implements GenericSerializer<String> {

    Charset charset = Charset.forName("UTF-8");

    @Override
    public String decode(ByteBuffer byteBuffer) throws IllegalArgumentException, NullPointerException {
        return charset.decode(byteBuffer).toString();
    }

    @Override
    public ByteBuffer encode(String s) throws IllegalArgumentException, NullPointerException {
        return ByteBuffer.wrap(s.getBytes());
    }
}
