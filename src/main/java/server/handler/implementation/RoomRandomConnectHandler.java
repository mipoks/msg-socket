package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.ByteArrayGiver;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;

import java.io.IOException;

public class RoomRandomConnectHandler implements Handler {

    private Server server;
    private Handler messageTransform;

    public RoomRandomConnectHandler(Server server) {
        this.server = server;
        this.messageTransform = new MessageTransform();
    }

    public void handleMessage(Client client, Message message) {
        try {
            Room room = Room.getOpenRoom();
            messageTransform.handleMessage(client, message);
            room.addClient(client);
            client.setRoom(room);

            byte[] bytes = ByteArrayGiver.toByteArray("connected to room");
            message.setType(message.getType() * -1);
            message.setData(bytes);
            server.sendMessage(client, message);

            bytes = ByteArrayGiver.toByteArray(client.getName() + " connected to room");
            message.setData(bytes);
            room.sendMessage(message);
        } catch (ServerException ex) {
            //Add some catch implementation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return 0;
    }
}
