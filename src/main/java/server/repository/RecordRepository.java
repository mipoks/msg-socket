package server.repository;

import server.model.Record;

import java.util.Collection;

public interface RecordRepository {
    int save(Record record);
    Collection<Record> getAll();
}
