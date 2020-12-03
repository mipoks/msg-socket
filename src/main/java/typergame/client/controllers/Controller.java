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
    private Scene gameScene;
    private Scene recordScene;
    private Scene settingsScene;
    private Message message;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     /*   ThemeContext.checkTheme();*/ //будем ли хранить смену темы юзера в бд ?
    }
    @FXML
    public void createRoom(MouseEvent mouseEvent){

        log.info("Комнта создана");
        try {
            Message message = MessageCreater.createRoomCreateMsg();
            Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            ThemeContext.checkTheme(gameScene);
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


    public void startDemo(MouseEvent mouseEvent) {
        Message message = null;
        try {
            message =MessageCreater.createStartGameMsg();
            client.sendMessage(message);
            Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(gameScene);





        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getRecordsTable(MouseEvent mouseEvent) {
        message =MessageCreater.createGetRecordsMsg();
        client.sendMessage(message);
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        ThemeContext.checkTheme(recordScene);
        primaryStage.setScene(recordScene);
        recordScene.getRoot().requestFocus();

    }

    public void getSettingsScene(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        ThemeContext.checkTheme(settingsScene);


        primaryStage.setScene(settingsScene);
        settingsScene.getRoot().requestFocus();
    }

}
