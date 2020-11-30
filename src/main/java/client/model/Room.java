package client.model;

import client.visualizer.EventListener;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;

@Slf4j
public class Room implements EventListener<Pair> {
    private String roomUnique;
    private boolean publicity;
    private ArrayList<Gamer> gamers;
    private static Room actualRoom;
    private ArrayList<EventListener> eventListeners;

    public static Room createNewRoom() {
        actualRoom = new Room();
        return actualRoom;
    }
    public static Room getActualRoom() {
        if (actualRoom == null)
            actualRoom = new Room();
        return actualRoom;
    }
    private Room() {
        gamers = new ArrayList<>();
        eventListeners = new ArrayList<>();
        publicity = false;
    }

    public boolean isPublicity() {
        return publicity;
    }

    public void setPublicity(boolean publicity) {
        this.publicity = publicity;
    }

    public void addGamer(Gamer gamer) {
        if (!gamers.contains(gamer)) {
            gamers.add(gamer);
            onRoomChanged(1, gamer);
        }
    }

    public void removeGamer(Gamer gamer) {
        gamers.remove(gamer);
        onRoomChanged(-1, gamer);
    }

    public String getRoomUnique() {
        return roomUnique;
    }

    public void setRoomUnique(String roomUnique) {
        this.roomUnique = roomUnique;
    }

    @Override
    public void onEventAction(Pair object) {
        Pair<Integer, String> pair = (Pair) object;
        Gamer gamer = new Gamer(pair.getKey(), pair.getValue());
        if (gamers.contains(gamer))
            return;

        gamers.add(gamer);

    }

    private void onRoomChanged(int type, Gamer gamer) {
        //type -1 удаление, type 1 добавлнение
        Pair<Integer, Gamer> pair = new Pair<Integer, Gamer>(type, gamer);
        for (EventListener eventListener : eventListeners) {
            eventListener.onEventAction(pair);
        }
        //onGamerAdded
        //onGamerRemoved
    }


    public void addEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }
}
