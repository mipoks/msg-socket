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

public class RoomOpenCloseHandler implements Handler {
    private Server server;
    private Handler messageTransform;

    public RoomOpenCloseHandler(Server server) {
        this.server = server;
        this.messageTransform = new MessageTransform();
    }

    @Override
    public void handleMessage(Client client, Message message) {
        try {
            messageTransform.handleMessage(client, message);
            Room room = client.getRoom();
            if (room == null)
                return;

            byte[] bytes = ByteArrayGiver.toByteArray(room.getRoomUniqueString());

            Message answer = null;
            if ((message.getType() & Type.ROOM_OPEN) == 1) {
                room.setPublicity(true);
                answer = Message.createMessage(Type.ROOM_OPEN, bytes);
            }
            if ((message.getType() & Type.ROOM_CLOSE) == 1) {
                room.setPublicity(false);
                answer = Message.createMessage(Type.ROOM_CLOSE, bytes);
            }
            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            //Add some catch implementation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_OPEN << 8 | Type.ROOM_CLOSE;
    }
}
