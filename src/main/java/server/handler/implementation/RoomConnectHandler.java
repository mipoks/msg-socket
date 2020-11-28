package server.handler.implementation;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.ObjectSerializer;
import server.handler.implementation.helper.ObjectDeserializer;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

import java.io.IOException;
import java.util.Optional;
@Slf4j
public class RoomConnectHandler implements Handler {

    private Server server;

    public RoomConnectHandler(Server server) {
        this.server = server;
    }

    public void handleMessage(Client client, Message message) {
        log.info("BLABALBAL");
        log.info("string res : {}", (String)(ObjectDeserializer.fromByteArray(message.getData())));
        try {
            //ToDo обернуть new String() в try catch

            String roomName = (String)(ObjectDeserializer.fromByteArray(message.getData()));
            Optional<Room> room = Room.getRoomByUnique(roomName);
            if (!room.isPresent())
                return;
            room.get().addClient(client);
            client.setRoom(room.get());



            Message msg = Message.createMessage(Type.ROOM_CONNECT, ObjectSerializer.toByteArray(
                    new Pair<Integer, String>(client.getId(), client.getName())));
            room.get().sendMessageExcept(client, msg);

            msg.setType(Type.ROOM_CONNECT_ANSWER);
            msg.setData(ObjectSerializer.toByteArray(new Integer(client.getId())));
            server.sendMessage(client, msg);
        } catch (ServerException ex) {
            //Add some catch implementation
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CONNECT;
    }
}
