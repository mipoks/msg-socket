package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.ObjectSerializer;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

import java.io.*;
import java.util.Arrays;

public class RoomCreateHandler implements Handler {
    private Server server;

    public RoomCreateHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        System.out.println("WE ARE CREATING ROOM");
        try {
            System.out.println("WE ARE CREATING ROOM2");
            Room room = new Room(Room.createRoomUniqueString());
            System.out.println("WE ARE CREATING ROOM3");
            room.addClient(client);
            System.out.println("WE ARE CREATING ROOM4");

            System.out.println(room.getRoomUniqueString());
            byte[] bytes = ObjectSerializer.toByteArray(room.getRoomUniqueString());
            System.out.println(Arrays.toString(bytes));

            Message answer = Message.createMessage(Type.ROOM_CREATE_ANSWER, bytes);
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
