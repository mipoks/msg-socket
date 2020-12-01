package client.visualizer.eventListImpl;

import client.model.Room;
import client.visualizer.EventListener;
import javafx.scene.control.TextField;

import java.util.Collection;


public class RoomCodePrinter implements EventListener<String> {
    private TextField textField;

    public RoomCodePrinter(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void onEventAction(String object) {
        textField.setText(object);
    }
}
