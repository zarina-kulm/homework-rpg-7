package com.narxoz.rpg.observer;
import java.util.ArrayList;
import java.util.List;
public class EventManager {
    private final List<GameObserver> observers = new ArrayList<>();
    public void subscribe(GameObserver observer) {
        observers.add(observer);
    }
    public void unsubscribe(GameObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers(GameEvent event) {
        for (GameObserver observer : observers) {
            observer.onEvent(event);
        }
    }
}
