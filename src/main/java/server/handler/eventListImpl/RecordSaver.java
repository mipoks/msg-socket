package server.handler.eventListImpl;

import server.repository.RecordRepository;
import server.repository.database.ConnectionGiver;
import server.repository.database.JdbcTemplate;
import server.repository.database.RowMapper;
import server.repository.database.implementation.ConnectionLocal;
import server.repository.database.implementation.RowMapperImpl;
import server.model.Record;
import server.model.implementation.RecordCreater;
import server.model.EntityCreater;
import server.handler.EventListener;
import server.protocol.Client;
import server.protocol.Game;
import server.repository.impl.RecordRepoImpl;

public class RecordSaver implements EventListener {

    private Game game;
    private static RecordRepository recordRepository = new RecordRepoImpl();

    public RecordSaver(Game game) {
        this.game = game;
    }

    @Override
    public void onEventAction(Object object) {
        if (object instanceof Client) {
            Client client = (Client) object;
            long cpersec = game.getGameText().length() /
                    (client.getEndTime().getTime() - client.getStartTime().getTime());
            Record record = new Record(client.getName(), cpersec);
            recordRepository.save(record);
        }
    }
}
