package typergame.server.handler;

import typergame.protocol.Message;
import typergame.server.model.Client;

public interface Handler {
    void handleMessage(Client client, Message message);

    int getType();
}
