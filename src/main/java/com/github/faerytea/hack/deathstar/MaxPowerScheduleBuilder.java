package com.github.faerytea.hack.deathstar;

import com.github.faerytea.hack.deathstar.entities.Schedule;
import com.github.faerytea.hack.deathstar.entities.Weapon;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ksenia on 09.12.17.
 */
@Getter
public class MaxPowerScheduleBuilder {
    private final Collection<Weapon> weapons;
    private final List<Weapon> buildWeapon = new ArrayList<>();

   public MaxPowerScheduleBuilder(Collection<Weapon> weapons) {
        this.weapons = weapons;
    }

    public Schedule build(long totalCost) {
        Map<Weapon, Long> costs = getCostsMap();
        List<Weapon> sortedWeapons = weapons.stream().sorted((w1, w2) -> {
            Double eff1 = 0.0 + costs.get(w1) / w1.getPower();
            Double eff2 = 0.0  + costs.get(w2) / w2.getPower();
            if (eff1 < eff2) {
                return -1;
            }
            return 1;
        }).collect(Collectors.toList());

        for (Weapon weapon : sortedWeapons) {
            long cost = costs.get(weapon);
            while (totalCost >= cost) {
                totalCost -= cost;
                buildWeapon.add(weapon);
            }
        }
        MinCostScheduleBuilder minCostScheduleBuilder = new MinCostScheduleBuilder();
        buildWeapon.forEach(weapon -> minCostScheduleBuilder.build(weapon));
        return minCostScheduleBuilder.getSchedule();
    }

    private Map<Weapon, Long> getCostsMap() {
        Map<Weapon, Long> costs = new HashMap<>();
        weapons.forEach(weapon -> {
            Schedule schedule = new MinCostScheduleBuilder().build(weapon);
            long cost = schedule.getTotalCost();
            costs.put(weapon, cost);
        });
        return costs;
    }
}