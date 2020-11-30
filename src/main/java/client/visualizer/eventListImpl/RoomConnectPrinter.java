package client.visualizer.eventListImpl;

import client.visualizer.EventListener;
import javafx.scene.text.Text;


public class RoomConnectPrinter implements EventListener<Integer> {

    private Text textStatus;

    public RoomConnectPrinter(Text textStatus) {
        this.textStatus = textStatus;
    }

    @Override
    public void onEventAction(Integer object) {
        textStatus.setText(String.valueOf(object));
    }
}
