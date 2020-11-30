package server.repository.impl;

import server.model.EntityCreater;
import server.model.Record;
import server.model.implementation.RecordCreater;
import server.repository.RecordRepository;
import server.repository.database.ConnectionGiver;
import server.repository.database.JdbcTemplate;
import server.repository.database.RowMapper;
import server.repository.database.implementation.ConnectionLocal;
import server.repository.database.implementation.RowMapperImpl;

import java.util.Collection;

public class RecordRepoImpl implements RecordRepository {

    private ConnectionGiver connectionGiver = new ConnectionLocal();
    private final JdbcTemplate<Record> jdbcTemplate;
    private final RowMapper<Record> rowMapper;
    private EntityCreater<Record> entityCreater;

    //language=sql
    private String SQL_INSERT_RECORD = "INSERT INTO records (name, cpersec) VALUES (?, ?)";
    //language=sql
    private String SQL_SELECT_LIMIT_15 = "SELECT name, cpersec FROM records LIMIT 15 ORDER BY cpersec DESC";


    public RecordRepoImpl() {
        jdbcTemplate = new JdbcTemplate(connectionGiver);
        entityCreater = new RecordCreater();
        rowMapper = new RowMapperImpl<Record>(entityCreater);
    }

    public int save(Record record) {
        String name = record.getName();
        long time = record.getTime();
        jdbcTemplate.query(SQL_INSERT_RECORD, rowMapper, name, time);
        return rowMapper.getResult();
    }

    @Override
    public Collection<Record> getAll() {
        Collection<Record> records = jdbcTemplate.query(SQL_SELECT_LIMIT_15, rowMapper);
        return records;
    }
}
