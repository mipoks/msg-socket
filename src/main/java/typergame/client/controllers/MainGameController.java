package typergame.client.controllers;

import javafx.scene.paint.Paint;
import typergame.client.logic.Client;
import typergame.client.message.MessageCreater;
import typergame.client.visualizer.ThemeContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import typergame.protocol.Message;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Data
public class MainGameController implements Initializable {
    @FXML
    private Text roomCode;
    @FXML
    private Text gamerTwoName;
    @FXML
    private Text gamerOneName;
    @FXML
    private Text gamerThreeName;
    @FXML
    private Text gamerFourName;
    @FXML
    private Text tappedChar;
    @FXML
    private VBox body;
    private String theme;
    private int n;
    private Text utillText;
    private String[] textArray;
    @FXML
    private TextFlow gameScreen;
    private Client client;
    private List<Text> textList = new ArrayList<>();
    private Scene winScene;
    private Scene lostScene;


    @FXML
    public void prepare(String text) {
        n = 0;
        body.setStyle(ThemeContext.DEFAULT_THEME);
        if (ThemeContext.currentTheme.equals(ThemeContext.DARK_THEME)) {
            dupplMapper(text, "-fx-stroke: #ffffff");
        } else {
            dupplMapper(text, "-fx-stroke: #353535");
        }

    }

    private void dupplMapper(String text, String style) {
        textArray = text.split("");
        n = 0;
        Arrays.stream(textArray).forEach(x -> {
            utillText = new Text(x);
            utillText.setFont(Font.font(40));
            utillText.setFill(Paint.valueOf("778899"));

            utillText.setStyle(style);
            textList.add(utillText);
            gameScreen.getChildren().add(utillText);
        });
        System.out.println(Arrays.toString(textArray));
       /* ThemeContext.checkTheme(body);*/
        log.info("Vbox body :{}", body);
    }


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*ThemeContext.checkTheme(body);*/
    }

    public void checkSensor(MouseEvent mouseEvent) {
        log.info("Sensor mouse checked");
    }

    public void handleTypedCode(KeyEvent keyEvent) {
        try {

            log.info("Code typed");
            System.out.println("code");

            log.info("Taked code{}", keyEvent.getCharacter());


            if (keyEvent.getCharacter().toLowerCase().equals(getTextArray()[n].toLowerCase()) ||
                    getTextArray()[n].equals(" ") && keyEvent.getCharacter().toLowerCase().equals("space")) {
/*                getGameScreen().getChildren().remove(n);
                setUtillText(new Text(keyEvent.getCharacter().toLowerCase()));
                getUtillText().setFont(Font.font(35));

//                getUtillText().setStyle("-fx-stroke: #ff0000");
                utillText.setFill(Paint.valueOf("778899"));
                log.info(getUtillText().getStyle());
                getGameScreen().getChildren().add(n, getUtillText());*/

                n++;

                Message message = MessageCreater.createPlayGameMsg(keyEvent.getCharacter());


                try {
                    client.sendMessage(message);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("Совпадение символа {}", n);
            }
//
            if (n == getTextArray().length) {

                Stage primaryStage = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                log.info("сцена {}", winScene);
                primaryStage.setScene(winScene);
                winScene.getRoot().requestFocus();
                log.info("Is focused?{}", winScene.getRoot().isFocused());
            } else {
                log.info("Следующая буква {}", getTextArray()[n]);
            }
            ((Text)getGameScreen().getChildren().get(n)).setFont(Font.font(55));


        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }

    public void startDemo(MouseEvent mouseEvent) {
        Message message = null;
        message = MessageCreater.createStartGameMsg();
        client.sendMessage(message);
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    }
}
