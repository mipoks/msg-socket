package controllers;

import client.message.MessageCreater;
import client.message.Text;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void createRoom(){
        System.out.println("Комнта создана");
        MessageCreater.createTextMsg(Text.builder().text("grgerg").build());
    }

}
