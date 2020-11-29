package client.controllers;

import client.logic.Client;
import client.message.MessageCreater;
import client.protocol.Message;
import client.visualizer.ThemeContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;
@Slf4j
public class Controller implements Initializable {
    @FXML
    private VBox body;
    @FXML
    TextField roomCode;
    @Setter
    private Client client;
    private String theme;
    @Setter
    private Scene scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        theme="light";
    }
    @FXML
    public void createRoom(){

        log.info("Комнта создана");
        try {
            Message message = MessageCreater.createRoomCreateMsg();
            client.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendConnect(MouseEvent mouseEvent) {
        try {
            Message message = MessageCreater.createRoomConnectMsg(roomCode.getText());
            client.sendMessage(message);
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
}
