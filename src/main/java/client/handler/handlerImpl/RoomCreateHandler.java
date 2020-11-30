package client.handler.handlerImpl;



import client.logic.Client;
import client.handler.Handler;
import client.visualizer.EventListener;
import client.protocol.Message;
import client.protocol.Type;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
                System.out.println(text);
                for (EventListener eventListener : listeners) {
                    eventListener.onEventAction(text);
                }
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
