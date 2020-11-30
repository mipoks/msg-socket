package server.handler.implementation;

import lombok.extern.slf4j.Slf4j;
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
