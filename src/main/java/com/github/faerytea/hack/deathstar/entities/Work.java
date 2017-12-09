package com.github.faerytea.hack.deathstar.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Work {
    public final String name;
    public final long time;
    public final long cost;
    public final int amount;

    public Work(String name, long time, long cost) {
        this(name, time, cost, 1);
    }
}
