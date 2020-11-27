package client.message;

import client.message.Text;
import client.protocol.Message;
import client.protocol.Type;

import java.io.*;

public class MessageCreater {

    private static byte[] serialize(Object object) {
        byte[] bytes = new byte[1];
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    public static Message createTextMsg(Text text) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(text);
//        objectOutputStream.flush();
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        objectOutputStream.close();
//        byteArrayOutputStream.close();
        //ToDo проверить, что так работает
        byte[] bytes = serialize(text);
        try {
            return Message.createMessage(Type.TEXT, bytes);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Message createStateMsg(State state) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(state);
//        objectOutputStream.flush();
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        objectOutputStream.close();
//        byteArrayOutputStream.close();
        //ToDo проверить, что так работает
        byte[] bytes = serialize(state);
        try {
            return Message.createMessage(Type.STATE, bytes);
        } catch (IllegalAccessException e) {
            throw  new IllegalStateException(e);
        }
    }

    public static Message createRoomCreateMsg() throws IllegalAccessException {
        byte[] bytes = new byte[1];
        return Message.createMessage(Type.ROOM_CREATE, bytes);
     }

     public static Message createRoomConnectMsg(String room) throws IllegalAccessException {
        return Message.createMessage(Type.ROOM_CONNECT, serialize(room));
     }
}
