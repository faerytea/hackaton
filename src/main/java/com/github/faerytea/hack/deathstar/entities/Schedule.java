package com.github.faerytea.hack.deathstar.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksenia on 09.12.17.
 */
@Getter
public class Schedule {
    private List<ScheduleEvent> events = new ArrayList<>();

    public void addEvent(String workName, Worker worker) {
        Work work = worker.getWorks().get(workName);
        events.add(new ScheduleEvent(work, worker));
    }

    public void print() {
        events.forEach(event -> System.out.println(
                        event.getWorker().getName() +
                        " " +
                        event.getWork().getName()));
    }

    public long getTotalTime() {
        return events.stream()
                .mapToLong(event -> event.getWork().getTime())
                .sum();
    }

    public long getTotalCost() {
        return events.stream()
                .mapToLong(event -> event.getWork().getCost())
                .sum();
    }

    @Getter
    @AllArgsConstructor
    public class ScheduleEvent {
        private Work work;
        private Worker worker;
    }
}