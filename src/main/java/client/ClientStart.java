package client;

import client.controllers.Controller;
import client.controllers.LostController;
import client.controllers.WinController;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
@Slf4j
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
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/Win.fxml"));
        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/lost.fxml"));

        loader2.load();
        loader4.load();
        Parent root = loader.load();
        Parent wint = loader3.load();


        Controller controller = loader.getController(); //manuController
        WinController controller3 = loader3.getController();//win scene controller
        LostController controller4 = loader4.getController();//loose scene controller
        Scene scene = new Scene(root);
        controller3.setScene(scene);
        controller4.setScene(scene);

        Client client = new Client(InetAddress.getByName("127.0.0.1"),4888);
        controller.setClient(client);
        client.connect();

        RoomCreateHandler roomCreateHandler = new RoomCreateHandler(client);
        RoomConnectHandler roomConnectHandler = new RoomConnectHandler(client);

        client.registerListener(roomConnectHandler);
        client.registerListener(roomCreateHandler);
        client.start();


        primaryStage.setScene(new Scene(wint));
        primaryStage.centerOnScreen();
        primaryStage.show();
        controller.setScene(scene);
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
