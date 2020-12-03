package typergame.client.message;

import lombok.extern.slf4j.Slf4j;
import typergame.protocol.Message;
import typergame.protocol.Type;

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
        return Message.createMessage(Type.ROOM_CREATE, bytes);
    }

    public static Message createRoomConnectMsg(String room) {
        return Message.createMessage(Type.ROOM_CONNECT, serialize(room));
    }

    public static Message createStartGameMsg() {
        return Message.createMessage(Type.GAME_START, new byte[1]);
    }

    public static Message createPlayGameMsg(String oneSymbol) {
        return Message.createMessage(Type.GAME_PLAY, serialize(oneSymbol));
    }

    public static Message createRoomPublicityMsg(boolean publicity) {
        return Message.createMessage(Type.ROOM_PUBLICITY_CHANGE, serialize(new Boolean(publicity)));
    }
    public static Message createRoomgetRecordsMsg(){
        return Message.createMessage(Type.RECORD_GET,new byte[1]);

    }

}
