package typergame.client.handler;

import typergame.protocol.Message;

public interface Handler {
    void handleMessage(Message message);

    int getType();
}
