package com.narxoz.rpg;
import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.strategy.*;
import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.engine.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        Hero hero1 = new Hero("Warrior", 150, 20, 10,
                new AggressiveStrategy(), eventManager);
        Hero hero2 = new Hero("Tank", 200, 15, 15,
                new DefensiveStrategy(), eventManager);
        Hero hero3 = new Hero("Rogue", 100, 25, 8,
                new BalancedStrategy(), eventManager);
        List<Hero> heroes = Arrays.asList(hero1, hero2, hero3);
        DungeonBoss boss = new DungeonBoss(
                "Ancient Dragon",
                500,
                20,
                10,
                eventManager
        );
        eventManager.subscribe(new BattleLogger());
        eventManager.subscribe(new AchievementTracker());
        eventManager.subscribe(new PartySupport(heroes));
        eventManager.subscribe(new HeroStatusMonitor());
        eventManager.subscribe(new LootDropper());
        eventManager.subscribe(boss);
        DungeonEngine engine = new DungeonEngine(heroes, boss);

        hero3.setStrategy(new AggressiveStrategy());
        EncounterResult result = engine.runEncounter();
        System.out.println("\n===== RESULT =====");
        System.out.println("Heroes won: " + result.isHeroesWon());
        System.out.println("Rounds played: " + result.getRoundsPlayed());
        System.out.println("Surviving heroes: " + result.getSurvivingHeroes());
    }
}
