package client.controllers;

import client.handler.EventListener;

import client.visualizer.ThemeContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
@Slf4j
@Data
public class MainGameController implements Initializable {
    @FXML
    private Text tappedChar;
    @FXML
    private VBox body;
    private int n;
    private Text utillText;
    private  String[] textArray;
    @FXML
    private TextFlow gameScreen;
    private List<Text> textList = new ArrayList<>();



    public void prepare(String text){
        body.setStyle(ThemeContext.DEFAULT_THEME);

        textArray =text.split("");
        n=0;
        Arrays.stream(textArray).forEach(x->{
            utillText = new Text(x);
            utillText.setFont(Font.font(18));
            textList.add(utillText);
            gameScreen.getChildren().add(utillText);
        });
        System.out.println(Arrays.toString(textArray));
        ThemeContext.checkTheme(body);
        log.info("Vbox body :{}",body);
    }



    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    public void checkSensor(MouseEvent mouseEvent) {
        log.info("Sensor mouse checked");
    }

    public void handleTypedCode(KeyEvent keyEvent) {
      /*  log.info("Code typed");
        System.out.println("code");

        log.info("Taked code{}",keyEvent.getCharacter().toString());



        if (keyEvent.getCharacter().toLowerCase().equals(textArray[n].toLowerCase())) {
            gameScreen.getChildren().remove(n);
            utillText =new Text(keyEvent.getCharacter().toLowerCase());
            utillText.setFont(Font.font(35));

            utillText.setStyle("-fx-stroke: #ff00c8");
            log.info(utillText.getStyle());
            gameScreen.getChildren().add(n,utillText);
            tappedChar.setText(utillText.getText());

            n++;

            log.info("Совпадение символа {}",  n);
        }
        if (textArray[n].equals(" ") && keyEvent.getCharacter().toString().toLowerCase().equals("space")) {

            n++;
            log.info("Совпадение пробела {}",  n);
        }
        log.info("Следующая буква {}" ,textArray[n]);*/
    }
}
