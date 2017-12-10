package com.github.faerytea.hack.deathstar;

import com.github.faerytea.hack.deathstar.entities.Product;
import com.github.faerytea.hack.deathstar.entities.Schedule;
import com.github.faerytea.hack.deathstar.entities.Worker;
import com.github.faerytea.hack.deathstar.utils.IntPair;
import lombok.Getter;
import lombok.val;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ksenia on 09.12.17.
 */
@Getter
public class MinCostScheduleBuilder {
    private Schedule schedule = new Schedule();
    private Map<String, Integer> works;
    private Map<String, Integer> overkill;
    long time = 0;

    public Schedule build(Product product) {
        works = new HashMap<>();
        overkill = new HashMap<>();
        collectWorks(product);
        buildSchedule(product);
        return schedule;
    }


    private void collectWorks(Product product) {
        product.taskNames.forEach(s -> works.compute(s, (k, v) -> v == null ? 1 : v + 1));
        for (val e : product.dependencies)
            collectWorks(e);
    }

    private void buildSchedule(Product product) {
        for (val e : product.dependencies)
            buildSchedule(e);
        for (val e : product.taskNames) {
            int done = overkill.getOrDefault(e, 0);
            if (done == 0) {
                int must = works.get(e);
                fillWithMinCostWorker(e, must);
                overkill.put(e, must); // once per e
            } else {
                overkill.put(e, --done);
            }
        }
    }

    private void fillWithMinCostWorker(String task, int amount) {
        val wss = Main.tasksToWorkers.get(task);
        time += 1; // warm up
        switch (wss.size()) {
            case 1: {
                final Worker chosenOne = wss.get(0);
                val work = chosenOne.getWorks().get(task);
                while (amount > 0) {
                    schedule.addEvent(task, chosenOne, time);
                    time += work.time;
                    amount -= work.amount;
                }
                break;
            }
            case 2: {
                // is this correct?
                val firstW = wss.get(0);
                val secondW = wss.get(1);
                val fst = firstW.getWorks().get(task);
                val snd = secondW.getWorks().get(task);
                int bestX = -1, bestY = -1; // keep compiler happy
                long bestCost = Long.MAX_VALUE;
                for (int i = 0; i < amount / fst.amount + 2; ++i) { // +1 for sure
                    int j = 0;
                    for (; i * fst.amount + j * snd.amount < amount; ++j);
                    long cc = i * fst.cost + j * snd.cost;
                    if (cc < bestCost) {
                        bestCost = cc;
                        bestX = i;
                        bestY = j;
                    }
                }
                for (int i = 0; i < bestX; ++i) {
                    schedule.addEvent(task, firstW, time);
                    time += fst.time;
                }
                ++time; // second warm up
                for (int i = 0; i < bestY; ++i) {
                    schedule.addEvent(task, secondW, time);
                    time += snd.time;
                }
                break;
            }
            default:
                System.err.println("what a terrible failure...");
                System.err.println(wss);
        }
    }
}