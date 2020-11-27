package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

import java.io.*;
import java.nio.ByteBuffer;

public class RoomCreateHandler implements Handler {
    private Server server;
    private Handler messageTransform;

    public RoomCreateHandler(Server server) {
        this.server = server;
        this.messageTransform = new MessageTransform();
    }

    @Override
    public void handleMessage(Client client, Message message) {
        System.out.println(new String(message.getData()));
        try {
            messageTransform.handleMessage(client, message);
            Room room = new Room(Room.createRoomUniqueString());
            room.addClient(client);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(out);
            objectOutputStream.writeObject(room.getRoomUniqueString().getBytes());
            objectOutputStream.flush();




            Message answer = Message.createMessage(Type.ROOM_CREATE_ANSWER, out.toByteArray());
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
