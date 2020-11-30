package client.controllers;

import client.visualizer.ThemeContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Data
public class WinController implements Initializable {
    @FXML
    public ImageView imageView;
    @FXML
    private VBox body;
    @Setter
    private Scene scene;

/*    @FXML
    public void initialize(){
        log.info(imageView.toString());
        Image i = new Image(new File("src/main/resources/assets/SdRockFact.gif").toURI().toString());
        imageView.setImage(i);
    }*/

    public void goToMenu(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        log.info("сцена {}",scene);
        primaryStage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info(imageView.toString());
        Image i = new Image(new File("src/main/resources/assets/SdRockFact.gif").toURI().toString());
        imageView.setImage(i);
        log.info("Проверяю тему при инициализации");
        ThemeContext.checkTheme(body);
    }
}
