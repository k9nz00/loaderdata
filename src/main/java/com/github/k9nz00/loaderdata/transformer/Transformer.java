package com.github.k9nz00.loaderdata.transformer;

public interface Transformer<I, O> {
    O transform(I input);
}
