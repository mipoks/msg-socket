package ru.itis.typergame.client.visualizer.eventListImpl;

import lombok.extern.slf4j.Slf4j;
import ru.itis.typergame.client.controllers.MainGameController;
import ru.itis.typergame.client.visualizer.EventListener;
@Slf4j
public class ButtonsPrinter implements EventListener {
    private  MainGameController mainGameController;
    public ButtonsPrinter(MainGameController mainGameController){
         this.mainGameController =mainGameController;
    }

    @Override
    public void onEventAction(Object object) {
            log.info("Показываю кнопки");
        mainGameController.getDemoGame().setVisible(true);
        mainGameController.getHardGameMode().setVisible(true);
        mainGameController.getPublicity().setVisible(true);
    }
}
