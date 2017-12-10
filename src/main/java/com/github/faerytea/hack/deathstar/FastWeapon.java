package com.github.faerytea.hack.deathstar;

import com.github.faerytea.hack.deathstar.entities.Schedule;
import com.github.faerytea.hack.deathstar.entities.Worker;
import lombok.Getter;
import lombok.val;

import java.util.List;

public class FastWeapon {
    public FastWeapon(int reqSwords, int reqDroids, int reqBlasters) {
        this.reqSwords = reqSwords;
        this.reqDroids = reqDroids;
        this.reqBlasters = reqBlasters;
    }

    public static int power(int swords, int droids, int blasters) {
        return swords * 7 + droids + blasters * 2;
    }

    private final Worker optic = Main.tasksToWorkers.get("Выращивание кристалла").get(0);
    private final Worker constructor;
    private final Worker shiva;
    private final Worker troops;
    private final Worker advTroops;
    private final Worker dynamo;
    private final Worker accumulator;
    private final int reqSwords;
    private final int reqDroids;
    private final int reqBlasters;
    {
        List<Worker> constructors = Main.tasksToWorkers.get("Сборка дроида");
        Worker w1 = constructors.get(0);
        Worker w2 = constructors.get(1);
        if (w1.getName().equals("Дроиды-сборщики")) {
            constructor = w1;
            shiva = w2;
        } else {
            constructor = w2;
            shiva = w1;
        }
        List<Worker> ts = Main.tasksToWorkers.get("Пристрелка");
        Worker t1 = ts.get(0);
        Worker t2 = ts.get(1);
        if (t1.getName().equals("Отряд штурмовиков")) {
            troops = t1;
            advTroops = t2;
        } else {
            troops = t2;
            advTroops = t1;
        }
        List<Worker> acs = Main.tasksToWorkers.get("Заряд батарей бластера");
        Worker a1 = acs.get(0);
        Worker a2 = acs.get(1);
        if (a1.getName().equals("Динамо-машина")) {
            dynamo = a1;
            accumulator = a2;
        } else {
            dynamo = a2;
            accumulator = a1;
        }
    }
    @Getter
    private Schedule schedule = new Schedule();
    private int opticsTime = 1;
    private int constructorTime = 1;
    private int shivaTime = 1;
    private int troopsTime = 1;
    private int advTroopsTime = 1;
    private int minerTime = 1;

