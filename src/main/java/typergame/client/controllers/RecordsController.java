package typergame.client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class RecordsController implements Initializable {
    @FXML
    private VBox body;
    @FXML
    @Setter
    @Getter
    private TextFlow recordsScreen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
