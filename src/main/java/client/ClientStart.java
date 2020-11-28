package client;

import client.controllers.Controller;
import client.handler.implementation.RoomConnectHandler;
import client.handler.implementation.RoomCreateHandler;
import client.logic.Client;
import client.visualizer.RoomCodePrinter;
import client.visualizer.RoomConnectPrinter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class ClientStart extends Application {
    public TextField textField;
    public Text textStatus;

    public static void main(String[] args) throws IOException, IllegalAccessException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/socketSemestr.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/MainGame.fxml"));
        Parent root = loader2.load();

        Controller controller =(Controller) loader.getController();
        Client client = new Client(InetAddress.getByName("127.0.0.1"),4888);
        /*controller.setClient(client);*/
        client.connect();

        RoomCreateHandler roomCreateHandler = new RoomCreateHandler(client);
        RoomConnectHandler roomConnectHandler = new RoomConnectHandler(client);


        client.registerListener(roomConnectHandler);
        client.registerListener(roomCreateHandler);
        client.start();


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
/*
        textStatus = (Text) scene.lookup("#textStatus");
        textField = (TextField)scene.lookup("#roomID1");//Селектор для id и fx:id , берёт первое вхождение, если совпадений несколько
        textField.setText("SomeText");
        RoomCodePrinter roomCodePrinter = new RoomCodePrinter(textField);
        roomCreateHandler.addEventListener(roomCodePrinter);

        RoomConnectPrinter roomConnectPrinter = new RoomConnectPrinter(textStatus);
        roomConnectHandler.addEventListener(roomConnectPrinter);*/


/*
        System.out.println(""+ textField.toString());*/
    }
}