    public Schedule compute() {
        // send optics to swords
        for (int i = 0; i < reqSwords; ++i) {
            schedule.addEvent("Выращивание кристалла", optic, opticsTime);
            opticsTime += 25;
        }
        ++opticsTime;
        for (int i = 0; i < reqSwords; ++i) {
            schedule.addEvent("Огранка кристалла", optic, opticsTime);
            opticsTime += 40;
        }
        val swordOptics = opticsTime;
        // ... and then to blasters
        ++opticsTime;
        for (int i = 0; i < reqBlasters; ++i) {
            schedule.addEvent("Огранка рубина", optic, opticsTime);
            opticsTime += 5;
        }
        ++opticsTime;
        for (int i = 0; i < reqBlasters; ++i) {
            schedule.addEvent("Изготовление системы зеркал", optic, opticsTime);
            opticsTime += 10;
        }
        // send miner to mines
        {
            final Worker miner = Main.tasksToWorkers.get("Добыча свинца").get(0);
            for (int i = 0; i < reqSwords; ++i) {
                schedule.addEvent("Добыча свинца", miner, minerTime);
                minerTime += 4;
            }
            ++minerTime;
            for (int i = 0; i < reqSwords; ++i) {
                schedule.addEvent("Добыча платины", miner, minerTime);
                minerTime += 6;
            }
        }
        // send troops to swords
        troopsTime = minerTime + 4; // +1 warm up adv, +2 wait, +1 warm up
        advTroopsTime = minerTime + 1;
        for (int i = 0; i < reqSwords; ++i) {
            schedule.addEvent("Изготовление металлических пластин", advTroops, advTroopsTime);
            schedule.addEvent("Наполнение электролитом", troops, troopsTime);
            advTroopsTime += 2;
            troopsTime += 2;
        }
        val swordsReady = Math.max(troopsTime, swordOptics);
        // send constructor to bodies
        for (int i = 0; i < reqDroids; ++i) {
            schedule.addEvent("Сборка туловища", constructor, constructorTime);
            constructorTime += 3;
        }
        // send shiva to blasters
        for (int i = 0; i < reqBlasters; i += 5) {
            schedule.addEvent("Сборка корпуса бластера", shiva, shivaTime);
            ++shivaTime;
        }
        // send shiva to droids
        // // // very complex shit
        int swordsConstructorDone = -1;
        int blasterReady = -1;
        ++shivaTime;
        for (int i = 0; i < reqDroids * 2; i += 10) {
            schedule.addEvent("Сборка ноги", shiva, shivaTime);
            shivaTime += 10;
        }
        assert !(opticsTime <= constructorTime && shivaTime > constructorTime);
        blasterReady = checkBlasters(blasterReady);
        swordsConstructorDone = checkSwords(swordsConstructorDone);
        ++shivaTime;
        for (int i = 0; i < reqDroids; i += 8) {
            schedule.addEvent("Вставка компьютера", shiva, shivaTime);
            shivaTime += 2;
        }
        blasterReady = checkBlasters(blasterReady);
        swordsConstructorDone = checkSwords(swordsConstructorDone);
        ++shivaTime;
        for (int i = 0; i < reqDroids * 2; i += 3) {
            schedule.addEvent("Сборка руки", shiva, shivaTime);
            shivaTime += 5;
        }
        blasterReady = checkBlasters(blasterReady);
        swordsConstructorDone = checkSwords(swordsConstructorDone);
        ++shivaTime;
        for (int i = 0; i < reqDroids; i += 3) {
            schedule.addEvent("Сборка головы", shiva, shivaTime);
            shivaTime += 15;
        }
        assert shivaTime > constructorTime;
        ++shivaTime;
        for (int i = 0; i < reqDroids; ++i) {
            schedule.addEvent("Сборка дроида", shiva, shivaTime);
            shivaTime += 2;
        }
        if (swordsConstructorDone == -1) {
            assert shivaTime < swordsReady;
            shivaTime = swordsReady - 1;
            swordsConstructorDone = checkSwords(swordsConstructorDone);
        }
        shivaTime = Math.max(shivaTime, opticsTime);
        blasterReady = checkBlasters(blasterReady);
        assert blasterReady > 0;
        assert swordsConstructorDone > 0;
        // so, droids done.
        // what about blasters & swords?
        // our choice is — BLASTERS
        int blasterStartEarly = Math.max(advTroopsTime, blasterReady);
        boolean swordsFinished = reqSwords == 0;
        if (blasterStartEarly > swordsReady && !swordsFinished) {
            advTroopsTime += 2;
            schedule.addEvent("Полировка рукояти меча", advTroops, advTroopsTime - 1);
            blasterStartEarly = Math.max(advTroopsTime, blasterReady);
            int accTime = advTroopsTime + 1;
            for (int i = 0; i < reqSwords; ++i) {
                schedule.addEvent("Заряд плазменных батарей", dynamo, accTime);
                ++accTime;
            }
            swordsFinished = true;
        }
        advTroopsTime = blasterStartEarly + 1;
        int breaking = -1;
        for (int i = 0; i < reqBlasters; ++i) {
            schedule.addEvent("Установка лазера бластера в корпус", advTroops, advTroopsTime);
            ++advTroopsTime;
            if (!swordsFinished && swordsReady <= advTroopsTime) {
                breaking = advTroopsTime;
                advTroopsTime += 2;
                schedule.addEvent("Полировка рукояти меча", advTroops, advTroopsTime - 1);
                int accTime = advTroopsTime + 1;
                for (int j = 0; j < reqSwords; ++j) {
                    schedule.addEvent("Заряд плазменных батарей", dynamo, accTime);
                    ++accTime;
                }
                swordsFinished = true;
            }
        }
        constructorTime = Math.max(blasterStartEarly + 2, constructorTime) + 1;
        val cct = constructorTime;
        for (int i = 0; i < reqBlasters; ++i) {
            schedule.addEvent("Полировка рукояти бластера", constructor, constructorTime);
            ++constructorTime;
            if (constructorTime == breaking + 1) constructorTime += 4;
        }
        int accTime = cct + 2;
        val at = accTime;
        for (int i = 0; i < reqBlasters; ++i) {
            schedule.addEvent("Заряд батарей бластера", accumulator, accTime);
            accTime += 3;
        }
        troopsTime = at + 4;
        for (int i = 0; i < reqBlasters; ++i) {
            schedule.addEvent("Пристрелка", troops, troopsTime);
            troopsTime += 3;
        }
        return schedule;
    }

    private int checkSwords(int swordsReady) {
        if (shivaTime >= swordsReady && swordsReady == -1) {
            ++shivaTime;
            for (int i = 0; i < reqSwords; i += 6) {
                schedule.addEvent("Сборка меча", shiva, shivaTime);
                shivaTime += 20;
            }
            return shivaTime;
        }
        return swordsReady;
    }

    private int checkBlasters(int blaster) {
        if (shivaTime >= opticsTime && blaster == -1) {
            ++shivaTime;
            for (int i = 0; i < reqBlasters; i += 10) {
                schedule.addEvent("Сборка лазера", shiva, shivaTime);
                shivaTime += 8;
            }
            return shivaTime;
        }
        return blaster;
    }
}
