package client.logic;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
@Slf4j
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
