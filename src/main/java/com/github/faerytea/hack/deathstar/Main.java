package com.github.faerytea.hack.deathstar;

import com.github.faerytea.hack.deathstar.entities.Product;
import com.github.faerytea.hack.deathstar.entities.Work;
import com.github.faerytea.hack.deathstar.entities.Worker;
import lombok.val;

import java.util.*;

import static java.util.Arrays.asList;

public class Main {
    public static final Set<Worker> workers = new HashSet<>();
    public static final Map<String, List<Worker>> tasksToWorkers = new HashMap<>();
    public static final Product deathStar = null;
    public static final Product blaster;
    public static final Product droid;
    public static final Product lightsabre;

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
            sithhLabors.add(new Work("Вставка сердечника", 3, 50));
            droidLabors.add(new Work("Вставка сердечника", 4, 40));
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
                tasksToWorkers.put(work.name, asList(itMoOptic));
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
                tasksToWorkers.put(work.name, asList(hauler));
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
                tasksToWorkers.put(work.name, asList(metalWorker));
            }
        }
        // production
        {
            val pl = new Product("Платина", asList("Добыча платины"), Collections.emptyList());
            val pb = new Product("Слиток свинца", asList("Добыча свинца"), Collections.emptyList());
            // droid
            {
                val hand = new Product("Рука", asList("Сборка руки"), Collections.emptyList());
                val body = new Product("Туловище", asList("Сборка туловища"), Collections.emptyList());
                val leg = new Product("Нога", asList("Сборка ноги"), Collections.emptyList());
                val head = new Product("Голова", asList("Сборка головы", "Вставка компьютера"), Collections.emptyList());
                droid = new Product(
                        "Боевой дроид",
                        asList("Сборка дроида"),
                        asList(hand, hand, body, leg, leg, head));
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
                lightsabre = new Product(
                        "Световой меч",
                        asList("Сборка меча", "Полировка рукояти меча", "Заряд плазменных батарей"),
                        asList(cell, crystal));
            }
            // blaster
            {
                val body = new Product(
                        "Корпус",
                        asList("Сборка корпуса бластера"),
                        Collections.emptyList());
                val laser = new Product(
                        "Лазер",
                        asList("Огранка рубина", "Изготовление системы зеркал", "Сборка лазера"),
                        Collections.emptyList());
                blaster = new Product(
                        "Бластер",
                        asList(
                                "Установка лазера бластера в корпус",
                                "Полировка рукояти бластера",
                                "Заряд батарей бластера",
                                "Пристрелка"),
                        asList(body, laser));
            }
        }
    }
}
