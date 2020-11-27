package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.ByteArrayGiver;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class RoomCreateHandler implements Handler {
    private Server server;

    public RoomCreateHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        System.out.println(new String(message.getData()));
        try {
            Room room = new Room(Room.createRoomUniqueString());
            room.addClient(client);

            byte[] bytes = ByteArrayGiver.toByteArray(room.getRoomUniqueString());
            System.out.println(Arrays.toString(bytes));

            Message answer = Message.createMessage(Type.ROOM_CREATE_ANSWER, bytes);
            server.sendMessage(client, answer);
        } catch (ServerException ex) {
            //Add some catch implementation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CREATE;
    }
}
