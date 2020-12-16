package ru.itis.typergame.server.handler.handlerImpl;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import ru.itis.typergame.protocol.Message;
import ru.itis.typergame.protocol.Type;
import ru.itis.typergame.server.Server;
import ru.itis.typergame.server.exception.ServerException;
import ru.itis.typergame.server.handler.Handler;
import ru.itis.typergame.server.handler.handlerImpl.helper.ObjectSerializer;
import ru.itis.typergame.server.model.Client;
import ru.itis.typergame.server.model.Room;

import java.util.Arrays;

@Slf4j
public class RoomCreateHandler implements Handler {
    private Server server;

    public RoomCreateHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        try {
            Room room = new Room(Room.createRoomUniqueString());
            room.addClient(client);
            if (client.getRoom().isPresent()) {
                Room roomTmp = client.getRoom().get();
                roomTmp.removeClient(client);
            }
            client.setRoom(room);
            log.info("room.getRoomUniqueString() result :  {}",room.getRoomUniqueString());
            byte[] bytes = ObjectSerializer.toByteArray(room.getRoomUniqueString());
            log.info("Array bytes{}",Arrays.toString(bytes));

            Message answer = Message.createMessage(Type.ROOM_CREATE_ANSWER, bytes);
            server.sendMessage(client, answer);

            answer = Message.createMessage(Type.ROOM_CONNECT_ANSWER,
                    ObjectSerializer.toByteArray(new Pair<Integer, String>(client.getId(), client.getName())));
            server.sendMessage(client, answer);

            if (room.getRoomOwner().get() == client) {
                Message roomOwner = Message.createMessage(Type.ROOM_OWNER_ANSWER, ObjectSerializer.toByteArray(client.getId()));
                server.sendMessage(client, roomOwner);
            }
        } catch (ServerException ex) {
            log.info(ex.getMessage());
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CREATE;
    }
}
