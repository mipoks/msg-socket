package server.handler;

import server.protocol.Client;
import server.protocol.Message;

public interface Handler {
    void handleMessage(Client client, Message message);

    int getType();
}
