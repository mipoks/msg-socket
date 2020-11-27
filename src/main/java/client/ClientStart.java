package client;

import client.controllers.Controller;
import client.handler.implementation.RoomCreateHandler;
import client.handler.implementation.TextHandler;
import client.logic.Client;
import client.message.MessageCreater;
import client.message.Text;
import client.protocol.Message;
import client.visualizer.RoomCodePrinter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class ClientStart extends Application {
    public TextField textField;
    public static void main(String[] args) throws IOException, IllegalAccessException {





        launch(args);
        Object object;

        Message msg = MessageCreater.createTextMsg(new Text("Hello world"));
        System.out.println();
    }

    @FXML
    TextField roomID;
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/socketSemestr.fxml"));
        Parent root = loader.load();
        Controller controller =(Controller) loader.getController();
        Client client = new Client(InetAddress.getByName("127.0.0.1"),4888);
        controller.setClient(client);
        client.connect();

        TextHandler textHandler = new TextHandler(client);


        client.registerListener(textHandler);
        client.registerListener(new RoomCreateHandler(client));
        client.start();




        Scene scene = new Scene(root );
//         = (TextField)scene.lookup("#roomID");
        RoomCodePrinter roomCodePrinter = new RoomCodePrinter(roomID);
        textHandler.addEventListener(roomCodePrinter);
        System.out.println("TEXTETXETXTE" + roomID);


        primaryStage.setScene(scene);
        primaryStage.show();

        textField = (TextField)scene.lookup("#roomID1");//Селектор для id и fx:id , берёт первое вхождение, если совпадений несколько
        textField.setText("SomeText");
        System.out.println(""+ textField.toString());
    }
}
