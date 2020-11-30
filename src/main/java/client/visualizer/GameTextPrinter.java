package client.visualizer;

import client.handler.EventListener;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Arrays;

public class GameTextPrinter implements EventListener<String>
{
    private TextFlow textFlow;
    private String[] textArray;
    private Text utillText;

    public GameTextPrinter(TextFlow textFlow){
        this.textFlow = textFlow;
    }
    @Override
    public void onEventAction(String object)    {
        textArray =object.split("");
    /*    Arrays.stream(textArray).forEach(x->{
            utillText = new Text(x);
            utillText.setFont(Font.font(18));
            textList.add(utillText);
            gameScreen.getChildren().add(utillText);
        });*/
        System.out.println(Arrays.toString(textArray));
    }
}
