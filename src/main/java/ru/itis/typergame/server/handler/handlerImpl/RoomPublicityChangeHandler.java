package ru.itis.typergame.server.handler.handlerImpl;

import ru.itis.typergame.server.Server;
import ru.itis.typergame.server.exception.ServerException;
import ru.itis.typergame.server.handler.Handler;
import ru.itis.typergame.server.handler.handlerImpl.helper.ObjectDeserializer;
import ru.itis.typergame.server.model.Client;
import ru.itis.typergame.server.model.Room;
import ru.itis.typergame.protocol.Message;
import ru.itis.typergame.protocol.Type;

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
