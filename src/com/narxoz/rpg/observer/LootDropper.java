package com.narxoz.rpg.observer;
import java.util.Random;
public class LootDropper implements GameObserver {
    private Random random = new Random();
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED) {
            System.out.println("Loot drop: Phase reward!");
        }
        if (event.getType() == GameEventType.BOSS_DEFEATED) {
            String[] loot = {"Legendary Sword", "Epic Armor", "Mythic Ring"};
            String drop = loot[random.nextInt(loot.length)];
            System.out.println("FINAL LOOT: " + drop);
        }
    }
}
