package client.controllers;

import client.logic.Client;
import client.message.MessageCreater;
import client.protocol.Message;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Setter
    private Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void createRoom(){

        System.out.println("Комнта создана");
        try {
            Message message = MessageCreater.createRoomCreateMsg();
            client.sendMessage(message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
