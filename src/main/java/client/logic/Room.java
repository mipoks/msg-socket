package client.logic;

import java.io.Serializable;

public class Room implements Serializable {
    private String roomUnique;
    private boolean publicity;
    //ToDo работа с publicity

    public String getRoomUnique() {
        return roomUnique;
    }

    public void setRoomUnique(String roomUnique) {
        this.roomUnique = roomUnique;
    }
}
