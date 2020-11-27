
import client.message.MessageCreater;
import client.message.Text;
import client.protocol.Message;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
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
        Scene scene = new Scene(root, 400,400 );

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
