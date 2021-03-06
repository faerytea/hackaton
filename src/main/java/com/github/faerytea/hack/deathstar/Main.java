package com.github.faerytea.hack.deathstar;

import com.github.faerytea.hack.deathstar.entities.*;
import lombok.val;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class Main {
    public static final Set<Worker> workers = new HashSet<>();
    public static final Map<String, List<Worker>> tasksToWorkers = new HashMap<>();
    public static final Weapon deathStar;
    public static final Weapon blaster;
    public static final Weapon droid;
    public static final Weapon lightsabre;

    static {
        // troops
        {
            ArrayList<Work> troopsLabors = new ArrayList<>(9);
            ArrayList<Work> advancedTroopsLabors = new ArrayList<>(9);
            troopsLabors.add(new Work("Пристрелка", 1, 150));
            advancedTroopsLabors.add(new Work("Пристрелка", 2, 50));
            troopsLabors.add(new Work("Установка лазера бластера в корпус", 2, 25));
            advancedTroopsLabors.add(new Work("Установка лазера бластера в корпус", 1, 40));
            troopsLabors.add(new Work("Полировка рукояти меча", 1, 15));
            advancedTroopsLabors.add(new Work("Полировка рукояти меча", 1, 300, 40));
            troopsLabors.add(new Work("Сгибание проволоки катушки", 1, 20));
            advancedTroopsLabors.add(new Work("Сгибание проволоки катушки", 1, 120, 5));
            troopsLabors.add(new Work("Сгибание проволоки соленоида", 2, 35));
            advancedTroopsLabors.add(new Work("Сгибание проволоки соленоида", 1, 200, 7));
            troopsLabors.add(new Work("Сгибание проволоки корректирующей катушки", 1, 20));
            advancedTroopsLabors.add(new Work("Сгибание проволоки корректирующей катушки", 1, 180, 4));
            troopsLabors.add(new Work("Откачка воздуха", 25, 50));
            advancedTroopsLabors.add(new Work("Откачка воздуха", 15, 150));
            troopsLabors.add(new Work("Изготовление металлических пластин", 3, 50));
            advancedTroopsLabors.add(new Work("Изготовление металлических пластин", 2, 70));
            troopsLabors.add(new Work("Наполнение электролитом", 2, 100));
            advancedTroopsLabors.add(new Work("Наполнение электролитом", 1, 150));
            final Worker troops = new Worker("Отряд штурмовиков", troopsLabors);
            final Worker advancedTroops = new Worker("Отряд продвинутых штурмовиков", advancedTroopsLabors);
            workers.add(troops);
            workers.add(advancedTroops);
            for (Work work : troopsLabors) {
                tasksToWorkers.put(work.name, asList(troops, advancedTroops));
            }
        }
        // accums
        {
            ArrayList<Work> atomicLabors = new ArrayList<>(2);
            ArrayList<Work> dynamoLabors = new ArrayList<>(2);
            atomicLabors.add(new Work("Заряд батарей бластера", 3, 40));
            dynamoLabors.add(new Work("Заряд батарей бластера", 5, 30));
            atomicLabors.add(new Work("Заряд плазменных батарей", 4, 50));
            dynamoLabors.add(new Work("Заряд плазменных батарей", 1, 500));
            final Worker atomic = new Worker("Атомный аккумулятор", atomicLabors);
            final Worker dynamo = new Worker("Динамо-машина", dynamoLabors);
            workers.add(atomic);
            workers.add(dynamo);
            for (Work work : atomicLabors) {
                tasksToWorkers.put(work.name, asList(atomic, dynamo));
            }
        }
        // constructors
        {
            ArrayList<Work> droidLabors = new ArrayList<>(13);
            ArrayList<Work> shivaLabors = new ArrayList<>(13);
            droidLabors.add(new Work("Сборка корпуса бластера", 1, 40));
            shivaLabors.add(new Work("Сборка корпуса бластера", 1, 200, 5));
            droidLabors.add(new Work("Полировка рукояти бластера", 1, 20));
            shivaLabors.add(new Work("Полировка рукояти бластера", 1, 300, 10));
            droidLabors.add(new Work("Сборка лазера", 4, 40));
            shivaLabors.add(new Work("Сборка лазера", 8, 1500, 10));
            droidLabors.add(new Work("Сборка суперлазера", 100, 4000));
            shivaLabors.add(new Work("Сборка суперлазера", 40, 8000));
            droidLabors.add(new Work("Сборка тяжёлого лазера", 500, 5000));
            shivaLabors.add(new Work("Сборка тяжёлого лазера", 250, 10000));
            droidLabors.add(new Work("Сборка меча", 50, 50));
            shivaLabors.add(new Work("Сборка меча", 20, 600, 6));
            droidLabors.add(new Work("Сборка головы", 20, 60));
            shivaLabors.add(new Work("Сборка головы", 15, 280, 3));
            droidLabors.add(new Work("Сборка ноги", 20, 60));
            shivaLabors.add(new Work("Сборка ноги", 10, 1200, 10));
            droidLabors.add(new Work("Сборка руки", 10, 40));
            shivaLabors.add(new Work("Сборка руки", 5, 80, 3));
            droidLabors.add(new Work("Вставка компьютера", 5, 15));
            shivaLabors.add(new Work("Вставка компьютера", 2, 150, 8));
            droidLabors.add(new Work("Сборка дроида", 5, 10));
            shivaLabors.add(new Work("Сборка дроида", 2, 15));
            droidLabors.add(new Work("Сборка туловища", 3, 10));
            shivaLabors.add(new Work("Сборка туловища", 2, 20));
            droidLabors.add(new Work("Сборка реактора", 1000, 10000));
            shivaLabors.add(new Work("Сборка реактора", 500, 22000));
            final Worker droid = new Worker("Дроиды-сборщики", droidLabors);
            final Worker shiva = new Worker("Автоматическая сборочная линия Ши Ва", shivaLabors);
            workers.add(droid);
            workers.add(shiva);
            for (Work work : droidLabors) {
                tasksToWorkers.put(work.name, asList(droid, shiva));
            }
        }
        // installers
        {
            val sithhLabors = new ArrayList<Work>();
            val droidLabors = new ArrayList<Work>();
            sithhLabors.add(new Work("Монтаж суперлазера", 3, 1500));
            droidLabors.add(new Work("Монтаж суперлазера", 30, 90));
            sithhLabors.add(new Work("Монтаж тяжёлого лазера", 3, 1000));
            droidLabors.add(new Work("Монтаж тяжёлого лазера", 30, 90));
            sithhLabors.add(new Work("Монтаж реактора", 5, 1500));
            droidLabors.add(new Work("Монтаж реактора", 40, 150));
            sithhLabors.add(new Work("Вставка сердечника катушки", 3, 50));
            droidLabors.add(new Work("Вставка сердечника катушки", 4, 40));
            sithhLabors.add(new Work("Вставка сердечника соленоида", 3, 50));
            droidLabors.add(new Work("Вставка сердечника соленоида", 4, 40));
            sithhLabors.add(new Work("Вставка сердечника корректирующей катушки", 3, 50));
            droidLabors.add(new Work("Вставка сердечника корректирующей катушки", 4, 40));
            sithhLabors.add(new Work("Монтаж кассет", 4, 35));
            droidLabors.add(new Work("Монтаж кассет", 2, 25));
            sithhLabors.add(new Work("Изготовление холодильника", 3, 500));
            droidLabors.add(new Work("Изготовление холодильника", 2, 800));
            sithhLabors.add(new Work("Установка катушек и соленоида", 5, 200));
            droidLabors.add(new Work("Установка катушек и соленоида", 4, 300, 2));
            sithhLabors.add(new Work("Установка корректирующих катушек", 3, 600));
            droidLabors.add(new Work("Установка корректирующих катушек", 4, 700, 2));
            sithhLabors.add(new Work("Сборка криостата", 5, 1800));
            droidLabors.add(new Work("Сборка криостата", 6, 3000, 2));
            final Worker droid = new Worker("Отряд дроидов-монтажников", droidLabors);
            final Worker sithh = new Worker("Ситх-монтажник", sithhLabors);
            workers.add(droid);
            workers.add(sithh);
            for (Work work : droidLabors) {
                tasksToWorkers.put(work.name, asList(droid, sithh));
            }
        }
        // builders
        {
            val sithBuilders = new ArrayList<Work>();
            val droidBuilders = new ArrayList<Work>();
            sithBuilders.add(new Work("Соединение биозащитных оболочек", 1000, 800));
            sithBuilders.add(new Work("Склейка двух наносвинцовых покрытий", 15, 40));
            sithBuilders.add(new Work("Склейка двух пар наносвинцовых покрытий", 20, 45));
            sithBuilders.add(new Work("Конструирование камеры", 500, 2500));
            sithBuilders.add(new Work("Конструирование нейтронной ловушки", 300, 500));
            sithBuilders.add(new Work("Изготовление кассеты", 300, 1500));
            droidBuilders.add(new Work("Соединение биозащитных оболочек", 5000, 300));
            droidBuilders.add(new Work("Склейка двух наносвинцовых покрытий", 20, 30));
            droidBuilders.add(new Work("Склейка двух пар наносвинцовых покрытий", 25, 40));
            droidBuilders.add(new Work("Конструирование камеры", 450, 3000));
            droidBuilders.add(new Work("Конструирование нейтронной ловушки", 200, 600));
            droidBuilders.add(new Work("Изготовление кассеты", 200, 1800));
            final Worker sithh = new Worker("Ситх-строитель", sithBuilders);
            final Worker droid = new Worker("Отряд дроидов-строителей", droidBuilders);
            workers.add(droid);
            workers.add(sithh);
            for (Work work : droidBuilders) {
                tasksToWorkers.put(work.name, asList(droid, sithh));
            }
        }
        // optics
        {
            val itMo = new ArrayList<Work>();
            itMo.add(new Work("Огранка рубина", 5, 150));
            itMo.add(new Work("Изготовление системы зеркал", 10, 120));
            itMo.add(new Work("Огранка здорового рубина", 5000, 150000));
            itMo.add(new Work("Изготовление системы здоровых зеркал", 4000, 160000));
            itMo.add(new Work("Изготовление системы тяжёлых зеркал", 1000, 16000));
            itMo.add(new Work("Выращивание кристалла", 25, 200));
            itMo.add(new Work("Огранка кристалла", 40, 300));
            itMo.add(new Work("Огранка тяжёлого рубина", 6000, 140000));
            final Worker itMoOptic = new Worker("Робот-оптик \"Ит Мо\"", itMo);
            workers.add(itMoOptic);
            for (Work work : itMo) {
                tasksToWorkers.put(work.name, singletonList(itMoOptic));
            }

        }
        // haulers
        {
            val haulersSquad = new ArrayList<Work>();
            haulersSquad.add(new Work("Перетаскивание суперлазера", 100, 5000));
            haulersSquad.add(new Work("Перетаскивание тяжёлого лазера", 100, 6000));
            haulersSquad.add(new Work("Перетаскивание реактора", 150, 9000));
            haulersSquad.add(new Work("Перетаскивание корпуса", 200, 12000));
            final Worker hauler = new Worker("Отряд грузчиков", haulersSquad);
            workers.add(hauler);
            for (Work work : haulersSquad) {
                tasksToWorkers.put(work.name, singletonList(hauler));
            }
        }
        // metalfuckers
        {
            val metallurgists = new ArrayList<Work>();
            metallurgists.add(new Work("Раскатка слитка", 3, 200));
            metallurgists.add(new Work("Добыча свинца", 4, 250));
            metallurgists.add(new Work("Добыча платины", 6, 300));
            metallurgists.add(new Work("Волочение платины", 4, 150));
            metallurgists.add(new Work("Добыча бериллия", 5, 500));
            final Worker metalWorker = new Worker("Дроид-металлург", metallurgists);
            workers.add(metalWorker);
            for (Work work : metallurgists) {
                tasksToWorkers.put(work.name, singletonList(metalWorker));
            }
        }
        // production
        {
            val pl = new Product("Платина", singletonList("Добыча платины"), Collections.emptyList());
            val pb = new Product("Слиток свинца", singletonList("Добыча свинца"), Collections.emptyList());
            // droid
            {
                val hand = new Product("Рука", singletonList("Сборка руки"), Collections.emptyList());
                val body = new Product("Туловище", singletonList("Сборка туловища"), Collections.emptyList());
                val leg = new Product("Нога", singletonList("Сборка ноги"), Collections.emptyList());
                val head = new Product("Голова", asList("Сборка головы", "Вставка компьютера"), Collections.emptyList());
                droid = new Weapon(
                        "Боевой дроид",
                        singletonList("Сборка дроида"),
                        asList(hand, hand, body, leg, leg, head),
                        1);
            }
            // lightsabre
            {
                val cell = new Product(
                        "Энергоячейка",
                        asList("Изготовление металлических пластин", "Наполнение электролитом"),
                        asList(pl, pb));
                val crystal = new Product(
                        "Кристалл",
                        asList("Выращивание кристалла", "Огранка кристалла"),
                        Collections.emptyList());
                lightsabre = new Weapon(
                        "Световой меч",
                        asList("Сборка меча", "Полировка рукояти меча", "Заряд плазменных батарей"),
                        asList(cell, crystal),
                        7);
            }
            // blaster
            {
                val body = new Product(
                        "Корпус",
                        singletonList("Сборка корпуса бластера"),
                        Collections.emptyList());
                val laser = new Product(
                        "Лазер",
                        asList("Огранка рубина", "Изготовление системы зеркал", "Сборка лазера"),
                        Collections.emptyList());
                blaster = new Weapon(
                        "Бластер",
                        asList(
                                "Установка лазера бластера в корпус",
                                "Полировка рукояти бластера",
                                "Заряд батарей бластера",
                                "Пристрелка"),
                        asList(body, laser),
                        2);
            }
            // death star
            {
                val nanoPb = new Product(
                        "Наносвинцовое покрытие",
                        singletonList("Раскатка слитка"),
                        asList(pb, pb));
                val threadPl = new Product(
                        "Платиновая проволока",
                        singletonList("Волочение платины"),
                        asList(pl, pl));
                val bioProtection = new Product(
                        "Биозащитная оболочка",
                        asList(
                                "Склейка двух наносвинцовых покрытий",
                                "Склейка двух наносвинцовых покрытий",
                                "Склейка двух пар наносвинцовых покрытий"),
                        asList(nanoPb, nanoPb, nanoPb, nanoPb));
                val body = new Product(
                        "Корпус станции",
                        singletonList("Соединение биозащитных оболочек"),
                        asList(bioProtection, bioProtection, bioProtection, bioProtection, bioProtection));
                val coil = new Product(
                        "Катушка",
                        asList("Сгибание проволоки катушки", "Вставка сердечника катушки"),
                        asList(threadPl, threadPl));
                val solenoid = new Product(
                        "Соленоид",
                        asList("Сгибание проволоки соленоида", "Вставка сердечника соленоида"),
                        singletonList(threadPl));
                val corCoil = new Product(
                        "Корректирующая катушка",
                        asList("Сгибание проволоки корректирующей катушки", "Вставка сердечника корректирующей катушки"),
                        singletonList(threadPl));
                val tokamak = new Product(
                        "Токамак",
                        asList("Установка катушек и соленоида", "Установка корректирующих катушек"),
                        asList(coil, solenoid, corCoil));
                val vacuum = new Product(
                        "Вакуумная камера",
                        asList("Конструирование камеры", "Откачка воздуха"),
                        emptyList());
                val be = new Product(
                        "Бериллий",
                        singletonList("Добыча бериллия"),
                        emptyList());
                val beTape = new Product(
                        "Бериллиевая кассета",
                        singletonList("Изготовление кассеты"),
                        asList(be, be, be));
                val blancet = new Product(
                        "Бланкет",
                        asList("Монтаж кассет", "Конструирование нейтронной ловушки"),
                        asList(beTape, beTape));
                val fridge = new Product(
                        "Холодильник",
                        singletonList("Изготовление холодильника"),
                        emptyList());
                val cryostat = new Product(
                        "Криостат",
                        singletonList("Сборка криостата"),
                        asList(bioProtection, fridge));
                val reactor = new Product(
                        "Реактор гиперматериального синтеза",
                        singletonList("Сборка реактора"),
                        asList(tokamak, vacuum, blancet, cryostat));
                val superLaser = new Product(
                        "Суперлазер",
                        asList("Огранка здорового рубина", "Изготовление системы здоровых зеркал", "Сборка суперлазера"),
                        emptyList());
                val heavyLaser = new Product(
                        "Тяжёлый лазер",
                        asList("Огранка тяжёлого рубина", "Изготовление системы тяжёлых зеркал", "Сборка тяжёлого лазера"),
                        emptyList());
                deathStar = new Weapon(
                        "Звезда Смерти",
                        asList(
                                "Перетаскивание корпуса",
                                "Перетаскивание суперлазера",
                                "Монтаж суперлазера",
                                "Перетаскивание тяжёлого лазера",
                                "Монтаж тяжёлого лазера",
                                "Перетаскивание реактора",
                                "Монтаж реактора"),
                        asList(superLaser, heavyLaser, body, reactor),
                        1000);
            }
        }
    }

    public static void main(String[] args) {
//        task1();
//        task2();
        task3();
//        task4();
    }

    private static void task2() {
        System.out.println("task 2");
        val schedule = new MinCostScheduleBuilder().build(deathStar);
        System.out.println("cost: " + schedule.getTotalCost());
        schedule.print();
    }

    private static void task4() {
        System.out.println("task 4");
        MaxPowerScheduleBuilder scheduleBuilder = new MaxPowerScheduleBuilder(
                asList(blaster, droid, lightsabre));
        Schedule schedule = scheduleBuilder.build(10000);
        System.out.println("cost: " + schedule.getTotalCost());
        schedule.print();
    }

    private static void task1() {
        System.out.println("task 1");
        long endTimeOfOpticsWork = 16105; //see task2,
        val schedule = new MinCostScheduleBuilder().build(deathStar);
        int eventsCount = schedule.getEvents().size();
        schedule.getEvents().stream()
                .limit(eventsCount - 7)
                .filter(e -> e.getStartTime() > endTimeOfOpticsWork)
                .forEach(e -> e.setStartTime(e.getStartTime() - endTimeOfOpticsWork));
        val buildDeathStar = schedule.getEvents().subList(eventsCount - 7, eventsCount);
        val startBuildingDeathStarTimeDiff = 24838 - 16105;
        buildDeathStar.stream()
                .forEach(e -> e.setStartTime(e.getStartTime() - startBuildingDeathStarTimeDiff));
        System.out.println("time: " + schedule.getTotalTime());
        System.out.println("cost: " + schedule.getTotalCost());
        schedule.print();
    }

    private static void task3() {
        System.out.println("task 3");
        val schedules = new ArrayList<Schedule>();
        val powers = new ArrayList<Integer>();
        Schedule best = null;
        int bestPower = 0;
        int bestDroid = 0;
        int bestBlast = 0;
        int bestSword = 0;
        for (int droids = 0; droids < 100; ++droids) {
            for (int blast = 0; blast < 50; ++blast) {
                for (int sword = 0; sword < 15; ++sword) {
                    int power = FastWeapon.power(sword, droids, blast);
                    if (power < bestPower) continue;
                    val res = new FastWeapon(sword, droids, blast).compute();
                    long time = res.getTotalTime();
                    if (time <= 1000) {
                        bestPower = power;
                        best = res;
                        bestDroid = droids;
                        bestBlast = blast;
                        bestSword = sword;
                    } else if (time < 1010) {
                        schedules.add(res);
                        powers.add(power);
                    }
                }
            }
        }
        System.out.println(" ----------- BEST ------------");
        System.out.println("power: " + bestPower);
        System.out.println(bestDroid + " droids, " + bestBlast + " blasters and " + bestSword + " lightsabers");
        assert best != null;
        best.print();
        System.out.println(" ----------- OTHERS ------------");
        for (int i = 0; i < schedules.size(); ++i) {
            if (powers.get(i) <= bestPower) continue;
            System.out.println("power: " + powers.get(i));
            System.out.println("time: " + schedules.get(i).getTotalTime());
            schedules.get(i).print();
        }
    }
}