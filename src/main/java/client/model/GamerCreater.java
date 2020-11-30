package client.model;

import client.visualizer.EventListener;
import javafx.util.Pair;

public class GamerCreater implements EventListener<Pair> {
    @Override
    public void onEventAction(Pair object) {
        Pair<Integer, String> pair = (Pair) object;
        Gamer gamer = new Gamer(pair.getKey(), pair.getValue());
        Room room = Room.getActualRoom();
        room.addGamer(gamer);
    }
}
