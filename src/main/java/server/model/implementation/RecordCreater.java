package server.model.implementation;

import server.model.Record;
import server.model.EntityCreater;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordCreater implements EntityCreater {
    public Record create(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        Long time = resultSet.getLong("time");
        Record clientDTO = new Record(name, time);
        return clientDTO;
    }
}
