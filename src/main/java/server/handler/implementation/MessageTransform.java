package server.handler.implementation;

import server.handler.Handler;
import server.protocol.Client;
import server.protocol.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MessageTransform implements Handler {
    @Override
    public void handleMessage(Client client, Message message) {
        String from = client.getName() + ": ";
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(from.getBytes("UTF-8"));
            outputStream.write(message.getData());
            message.setData(outputStream.toByteArray());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return 0;
    }
}
