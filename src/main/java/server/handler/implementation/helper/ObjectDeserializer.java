package server.handler.implementation.helper;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectDeserializer {
    public static Object fromByteArray(byte[] bytes) {
        Object object = null;
        try (ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(bytes)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayOutputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
