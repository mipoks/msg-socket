package client.handler.implementation;

import client.handler.Handler;
import client.protocol.Message;
import client.protocol.Type;

public class StateHandler implements Handler {
    @Override
    public void handleMessage(Message message) {

    }

    @Override
    public int getType() {
        return Type.STATE_ANSWER;
    }
}
