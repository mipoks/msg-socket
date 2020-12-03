package typergame.client.controllers;

import typergame.client.logic.Client;
import typergame.client.message.MessageCreater;
import typergame.client.visualizer.ThemeContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import typergame.protocol.Message;

import java.net.URL;
import java.util.ResourceBundle;
@Slf4j
@Data
public class Controller implements Initializable {

    @FXML
    private Text opponentRow;
    @FXML
    private VBox body;
    @FXML
    TextField roomCode;
    @Setter
    private Client client;
    private String theme;
    @Setter
    private Scene scene;
    private Scene gameScene;
    private Scene recordScene;
    private Message message;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        theme="light";
    }
    @FXML
    public void createRoom(MouseEvent mouseEvent){

        log.info("Комнта создана");
        try {
            Message message = MessageCreater.createRoomCreateMsg();
            Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            log.info("сцена {}",gameScene);
            primaryStage.setScene(gameScene);
            gameScene.getRoot().requestFocus();
            log.info("Is focused?{}",gameScene.getRoot().isFocused());
            client.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void sendConnect(MouseEvent mouseEvent) {
        log.info("connect sended");
        try {

            Message message = MessageCreater.createRoomConnectMsg(roomCode.getText());
            log.info("message text {}",roomCode.getText());

            client.sendMessage(message);
            Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            log.info("сцена {}",gameScene);
            primaryStage.setScene(gameScene);
            gameScene.getRoot().requestFocus();
            log.info("Is focused?{}",gameScene.getRoot().isFocused());




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeTheme(MouseEvent mouseEvent) {
        if (ThemeContext.currentTheme.equals(ThemeContext.DEFAULT_THEME)) {
            ThemeContext.currentTheme =ThemeContext.DARK_THEME;

            body.setStyle(ThemeContext.DARK_THEME);
        }else {
            ThemeContext.currentTheme =ThemeContext.DEFAULT_THEME;
            body.setStyle(ThemeContext.DEFAULT_THEME);
        }
    }

    public void startDemo(MouseEvent mouseEvent) {
        Message message = null;
        try {
            message =MessageCreater.createStartGameMsg();
            client.sendMessage(message);
            Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            log.info("сцена {}",scene);
            primaryStage.setScene(gameScene);





        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getRecordsTable(MouseEvent mouseEvent) {
        message =MessageCreater.createRoomCreateMsg();
        client.sendMessage(message);
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(recordScene);

    }
}
