package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.ByteArrayGiver;
import server.handler.implementation.helper.ObjectDeserializer;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

import java.io.IOException;

public class RoomConnectHandler implements Handler {

    private Server server;

    public RoomConnectHandler(Server server) {
        this.server = server;
    }

    public void handleMessage(Client client, Message message) {
        System.out.println("BLABALBAL");
        System.out.println("KAKAKA" + (String)(ObjectDeserializer.deserialize(message.getData())));
        try {
            //ToDo обернуть new String() в try catch

            Room room = Room.getRoomByUnique((String)(ObjectDeserializer.deserialize(message.getData())));
            if (room == null)
                return;
            room.addClient(client);
            client.setRoom(room);

            message.setType(Type.ROOM_CONNECT_ANSWER);
            message.setData(ByteArrayGiver.toByteArray("connected to room"));
            server.sendMessage(client, message);
            message.setData(ByteArrayGiver.toByteArray(client.getName() + " connected to room"));
            room.sendMessage(message);
        } catch (ServerException ex) {
            //Add some catch implementation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CONNECT;
    }
}
