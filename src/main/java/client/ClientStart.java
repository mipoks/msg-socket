package client;

import client.controllers.Controller;
import client.handler.implementation.RoomCreateHandler;
import client.handler.implementation.TextHandler;
import client.logic.Client;
import client.message.MessageCreater;
import client.message.Text;
import client.protocol.Message;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class ClientStart extends Application {
    public static void main(String[] args) throws IOException, IllegalAccessException {





        launch(args);
        Object object;

        Message msg = MessageCreater.createTextMsg(new Text("Hello world"));
        System.out.println();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/socketSemestr.fxml"));
        Parent root = loader.load();
        Controller controller =(Controller) loader.getController();
        Client client = new Client(InetAddress.getByName("127.0.0.1"),4888);
        controller.setClient(client);
        client.connect();
        client.registerListener(new TextHandler(client));
        client.registerListener(new RoomCreateHandler(client));
        client.start();






        Scene scene = new Scene(root );
        TextField textField = (TextField)scene.lookup("roomID");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
