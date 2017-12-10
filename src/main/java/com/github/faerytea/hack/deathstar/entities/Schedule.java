package com.github.faerytea.hack.deathstar.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ksenia on 09.12.17.
 */
@Getter
public class Schedule {
    private List<ScheduleEvent> events = new ArrayList<>();

    public void addEvent(String workName, Worker worker, long startTime) {
        Work work = worker.getWorks().get(workName);
        events.add(new ScheduleEvent(work, worker, startTime));
    }

    public void print() {
        events.forEach(event -> System.out.println(event.startTime + "\t" +
                event.getWorker().getName() +
                " " +
                event.getWork().getName()));
    }

    //Без учета переключений!!!
    public long getTotalTime() {
        return events.stream()
                .mapToLong(event -> event.getWork().getTime())
                .sum();
    }

    public long getTotalTimeWithWarmUps() {
        long currentTime = 0;
        val finish = new HashMap<Worker, Long>();
        for (val e : events) {
            long prevTime = finish.computeIfAbsent(e.worker, k -> 0L);

        }
        throw new UnsupportedOperationException();
    }

    public long getTotalCost() {
        return events.stream()
                .mapToLong(event -> event.getWork().getCost())
                .sum();
    }

    @Getter
    @AllArgsConstructor
    public class ScheduleEvent {
        private final Work work;
        private final Worker worker;
        private final long startTime;
    }
}