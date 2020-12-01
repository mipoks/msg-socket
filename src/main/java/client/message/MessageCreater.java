package client.message;

import client.protocol.Message;
import client.protocol.Type;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class MessageCreater {

    private static byte[] serialize(Object object) {
        byte[] bytes = new byte[1];
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

//    public static Message createTextMsg(Text text) {
//        byte[] bytes = serialize(text);
//        try {
//            return Message.createMessage(Type.TEXT, bytes);
//        } catch (IllegalAccessException e) {
//            throw new IllegalStateException(e);
//        }
//    }

    public static Message createRoomCreateMsg() {
        byte[] bytes = new byte[1];
        try {
            return Message.createMessage(Type.ROOM_CREATE, bytes);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Message createRoomConnectMsg(String room) {
        try {
            return Message.createMessage(Type.ROOM_CONNECT, serialize(room));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Message createStartGameMsg() {
        try {
            return Message.createMessage(Type.GAME_START, new byte[1]);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Message createPlayGameMsg(String oneSymbol) {
        try {
            return Message.createMessage(Type.GAME_PLAY, serialize(oneSymbol));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Message createRoomPublicityMsg(boolean publicity) {
        try {
            return Message.createMessage(Type.ROOM_PUBLICITY_CHANGE, serialize(new Boolean(publicity)));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

}
