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

public class RoomOpenHandler implements Handler {
    private Server server;

    public RoomOpenHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        try {
            if (client.getRoom().isPresent())
                return;
            Room room = client.getRoom().get();
            byte[] bytes = ByteArrayGiver.toByteArray(room.getRoomUniqueString());

            room.setPublicity(true);
            Message answer = Message.createMessage(Type.ROOM_OPEN_ANSWER, bytes);

            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            //Add some catch implementation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_OPEN;
    }
}
