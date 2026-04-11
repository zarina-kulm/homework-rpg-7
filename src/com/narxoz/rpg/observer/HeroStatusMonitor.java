package com.narxoz.rpg.observer;
public class HeroStatusMonitor implements GameObserver {
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP) {
            System.out.println("[STATUS] " + event.getSourceName() + " is LOW HP!");
        }
        if (event.getType() == GameEventType.HERO_DIED) {
            System.out.println("[STATUS] " + event.getSourceName() + " has DIED!");
        }
    }
}
