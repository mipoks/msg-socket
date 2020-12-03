package typergame.server.repository.impl;

import typergame.server.model.EntityCreater;
import typergame.protocol.Record;
import typergame.server.model.implementation.RecordCreater;
import typergame.server.repository.RecordRepository;
import typergame.server.repository.database.ConnectionGiver;
import typergame.server.repository.database.JdbcTemplate;
import typergame.server.repository.database.RowMapper;
import typergame.server.repository.database.implementation.ConnectionLocal;
import typergame.server.repository.database.implementation.RowMapperImpl;

import java.util.Collection;

public class RecordRepoImpl implements RecordRepository {

    private ConnectionGiver connectionGiver = new ConnectionLocal();
    private final JdbcTemplate<Record> jdbcTemplate;
    private final RowMapper<Record> rowMapper;
    private EntityCreater<Record> entityCreater;

    //language=sql
    private String SQL_INSERT_RECORD = "INSERT INTO records (name, cpersec) VALUES (?, ?)";
    //language=sql
    private String SQL_SELECT_LIMIT_15 = "SELECT name, cpersec FROM records ORDER BY cpersec DESC LIMIT 15";


    public RecordRepoImpl() {
        jdbcTemplate = new JdbcTemplate(connectionGiver);
        entityCreater = new RecordCreater();
        rowMapper = new RowMapperImpl<Record>(entityCreater);
    }

    public int save(Record record) {
        String name = record.getName();
        double time = record.getTime();
        jdbcTemplate.query(SQL_INSERT_RECORD, rowMapper, name, time);
        return rowMapper.getResult();
    }

    @Override
    public Collection<Record> getAll() {
        Collection<Record> records = jdbcTemplate.query(SQL_SELECT_LIMIT_15, rowMapper);
        return records;
    }
}
