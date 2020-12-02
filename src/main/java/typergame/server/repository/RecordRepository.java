package typergame.server.repository;

import typergame.protocol.Record;

import java.util.Collection;

public interface RecordRepository {
    int save(Record record);
    Collection<Record> getAll();
}
