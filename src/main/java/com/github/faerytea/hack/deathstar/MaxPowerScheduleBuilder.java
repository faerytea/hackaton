package com.github.faerytea.hack.deathstar;

import com.github.faerytea.hack.deathstar.entities.Schedule;
import com.github.faerytea.hack.deathstar.entities.Weapon;
import com.github.faerytea.hack.deathstar.entities.Worker;
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

    public void buildSchedule(long totalCost) {
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
        buildWeapon.forEach(weapon -> System.out.println(weapon.getName()));
    }

    private Map<Weapon, Long> getCostsMap() {
        Map<Weapon, Long> costs = new HashMap<>();
        weapons.forEach(weapon -> {
            Schedule schedule = MinCostScheduleBuilder.buildFor(weapon);
            long cost = schedule.getTotalCost();
            costs.put(weapon, cost);
        });
        return costs;
    }
}