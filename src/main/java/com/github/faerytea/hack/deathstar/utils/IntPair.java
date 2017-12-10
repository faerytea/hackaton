package com.github.faerytea.hack.deathstar.utils;

import lombok.Data;

@Data
public class IntPair<T> {
    public static <T> IntPair<T> one(T object) {
        return new IntPair<>(object, 1);
    }

    public static <T> IntPair<T> some(T object, int num) {
        return new IntPair<>(object, num);
    }

    public final T object;
    public final int number;
}
