package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.ByteArrayGiver;
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
            client.setName(new String(message.getData()));

            message.setType(message.getType() * -1);
            server.sendMessage(client, message);

            if (client.getRoom() != null) {
                String msg = oldName + " changed nickname to " + new String(message.getData());
                message.setData(ByteArrayGiver.toByteArray(msg));
                client.getRoom().sendMessage(message);
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
