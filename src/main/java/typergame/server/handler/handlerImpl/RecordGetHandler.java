package typergame.server.handler.handlerImpl;

import typergame.server.Server;
import typergame.server.exception.ServerException;
import typergame.server.handler.Handler;
import typergame.server.handler.handlerImpl.helper.ObjectSerializer;
import typergame.server.model.Client;
import typergame.protocol.Record;
import typergame.protocol.Message;
import typergame.protocol.Type;
import typergame.server.repository.RecordRepository;

import java.util.Collection;

public class RecordGetHandler implements Handler {
    private Server server;
    private RecordRepository recordRepository;

    public RecordGetHandler(Server server, RecordRepository recordRepository) {
        this.server = server;
        this.recordRepository = recordRepository;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        try {
            Collection<Record> records = recordRepository.getAll();
            Message msgAns = Message.createMessage(Type.RECORD_GET_ANSWER, ObjectSerializer.toByteArray(records));
            server.sendMessage(client, msgAns);
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getType() {
        return Type.RECORD_GET;
    }
}
