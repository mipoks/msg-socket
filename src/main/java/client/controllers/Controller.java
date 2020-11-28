package client.controllers;

import client.handler.EventListener;
import client.logic.Client;
import client.message.MessageCreater;
import client.protocol.Message;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;
@Slf4j
public class Controller implements Initializable {
    @FXML
    TextField roomCode;
    @Setter
    private Client client;
    private EventListener<String> listener = x ->{





    };



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void createRoom(){

        log.info("Комнта создана");
        try {
            Message message = MessageCreater.createRoomCreateMsg();
            client.sendMessage(message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendConnect(MouseEvent mouseEvent) {
        try {
            Message message = MessageCreater.createRoomConnectMsg(roomCode.getText());
            client.sendMessage(message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
