package client.controllers;

import client.handler.EventListener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
@Slf4j
@Data
public class MainGameController implements Initializable {
    private int n;
    private Text utillText;
    private  String[] textArray;
    @FXML
    private TextFlow gameScreen;
    private List<Text> textList = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textArray ="There is little text here".split("");
        n=0;
        Arrays.stream(textArray).forEach(x->{
            utillText = new Text(x);
            utillText.setFont(Font.font(18));
            textList.add(utillText);
            gameScreen.getChildren().add(utillText);
        });
        System.out.println(Arrays.toString(textArray));
    }

    public void getTypedCode(KeyEvent keyEvent) {



        System.out.println(keyEvent.getCharacter().toString());



        if (keyEvent.getCharacter().toLowerCase().equals(textArray[n].toLowerCase())) {
            gameScreen.getChildren().remove(n);
            utillText =new Text(keyEvent.getCharacter().toLowerCase());
            utillText.setFont(Font.font(35));
            utillText.setStyle("-fx-stroke: #ff00c8");
            gameScreen.getChildren().add(n,utillText);

            n++;

            log.info("Совпадение символа {}",  n);
        }
        if (textArray[n].equals(" ") && keyEvent.getCharacter().toString().toLowerCase().equals("space")) {

            n++;
            log.info("Совпадение пробела {}",  n);
        }
        log.info("Следующая буква {}" ,textArray[n]);

    }


}
