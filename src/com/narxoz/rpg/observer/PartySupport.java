package com.narxoz.rpg.observer;
import com.narxoz.rpg.combatant.Hero;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PartySupport implements GameObserver {
    private List<Hero> heroes;
    private Random random = new Random();
    public PartySupport(List<Hero> heroes) {
        this.heroes = heroes;
    }
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP) {
            List<Hero> alive = new ArrayList<>();
            for (Hero h : heroes) {
                if (h.isAlive()) {
                    alive.add(h);
                }
            }
            if (!alive.isEmpty()) {
                Hero target = alive.get(random.nextInt(alive.size()));
                System.out.println("Support heals " + target.getName() + " for 15 HP");
            }
        }
    }
}
