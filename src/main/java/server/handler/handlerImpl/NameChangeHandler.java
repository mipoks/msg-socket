package server.handler.handlerImpl;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.handlerImpl.helper.ObjectSerializer;
import server.handler.handlerImpl.helper.ObjectDeserializer;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Type;

@Slf4j
public class NameChangeHandler implements Handler {
    private Server server;

    public NameChangeHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        try {
            client.setName((String)(ObjectDeserializer.fromByteArray(message.getData())));

            Message msgAns = Message.createMessage(Type.NAME_CHANGE_ANSWER, message.getData());
            server.sendMessage(client, msgAns);

            if (client.getRoom().isPresent()) {
                msgAns.setData(ObjectSerializer.toByteArray(new Pair<Integer, String>(client.getId(), client.getName())));
                msgAns.setType(Type.NAME_CHANGE);
                client.getRoom().get().sendMessage(message);
            }
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.NAME_CHANGE;
    }
}
