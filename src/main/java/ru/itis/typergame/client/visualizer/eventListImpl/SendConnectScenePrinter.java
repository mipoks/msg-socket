package ru.itis.typergame.client.visualizer.eventListImpl;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import ru.itis.typergame.client.visualizer.EventListener;
import ru.itis.typergame.client.visualizer.ISceneChanger;

@Slf4j
public class SendConnectScenePrinter implements EventListener {
    private Scene scene;
    private Stage primaryStage;
    public SendConnectScenePrinter(Scene scene, Stage stage){
      this.scene = scene;
      this.primaryStage = stage;
    }

    @Override
    public void onEventAction(Object object) {
        log.info("Some info");
        ISceneChanger.changeScene(scene,primaryStage);
    }
}
