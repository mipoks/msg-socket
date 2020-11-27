package client.controllers;

import client.handler.EventListener;
import client.handler.implementation.TextHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import lombok.Data;

import java.net.URL;
import java.util.ResourceBundle;
@Data
public class MainGameController implements Initializable {
    private Integer n;


    private TextHandler textHandler;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getTypedCode(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode().toString());

        /* System.out.print(keyEvent.getCharacter().toString());*/
     /*   if (keyEvent.getCode().toString().toLowerCase().equals(textArray[n].toLowerCase())) {
            n=0;
            n++;
            cursor.setLayoutX(cursor.getLayoutX() + 20);
            *//* controller.setPosition(controller.getCursorXPosition()+14,controller.getCursorYPosition()+14);*//*
            System.out.println("Совпадение символа" + n);
        }
        if (textArray[n].equals(" ") && keyEvent.getCode().toString().toLowerCase().equals("space")) {
            cursor.setLayoutX(cursor.getLayoutX() + 20);
            *//*controller.setPosition(controller.getCursorXPosition()+14,controller.getCursorYPosition()+14);*//*
            n++;
            System.out.println("Совпадение пробела" + n);
        }*/

    }


}
