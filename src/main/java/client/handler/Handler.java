package client.handler;

import client.protocol.Message;

public interface Handler {
    void handleMessage(Message message);

    int getType();
}
