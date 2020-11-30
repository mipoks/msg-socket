package server.handler.handlerImpl;

import lombok.extern.slf4j.Slf4j;
import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.handlerImpl.helper.ObjectSerializer;
import server.model.Client;
import server.protocol.Message;
import server.model.Room;
import server.protocol.Type;

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
