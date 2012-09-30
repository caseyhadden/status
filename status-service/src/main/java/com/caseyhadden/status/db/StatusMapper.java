package com.caseyhadden.status.db;

import static com.google.common.base.Throwables.propagate;

import com.caseyhadden.status.api.Status;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yammer.dropwizard.json.Json;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class StatusMapper implements ResultSetMapper<Status> {

    Json json = new Json();

    public Status map(int i, ResultSet resultSet, StatementContext statementContext)
        throws SQLException {
        try {
            return json.readValue(resultSet.getString("json"), Status.class);
        } catch (IOException e) {
            throw propagate(e);
        }
    }


}
