package com.narxoz.rpg.combatant;
import com.narxoz.rpg.strategy.CombatStrategy;
import com.narxoz.rpg.observer.EventManager;
import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventType;
public class Hero {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private CombatStrategy strategy;
    private EventManager eventManager;

    public Hero(String name, int hp, int attackPower, int defense,
                CombatStrategy strategy, EventManager eventManager) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.strategy = strategy;
        this.eventManager = eventManager;
    }
    public String getName()        {
        return name; }
    public int getHp()             {
        return hp; }
    public int getMaxHp()          {
        return maxHp; }
    public int getAttackPower()    {
        return attackPower; }
    public int getDefense()        {
        return defense; }
    public boolean isAlive()       {
        return hp > 0; }

    public void attack(DungeonBoss boss) {
        int damage = strategy.calculateDamage(attackPower);
        boss.takeDamage(damage);
        eventManager.notifyObservers(
                new GameEvent(GameEventType.ATTACK_LANDED, name, damage)
        );
    }
    public void takeDamage(int amount) {
        int reduced = amount - strategy.calculateDefense(defense);
        if (reduced < 0) reduced = 0;
        hp = Math.max(0, hp - reduced);
        if (hp == 0) {
            eventManager.notifyObservers(
                    new GameEvent(GameEventType.HERO_DIED, name, 0)
            );
        } else if (hp < maxHp * 0.3) {
            eventManager.notifyObservers(
                    new GameEvent(GameEventType.HERO_LOW_HP, name, hp)
            );
        }
    }
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }
    public void setStrategy(CombatStrategy strategy) {
        this.strategy = strategy;
        System.out.println(name + " switched to " + strategy.getName());
    }
}
