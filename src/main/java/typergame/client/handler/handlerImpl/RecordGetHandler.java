package typergame.client.handler.handlerImpl;

import typergame.client.handler.Handler;
import typergame.client.logic.Client;
import typergame.client.visualizer.EventListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import typergame.protocol.Message;
import typergame.protocol.Record;
import typergame.protocol.Type;

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
