package ru.itis.typergame.server.handler.handlerImpl;

import lombok.extern.slf4j.Slf4j;
import ru.itis.typergame.server.Server;
import ru.itis.typergame.server.exception.ServerException;
import ru.itis.typergame.server.handler.Handler;
import ru.itis.typergame.server.handler.handlerImpl.helper.ObjectSerializer;
import ru.itis.typergame.server.model.Client;
import ru.itis.typergame.protocol.Message;
import ru.itis.typergame.server.model.Room;
import ru.itis.typergame.protocol.Type;

@Slf4j
public class RoomRandomConnectHandler implements Handler {

    private Server server;

    public RoomRandomConnectHandler(Server server) {
        this.server = server;
    }

    public void handleMessage(Client client, Message message) {
        try {
            Room room = Room.getOpenRoom();
            room.addClient(client);
            client.setRoom(room);

            byte[] bytes = ObjectSerializer.toByteArray("connected to room");
            message.setType(Type.ROOM_CONNECT_RAND_ANSWER);
            message.setData(bytes);
            server.sendMessage(client, message);

            bytes = ObjectSerializer.toByteArray(client.getName() + " connected to room");
            message.setData(bytes);
            room.sendMessage(message);
        } catch (ServerException ex) {
            //Add some catch implementation
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CONNECT_RAND;
    }
}
