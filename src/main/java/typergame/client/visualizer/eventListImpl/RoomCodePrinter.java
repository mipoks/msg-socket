package typergame.client.visualizer.eventListImpl;

import typergame.client.visualizer.EventListener;
import javafx.scene.control.TextField;


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
