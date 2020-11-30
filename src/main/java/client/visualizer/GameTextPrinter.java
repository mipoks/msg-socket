package client.visualizer;

import client.controllers.MainGameController;
import client.handler.EventListener;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
@Slf4j
public class GameTextPrinter implements EventListener<String>
{
    MainGameController mainGameController;

    public GameTextPrinter(MainGameController mainGameController){
        this.mainGameController = mainGameController;
    }
    @Override
    public void onEventAction(String object)    {
        log.info("Подготавливаю текст");
        Platform.runLater(()->{
            mainGameController.prepare(object);
        });


    }
}
