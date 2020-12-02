package typergame.server.handler.handlerImpl;

import lombok.extern.slf4j.Slf4j;
import typergame.server.Server;
import typergame.server.exception.ServerException;
import typergame.server.handler.Handler;
import typergame.server.handler.handlerImpl.helper.ObjectSerializer;
import typergame.server.model.Client;
import typergame.protocol.Message;
import typergame.server.model.Room;
import typergame.protocol.Type;

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
