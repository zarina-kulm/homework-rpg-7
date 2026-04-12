package com.narxoz.rpg.engine;
import com.narxoz.rpg.combatant.*;
import java.util.List;
public class DungeonEngine {
    private final List<Hero> heroes;
    private final DungeonBoss boss;
    private static final int MAX_ROUNDS = 20;
    public DungeonEngine(List<Hero> heroes, DungeonBoss boss) {
        this.heroes = heroes;
        this.boss = boss;
    }
    public EncounterResult runEncounter() {
        int round = 1;
        while (round <= MAX_ROUNDS && boss.isAlive() && anyHeroAlive()) {
            System.out.println("===== ROUND " + round + " =====");
            for (Hero hero : heroes) {
                if (hero.isAlive()) {
                    hero.attack(boss);
                }
            }
            for (Hero hero : heroes) {
                if (hero.isAlive()) {
                    boss.attack(hero);
                }
            }
            round++;
        }
        boolean heroesWon = !boss.isAlive();
        int survivingHeroes = countAliveHeroes();
        return new EncounterResult(heroesWon, round - 1, survivingHeroes);
    }
    private boolean anyHeroAlive() {
        for (Hero hero : heroes) {
            if (hero.isAlive()) return true;
        }
        return false;
    }
    private int countAliveHeroes() {
        int count = 0;
        for (Hero hero : heroes) {
            if (hero.isAlive()) count++;
        }
        return count;
    }
}
