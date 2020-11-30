package server.handler.handlerImpl;

import lombok.extern.slf4j.Slf4j;
import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.handlerImpl.helper.ObjectSerializer;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

@Slf4j
public class RoomCloseHandler implements Handler {
    private Server server;

    public RoomCloseHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        try {
            if (!client.getRoom().isPresent())
                return;
            Room room = client.getRoom().get();

            byte[] bytes = ObjectSerializer.toByteArray(room.getRoomUniqueString());

            room.setPublicity(false);
            Message answer = Message.createMessage(Type.ROOM_CLOSE_ANSWER, bytes);

            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CLOSE;
    }
}
