package com.narxoz.rpg.combatant;
import com.narxoz.rpg.strategy.*;
import com.narxoz.rpg.observer.*;
public class DungeonBoss implements GameObserver {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private int currentPhase = 1;
    private CombatStrategy strategy;
    private final EventManager eventManager;
    public DungeonBoss(String name, int hp, int attackPower, int defense, EventManager eventManager) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.eventManager = eventManager;
        this.strategy = new Phase1Strategy();
    }
    public void takeDamage(int damage) {
        int reduced = damage - strategy.calculateDefense(defense);
        if (reduced < 0) reduced = 0;
        hp -= reduced;
        checkPhase();
        if (hp <= 0) {
            hp = 0;
            eventManager.notifyObservers(
                    new GameEvent(GameEventType.BOSS_DEFEATED, name, 0)
            );
        }
    }
    private void checkPhase() {
        double percent = (double) hp / maxHp;
        if (percent <= 0.3 && currentPhase == 2) {
            currentPhase = 3;
            eventManager.notifyObservers(
                    new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, 3)
            );
        }
        else if (percent <= 0.6 && currentPhase == 1) {
            currentPhase = 2;
            eventManager.notifyObservers(
                    new GameEvent(GameEventType.BOSS_PHASE_CHANGED, name, 2)
            );
        }
    }
    public void attack(Hero hero) {
        int damage = strategy.calculateDamage(attackPower);
        hero.takeDamage(damage);
    }
    public boolean isAlive() {
        return hp > 0;
    }
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED
                && event.getSourceName().equals(name)) {
            int phase = event.getValue();
            if (phase == 2) {
                strategy = new Phase2Strategy();
            } else if (phase == 3) {
                strategy = new Phase3Strategy();
            }
            System.out.println(name + " switched to " + strategy.getName());
        }
    }
}
