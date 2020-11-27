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
    public static Message createTextMsg(Text text) throws IOException, IllegalAccessException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(text);
//        objectOutputStream.flush();
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        objectOutputStream.close();
//        byteArrayOutputStream.close();
        //ToDo проверить, что так работает
        byte[] bytes = serialize(text);
        return Message.createMessage(Type.TEXT, bytes);
    }

    public static Message createStateMsg(State state) throws IOException, IllegalAccessException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(state);
//        objectOutputStream.flush();
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        objectOutputStream.close();
//        byteArrayOutputStream.close();
        //ToDo проверить, что так работает
        byte[] bytes = serialize(state);
        return Message.createMessage(Type.STATE, bytes);
    }


}
