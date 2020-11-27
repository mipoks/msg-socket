package client.handler.implementation;



import client.logic.Client;
import client.handler.Handler;
import client.handler.EventListener;
import client.protocol.Message;
import client.protocol.Type;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

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
            e.printStackTrace();
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
