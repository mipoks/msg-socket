package server.handler.implementation;

import server.Server;
import server.protocol.Type;

public class RoomOpenCloseHandler {
    private Server server;
    private Handler messageTransform;

    public RoomCreateHandler(Server server) {
        this.server = server;
        this.messageTransform = new MessageTransform();
    }

    @Override
    public void handleMessage(Client client, Message message) {
        System.out.println(new String(message.getData()));
        try {
            messageTransform.handleMessage(client, message);
            Room room = new Room(Room.createRoomUniqueString());
            room.addClient(client);
            Message answer = Message.createMessage(Type.ROOM_CREATE_ANSWER, room.getRoomUniqueString().getBytes());
            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            //Add some catch implementation
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_OPEN;
    }
}
