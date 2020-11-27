package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.ByteArrayGiver;
import server.handler.implementation.helper.ObjectDeserializer;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Type;

import java.io.IOException;

public class NameChangeHandler implements Handler {
    private Server server;

    public NameChangeHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        try {
            String oldName = client.getName();
            client.setName((String)(ObjectDeserializer.deserialize(message.getData())));

            message.setType(Type.NAME_CHANGE_ANSWER);
            server.sendMessage(client, message);

            if (client.getRoom().isPresent()) {
                String msg = oldName + " changed nickname to " + (String)(ObjectDeserializer.deserialize(message.getData()));
                message.setData(ByteArrayGiver.toByteArray(msg));
                client.getRoom().get().sendMessage(message);
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.NAME_CHANGE;
    }
}
