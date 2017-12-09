package com.github.faerytea.hack.deathstar.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {
    public final String name;
    public final List<Work> tasks;
    public final List<Product> dependencies;
}
