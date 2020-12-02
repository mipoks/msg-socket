package typergame.client.visualizer.eventListImpl;


import typergame.client.controllers.MainGameController;
import typergame.client.model.Room;
import typergame.client.util.ColorMixer;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import typergame.client.visualizer.EventListener;

@Slf4j
public class ColorMixPrinter implements EventListener<Pair> {
   private MainGameController mainGameController;
   private ObservableList<Node> filed;
   private Room room;
   private int position;
   private Text symb;
   private String paint;
   private Integer colorIndexHome;
   private Integer colorIndexServer;
   private  Integer  color;


   public  ColorMixPrinter(MainGameController controller,Room room){
       this.mainGameController = controller;
       filed = controller.getGameScreen().getChildren();
       this.room = room;
   }

    @Override
    public void onEventAction(Pair object) {
       position = (int)object.getValue()-1;
       log.info("Pair object key {}, value {}",object.getKey(),object.getValue());
        room.getGamers().ifPresent(x -> {
            x.stream().forEach(gamer -> {
                if(gamer.getId()==(int)object.getKey()){
                    symb = (Text)filed.get(position);
                    paint =symb.getFill().toString().substring(2,8);
                    colorIndexHome = Integer.parseInt(paint,16);
                    colorIndexServer = Integer.parseInt(ColorMixer.getLibraryColor().get(gamer),16);
                    symb.setFill(Paint.valueOf("FF2376"));
              /*      color = ((colorIndexHome*colorIndexServer+colorIndexHome*colorIndexHome)-colorIndexHome*colorIndexServer);
                    log.info("home color {}",Integer.toString(colorIndexHome,16));
                    log.info("server color {}",Integer.toString(colorIndexServer,16));
                    log.info("final color {}",Integer.toString(color,16).substring(1,7));
                    symb.setFill(Paint.valueOf(Integer.toString(color,16).substring(1,7)));*/



                }
            });
        });

    }
}
