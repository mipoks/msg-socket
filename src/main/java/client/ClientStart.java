package client;

import client.controllers.Controller;
import client.controllers.LostController;
import client.controllers.MainGameController;
import client.controllers.WinController;
import client.handler.handlerImpl.GameStartHandler;
import client.handler.handlerImpl.RivalConnectHandler;
import client.handler.handlerImpl.RoomConnectHandler;
import client.handler.handlerImpl.RoomCreateHandler;
import client.logic.Client;
import client.visualizer.ThemeContext;
import client.visualizer.eventListImpl.GameTextPrinter;
import client.visualizer.eventListImpl.RivalPrinter;
import client.visualizer.eventListImpl.RoomCodePrinter;
import client.visualizer.eventListImpl.RoomConnectPrinter;
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
    private GameStartHandler gameStartHandler;
    private RoomCodePrinter roomCodePrinter;
    private  RoomConnectPrinter roomConnectPrinter;
    private  RivalPrinter rivalPrinter;
    private GameTextPrinter gameTextPrinter;


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
            scene = new Scene(root);

            controller = loader.getController(); //manuController
            controller1 =loader2.getController();//MainGame controller
            controller3 = loader3.getController();//win scene controller
             controller4 = loader4.getController();//loose scene controller

            controller.setScene(scene);
            controller3.setScene(scene);
            controller4.setScene(scene);

            client = new Client(InetAddress.getByName("127.0.0.1"),4888);
            controller.setClient(client);
            client.connect();

             roomCreateHandler = new RoomCreateHandler(client);
             roomConnectHandler = new RoomConnectHandler(client);
             rivalConnectHandler = new RivalConnectHandler(client);
             gameStartHandler = new GameStartHandler(client);

            client.registerListener(roomConnectHandler);
            client.registerListener(roomCreateHandler);
            client.registerListener(rivalConnectHandler);
            client.registerListener(gameStartHandler);
            client.start();


            Scene gameScene = new Scene(mainGame);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
            controller.setGameScene(gameScene);
           /* gameTextPrinter = new GameTextPrinter();*/

            textField = (TextField) scene.lookup("#roomID");
            textStatus = (Text) scene.lookup("#textStatus");
            roomCodePrinter = new RoomCodePrinter(textField);
            roomConnectPrinter = new RoomConnectPrinter(textStatus);
            gameTextPrinter = new GameTextPrinter(controller1);
            rivalPrinter = new RivalPrinter(controller.getOpponentRow());//Выводит принтер в контроллер

            gameStartHandler.addEventListener(gameTextPrinter);
            roomConnectHandler.addEventListener(roomConnectPrinter);
            roomCreateHandler.addEventListener(roomCodePrinter);
            rivalConnectHandler.addEventListener(rivalPrinter);//каждый раз когда соперник подключился , отправит всем листенерам Pair

            log.info(" opponent row{}",controller.getOpponentRow());


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

                           log.info("Совпадение символа {} длинная массив {}", n,controller1.getTextArray().length);
                           if (n==controller1.getTextArray().length){
                               ThemeContext.checkTheme(controller3.getBody());
                               ThemeContext.checkTheme(controller4.getBody());
                               primaryStage.setScene(new Scene(wint));
                               primaryStage.show();
                               wint.requestFocus();
                           }
                       }
                       if (controller1.getTextArray()[n].equals(" ") && keyEvent.getCharacter().toString().toLowerCase().equals("space")) {

                           n++;
                           log.info("Совпадение пробела {}", n);
                       }




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
