package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.ByteArrayGiver;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TextHandler implements Handler {

    private Server server;
    private Handler messageTransform;

    public TextHandler(Server server) {
        this.server = server;
        this.messageTransform = new MessageTransform();
    }

    @Override
    public void handleMessage(Client client, Message message) {
        //ToDo удалить нахрен этот класс + MessageTransform. Мы пишем НЕ чат.
        System.out.println(new String(message.getData()));
        try {

            messageTransform.handleMessage(client, message);
            Room room = client.getRoom();
            if (room != null) {
                room.sendMessage(client, message);
            }
            Message answer = Message.createMessage(Type.TEXT_ANSWER, ByteArrayGiver.toByteArray("accepted"));
            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            //Add some catch implementation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.TEXT;
    }
}
