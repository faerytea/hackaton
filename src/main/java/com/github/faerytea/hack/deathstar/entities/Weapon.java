package com.github.faerytea.hack.deathstar.entities;

import lombok.Getter;

import java.util.List;

/**
 * Created by ksenia on 09.12.17.
 */
@Getter
public class Weapon extends Product {
    private final int power;

    public Weapon(String name,
                  List<String> taskNames,
                  List<Product> dependencies,
                  int power) {
        super(name, taskNames, dependencies);
        this.power = power;
    }
}