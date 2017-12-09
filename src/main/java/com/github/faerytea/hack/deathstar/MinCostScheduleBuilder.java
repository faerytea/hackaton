package com.github.faerytea.hack.deathstar;

import com.github.faerytea.hack.deathstar.entities.Product;
import com.github.faerytea.hack.deathstar.entities.Schedule;
import com.github.faerytea.hack.deathstar.entities.Worker;
import lombok.Getter;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by ksenia on 09.12.17.
 */
@Getter
public class MinCostScheduleBuilder {
    private final Collection<Worker> workers;
    private Schedule schedule;


    public MinCostScheduleBuilder(Collection<Worker> workers) {
        this.workers = workers;
    }

    public void newSchedule() {
        schedule = new Schedule();
    }

    public void addProduct(Product product) {
        product.dependencies.forEach(d -> addProduct(d));
        product.getTaskNames().forEach(taskName -> {
            Worker worker = getMinCostWorker(taskName);
            schedule.addEvent(taskName, worker);
        });
    }

    private Worker getMinCostWorker(String taskName) {
        return workers.stream()
                .filter(worker -> worker.getWorks().containsKey(taskName))
                .collect(Collectors.maxBy((w1, w2) -> {
                    long t1 = w1.getWorks().get(taskName).getTime();
                    long t2 = w2.getWorks().get(taskName).getTime();
                    return (int) (t1 - t2);
                })).get();
    }
}