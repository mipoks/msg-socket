package ru.itis.typergame.client.visualizer.eventListImpl;

import javafx.scene.text.Text;
import ru.itis.typergame.client.visualizer.EventListener;


public class RoomCodePrinter implements EventListener<String> {
    private Text textField;


    public RoomCodePrinter(Text textField) {
        this.textField = textField;
    }

    @Override
    public void onEventAction(String object) {
        textField.setText(object);
    }
}
