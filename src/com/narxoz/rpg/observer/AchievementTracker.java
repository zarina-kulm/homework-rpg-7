package com.narxoz.rpg.observer;
public class AchievementTracker implements GameObserver {
    private int attackCount = 0;
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.ATTACK_LANDED) {
            attackCount++;
            if (attackCount == 10) {
                System.out.println("Achievement Unlocked: 10 Hits!");
            }
        }
        if (event.getType() == GameEventType.BOSS_DEFEATED) {
            System.out.println("Achievement Unlocked: Boss Slayer!");
        }
        if (event.getType() == GameEventType.HERO_DIED) {
            System.out.println("Achievement Unlocked: First Blood...");
        }
    }
}