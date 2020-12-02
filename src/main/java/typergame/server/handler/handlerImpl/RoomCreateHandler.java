package typergame.server.handler.handlerImpl;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import typergame.server.Server;
import typergame.server.exception.ServerException;
import typergame.server.handler.Handler;
import typergame.server.handler.handlerImpl.helper.ObjectSerializer;
import typergame.server.model.Client;
import typergame.protocol.Message;
import typergame.server.model.Room;
import typergame.protocol.Type;

import java.util.Arrays;
@Slf4j
public class RoomCreateHandler implements Handler {
    private Server server;

    public RoomCreateHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        log.info("WE ARE CREATING ROOM");
        try {
            log.info("WE ARE CREATING ROOM2");
            Room room = new Room(Room.createRoomUniqueString());
            log.info("WE ARE CREATING ROOM3");
            room.addClient(client);
            client.setRoom(room);
            log.info("WE ARE CREATING ROOM4");

            log.info("room.getRoomUniqueString() result :  {}",room.getRoomUniqueString());
            byte[] bytes = ObjectSerializer.toByteArray(room.getRoomUniqueString());
            log.info("Array bytes{}",Arrays.toString(bytes));

            Message answer = Message.createMessage(Type.ROOM_CREATE_ANSWER, bytes);
            server.sendMessage(client, answer);

            answer = Message.createMessage(Type.ROOM_CONNECT_ANSWER,
                    ObjectSerializer.toByteArray(new Pair<Integer, String>(client.getId(), client.getName())));
            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            ex.printStackTrace();
            //Add some catch implementation
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CREATE;
    }
}
