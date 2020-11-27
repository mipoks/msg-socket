package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

public class RoomConnectHandler implements Handler {

    private Server server;
    private Handler messageTransform;

    public RoomConnectHandler(Server server) {
        this.server = server;
        this.messageTransform = new MessageTransform();
    }

    public void handleMessage(Client client, Message message) {
        System.out.println(new String(message.getData()));
        try {

            messageTransform.handleMessage(client, message);
            Room room = Room.getRoomByUnique(new String(message.getData()));
            if (room == null)
                return;
            room.addClient(client);

            message.setType(message.getType() * -1);
            message.setData(("connected to room").getBytes());
            server.sendMessage(client, message);
            message.setData((client.getName() + " connected to room").getBytes());
            room.sendMessage(message);
        } catch (ServerException ex) {
            //Add some catch implementation
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CONNECT;
    }
}
