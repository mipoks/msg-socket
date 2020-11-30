package server.handler.handlerImpl;

import javafx.util.Pair;
import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.handlerImpl.helper.ObjectDeserializer;
import server.handler.handlerImpl.helper.ObjectSerializer;
import server.model.Client;
import server.model.Game;
import server.model.Room;
import server.protocol.Message;
import server.protocol.Type;

import java.util.Optional;

public class RoomPublicityChangeHandler implements Handler {
    private Server server;

    public RoomPublicityChangeHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {

        try {
            if (client.getRoom().isPresent())
                return;
            Room room = client.getRoom().get();
            if (!room.getRoomOwner().isPresent() || !room.getRoomOwner().get().equals(client))
                return;

            boolean publicity = (Boolean) ObjectDeserializer.fromByteArray(message.getData());
            room.setPublicity(publicity);
            Message answer = Message.createMessage(Type.ROOM_PUBLICITY_CHANGE_ANSWER, message.getData());

            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_PUBLICITY_CHANGE;
    }
}
