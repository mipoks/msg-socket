package client.visualizer;

import client.controllers.Controller;
import client.handler.EventListener;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class RivalPrinter implements EventListener<Pair> {
    private Text text;
    public RivalPrinter(Text text){
        this.text=text;
    }
    @Override
    public void onEventAction(Pair object) {
        System.out.println(object.getKey().toString()+object.getValue().toString());
        text.setText(String.valueOf((Integer)object.getKey()) +  (String) object.getValue());
    }
}
