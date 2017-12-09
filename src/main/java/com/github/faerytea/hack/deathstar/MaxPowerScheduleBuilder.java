package com.github.faerytea.hack.deathstar;

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
    private final MinCostScheduleBuilder minCostBuilder;
    private final List<Weapon> buildWeapon = new ArrayList<>();

    public MaxPowerScheduleBuilder(Collection<Weapon> weapons, Collection<Worker> workers) {
        this.weapons = weapons;
        minCostBuilder = new MinCostScheduleBuilder(workers);
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

        minCostBuilder.newSchedule();
        for (Weapon weapon : sortedWeapons) {
            long cost = costs.get(weapon);
            while (totalCost >= cost) {
                totalCost -= cost;
                buildWeapon.add(weapon);
                minCostBuilder.addProduct(weapon);
            }
        }
        buildWeapon.forEach(weapon -> System.out.println(weapon.getName()));
        //weapons.forEach(weapon -> System.out.println(weapon.getName() + " " + costs.get(weapon)));
        System.out.println("total cost: " + minCostBuilder.getSchedule().getTotalCost());
    }

    private Map<Weapon, Long> getCostsMap() {
        Map<Weapon, Long> costs = new HashMap<>();
        weapons.forEach(weapon -> {
            minCostBuilder.newSchedule();
            minCostBuilder.addProduct(weapon);
            long cost = minCostBuilder.getSchedule().getTotalCost();
            costs.put(weapon, cost);
        });
        return costs;
    }
}