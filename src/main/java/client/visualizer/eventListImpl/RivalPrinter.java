package client.visualizer.eventListImpl;

import client.visualizer.EventListener;
import javafx.scene.text.Text;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RivalPrinter implements EventListener<Pair> {
    private Text text;
    public RivalPrinter(Text text){
        log.info("Hello from rival printer ");
        this.text=text;
    }
    @Override
    public void onEventAction(Pair object) {
        log.info("Hello from rival printer METHOD");
        System.out.println(object.getKey().toString()+object.getValue().toString());
        text.setText(String.valueOf((Integer)object.getKey()) +  (String) object.getValue());
    }
}
