package com.github.faerytea.hack.deathstar.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode
@ToString
public class Worker {
    private final String name;
    private Map<String, Work> works = new HashMap<>();

    public Worker (String name, List<Work> workList) {
        this.name = name;
        workList.forEach(work -> works.put(work.getName(), work));
    }
}