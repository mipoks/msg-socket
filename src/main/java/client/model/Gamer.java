package client.model;

import client.visualizer.EventListener;
import javafx.event.Event;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class Gamer {
    private int progress; //in symbols
    private int id;
    private String name;
    private ArrayList<EventListener> eventListeners;

    public Gamer(int id, String name) {
        this.name = name;
        progress = 0;
        this.id = id;
        eventListeners = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return id == gamer.id;
    }

    public void addEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        onGamerChanged();
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        onGamerChanged();
        this.name = name;
    }

    private void onGamerChanged() {
        for (EventListener eventListener : eventListeners) {
            eventListener.onEventAction(this);
        }
    }
}
