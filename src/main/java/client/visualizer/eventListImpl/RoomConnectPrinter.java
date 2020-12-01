package client.visualizer.eventListImpl;

import client.model.Room;
import client.visualizer.EventListener;
import javafx.event.Event;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.Collection;


public class RoomConnectPrinter implements EventListener<Pair> {

    private Text text;

    public RoomConnectPrinter(Text text) {
        this.text = text;
    }

    @Override
    public void onEventAction(Pair object) {
        Pair<Integer, String> pair = (Pair<Integer, String>) object;
        text.setText("ID: " + pair.getKey() + " Name: " + pair.getValue());
    }
}
