package ru.itis.typergame.client.handler.handlerImpl;

import ru.itis.typergame.client.handler.Handler;
import ru.itis.typergame.client.logic.Client;
import ru.itis.typergame.client.visualizer.EventListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import ru.itis.typergame.protocol.Message;
import ru.itis.typergame.protocol.Record;
import ru.itis.typergame.protocol.Type;

public class RecordGetHandler implements Handler {

    private Client client;
    private ArrayList<EventListener> listeners;

    public RecordGetHandler(Client client) {
        this.client = client;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void handleMessage(Message message) {
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getData())) {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            if (object instanceof Collection) {
                Collection<Record> records = (Collection<Record>) object; //Text of game
                for (EventListener eventListener : listeners) {
                    eventListener.onEventAction(records);
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
        return Type.RECORD_GET_ANSWER;
    }
}
