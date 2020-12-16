package ru.itis.typergame.client.visualizer;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public interface ISceneChanger {
    public static void changeScene(Scene scene, InputEvent inputEvent){

        Stage primaryStage = (Stage)((Node)inputEvent.getSource()).getScene().getWindow();
        ThemeContext.checkTheme(scene);
        primaryStage.setScene(scene);
/*        primaryStage.setMinWidth(995);
        primaryStage.setMinHeight(687);*/
        scene.getRoot().requestFocus();
    };
    public  static   void   changeScene(Scene scene,Stage primaryStage){
        Platform.runLater(()->{
            ThemeContext.checkTheme(scene);
            primaryStage.setScene(scene);
/*        primaryStage.setMinWidth(995);
        primaryStage.setMinHeight(687);*/
            scene.getRoot().requestFocus();
        });

    }
}
