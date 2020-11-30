package server.handler;

import server.model.Client;
import server.protocol.Message;

public interface Handler {
    void handleMessage(Client client, Message message);

    int getType();
}
