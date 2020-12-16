package ru.itis.typergame.client.visualizer.eventListImpl;

import javafx.util.Pair;
import ru.itis.typergame.client.controllers.MainGameController;
import ru.itis.typergame.client.visualizer.EventListener;

public class DisconectPrinter implements EventListener<Pair<Integer , Integer>> {
    private MainGameController mainGameController;
    public DisconectPrinter(MainGameController controller){
        this.mainGameController = controller;
    }

    @Override
    public void onEventAction(Pair<Integer, Integer> object) {

    }
}
