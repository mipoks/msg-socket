package server.handler.eventListImpl;

import server.repository.RecordRepository;
import server.model.Record;
import server.handler.EventListener;
import server.model.Client;
import server.model.Game;
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
