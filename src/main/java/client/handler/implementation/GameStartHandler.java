package client.handler.implementation;

import client.handler.EventListener;
import client.logic.Client;
import lombok.extern.slf4j.Slf4j;
import server.handler.Handler;
import server.protocol.Message;
import server.protocol.Type;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
@Slf4j
public class GameStartHandler implements Handler {
    private Client client;
    private ArrayList<EventListener> listeners;

    public GameStartHandler(Client client) {
        this.client = client;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void handleMessage(server.protocol.Client client, Message message) {
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getData())) {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            if (object instanceof String) {
                String text = (String) object; //Text of game
                for (EventListener eventListener : listeners) {
                    eventListener.onEventAction(text);
                }
            }
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.GAME_START_ANSWER;
    }
}
