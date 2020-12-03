package typergame.client.visualizer.eventListImpl;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;
import typergame.client.controllers.RecordsController;
import typergame.client.handler.Handler;
import typergame.client.visualizer.EventListener;
import typergame.protocol.Message;
import typergame.protocol.Record;

import java.util.Collection;

public class RecordsCodePrinter implements EventListener<Collection<Record>> {
    private RecordsController recordsController;
    private TextFlow textField;
    private Text text;
    public RecordsCodePrinter(RecordsController recordsController){
        this.recordsController = recordsController;
        textField =(TextFlow) recordsController.getRecordsScreen();
    }

    @Override
    public void onEventAction(Collection<Record> object) {
        textField.getChildren().remove(0,textField.getChildren().size());

        object.forEach(x->{
            text = new Text(x.getName()+x.getTime());
            text.setFont(Font.font(34));


            textField.getChildren().add(text);});

    }
}
