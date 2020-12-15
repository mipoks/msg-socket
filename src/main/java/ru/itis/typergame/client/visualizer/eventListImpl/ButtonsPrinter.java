package ru.itis.typergame.client.visualizer.eventListImpl;

import ru.itis.typergame.client.controllers.MainGameController;
import ru.itis.typergame.client.visualizer.EventListener;

public class ButtonsPrinter implements EventListener {
    private  MainGameController mainGameController;
    public ButtonsPrinter(MainGameController mainGameController){
         this.mainGameController =mainGameController;
    }

    @Override
    public void onEventAction(Object object) {
        mainGameController.getDemoGame().setVisible(true);
        mainGameController.getHardGameMode().setVisible(true);
        mainGameController.getPublicity().setVisible(true);
    }
}
