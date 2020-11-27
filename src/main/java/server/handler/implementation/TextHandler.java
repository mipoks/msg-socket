package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

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
        System.out.println(new String(message.getData()));
        try {

            messageTransform.handleMessage(client, message);
            Room room =/* Room.getRoomByUnique(message.getRoomUnique())*/null;
            if (room != null) {
                room.sendMessage(client, message);
            }
            Message answer = Message.createMessage(Type.TEXT_ANSWER, ByteBuffer.allocate(4).putInt(message.getData().length).array()/*, message.getRoomUnique()*/);
            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            //Add some catch implementation
        }
    }

    @Override
    public int getType() {
        return Type.TEXT;
    }
}
