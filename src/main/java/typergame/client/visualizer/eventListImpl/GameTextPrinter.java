package typergame.client.visualizer.eventListImpl;

import typergame.client.controllers.MainGameController;
import typergame.client.visualizer.EventListener;
import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameTextPrinter implements EventListener<String> {
    MainGameController mainGameController;

    public GameTextPrinter(MainGameController mainGameController) {
        this.mainGameController = mainGameController;
    }

    @Override
    public synchronized void onEventAction(String object) {
        log.info("Подготавливаю текст");
        Platform.runLater(() -> {
            mainGameController.prepare(object);
        });


    }
}
