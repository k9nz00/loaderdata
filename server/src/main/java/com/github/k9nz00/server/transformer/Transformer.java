package com.github.k9nz00.server.transformer;

public interface Transformer<I, O> {
    O transform(I input);
}
