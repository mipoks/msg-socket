package typergame.client.handler.handlerImpl;



import typergame.client.logic.Client;
import typergame.client.handler.Handler;
import typergame.client.model.Room;
import typergame.client.visualizer.EventListener;
import lombok.extern.slf4j.Slf4j;
import typergame.protocol.Message;
import typergame.protocol.Type;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class RoomCreateHandler implements Handler {

    private Client client;
    private ArrayList<EventListener> listeners;

    public RoomCreateHandler(Client client) {
        this.client = client;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void handleMessage(Message message) {
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getData())) {

            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            if (object instanceof String) {
                String text = (String) object;

                //new code
                //copy all listeners of old room
                Room room = Room.getActualRoom();
                Collection<EventListener> listeners = room.getAllListeners();
                //creating room
                room = Room.createNewRoom();
                room.setRoomUnique(text);
                for (EventListener eventListener : listeners) {
                    room.addEventListener(eventListener);
                }
                log.info("GAMERS IN ROOM:" + room.getGamers().get().toString());


          /*      //old code
                for (EventListener eventListener : listeners) {
                    eventListener.onEventAction(text);
                }*/
            }
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addEventListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public int getType() {
        return Type.ROOM_CREATE_ANSWER;
    }
}
