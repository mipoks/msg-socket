package ru.itis.typergame.client.visualizer.eventListImpl;

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;
import ru.itis.typergame.client.visualizer.EventListener;


@Slf4j
public class RoomCodePrinter implements EventListener<String> {
    private Label label;


    public RoomCodePrinter(Label labelText) {
        this.label = labelText;
    }

    @Override
    public void onEventAction(String object) {
        log.info("IN CODE PRINTER " + object);
        label.setText(object);
    }
}
