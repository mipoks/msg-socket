package client;

import client.controllers.Controller;
import client.controllers.LostController;
import client.controllers.MainGameController;
import client.controllers.WinController;
import client.handler.implementation.RivalConnectHandler;
import client.handler.implementation.RoomConnectHandler;
import client.handler.implementation.RoomCreateHandler;
import client.logic.Client;
import client.visualizer.RivalPrinter;
import client.visualizer.RoomCodePrinter;
import client.visualizer.RoomConnectPrinter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetAddress;
@Slf4j
public class ClientStart extends Application {
    public TextField textField;
    public Text textStatus;
    private int n;
    private Parent root;
    private Parent wint;
    private Parent mainGame;
    private Parent loose;
    private  Controller controller;
    private MainGameController controller1;
    private  WinController controller3;
    private  LostController controller4;
    private  Client client;
    private Scene scene;
    private   FXMLLoader loader ;
    private   FXMLLoader loader2;
    private    FXMLLoader loader3;
    private  FXMLLoader loader4;
    private RoomCreateHandler roomCreateHandler;
    private  RoomConnectHandler roomConnectHandler;
    private RivalConnectHandler rivalConnectHandler;
    private RoomCodePrinter roomCodePrinter;
    private  RoomConnectPrinter roomConnectPrinter;
    private  RivalPrinter rivalPrinter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        n=0;
        loader = new FXMLLoader(getClass().getResource("/socketSemestr.fxml"));
        loader2 = new FXMLLoader(getClass().getResource("/MainGame.fxml"));
        loader3 = new FXMLLoader(getClass().getResource("/Win.fxml"));
        loader4 = new FXMLLoader(getClass().getResource("/lost.fxml"));

        try {
            loose =loader4.load();
            root = loader.load();
            wint = loader3.load();
            mainGame = loader2.load();


            controller = loader.getController(); //manuController
            controller1 =loader2.getController();
            controller3 = loader3.getController();//win scene controller
             controller4 = loader4.getController();//loose scene controller
             scene = new Scene(root);
            controller.setScene(scene);
            controller3.setScene(scene);
            controller4.setScene(scene);

            client = new Client(InetAddress.getByName("127.0.0.1"),4888);
            controller.setClient(client);
            client.connect();

             roomCreateHandler = new RoomCreateHandler(client);
             roomConnectHandler = new RoomConnectHandler(client);
             rivalConnectHandler = new RivalConnectHandler(client);
            client.registerListener(roomConnectHandler);
            client.registerListener(roomCreateHandler);
            client.registerListener(rivalConnectHandler);
            client.start();


            Scene gameScene = new Scene(mainGame);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();


            textField = (TextField) scene.lookup("#roomID");
            roomCodePrinter = new RoomCodePrinter(textField);
            textStatus = (Text) scene.lookup("#textStatus");
            roomConnectPrinter = new RoomConnectPrinter(textStatus);
            roomConnectHandler.addEventListener(roomConnectPrinter);
            roomCreateHandler.addEventListener(roomCodePrinter);
            log.info(" opponent row{}",controller.getOpponentRow());
            rivalPrinter = new RivalPrinter(controller.getOpponentRow());//Выводит принтер в контроллер
            rivalConnectHandler.addEventListener(rivalPrinter);//каждый раз когда соперник подключился , отправит всем листенерам Pair


                 //заплатка Todo разобраться с плохим контроллером
           try {
               gameScene.setOnKeyTyped(new EventHandler<KeyEvent>() {
                   @Override
                   public void handle(KeyEvent keyEvent) {

                       log.info("Code typed");
                       System.out.println("code");

                       log.info("Taked code{}", keyEvent.getCharacter().toString());


                       if (keyEvent.getCharacter().toLowerCase().equals(controller1.getTextArray()[n].toLowerCase())) {
                           controller1.getGameScreen().getChildren().remove(n);
                           controller1.setUtillText(new Text(keyEvent.getCharacter().toLowerCase()));
                           controller1.getUtillText().setFont(Font.font(35));

                           controller1.getUtillText().setStyle("-fx-stroke: #ff00c8");
                           log.info(controller1.getUtillText().getStyle());
                           controller1.getGameScreen().getChildren().add(n, controller1.getUtillText());
                           controller1.getTappedChar().setText(controller1.getUtillText().getText());

                           n++;

                           log.info("Совпадение символа {}", n);
                       }
                       if (controller1.getTextArray()[n].equals(" ") && keyEvent.getCharacter().toString().toLowerCase().equals("space")) {

                           n++;
                           log.info("Совпадение пробела {}", n);
                       }
                       log.info("Следующая буква {}", controller1.getTextArray()[n]);


                   }
               });
           }catch (Exception e){
               //Игрок прнажимал весь текст
               System.out.println(" Игрок пронажимал весь текст");
           }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
