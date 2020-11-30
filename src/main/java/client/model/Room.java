package client.model;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;

@Slf4j
public class Room implements Serializable {
    private String roomUnique;
    private boolean publicity;
    private ArrayList<Gamer> gamers;
    //ToDo работа с publicity

    public Room() {
        gamers = new ArrayList<>();
    }

    public void addGamer(Gamer gamer) {
        if (!gamers.contains(gamer))
            gamers.add(gamer);
    }

    public void removeGamer(Gamer gamer) {
        gamers.remove(gamer);
    }

    public String getRoomUnique() {
        return roomUnique;
    }

    public void setRoomUnique(String roomUnique) {
        this.roomUnique = roomUnique;
    }
}
