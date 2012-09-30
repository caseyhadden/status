package com.caseyhadden.status.db;

import com.caseyhadden.status.api.Status;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(StatusMapper.class)
public interface StatusDao {

    @SqlUpdate("create table if not exists statuses (id varchar(36) primary key, json text)")
    void createTable();

    @SqlQuery("select json from statuses")
    List<Status> findALl();

    @SqlQuery("select json from statuses where id = :id")
    Status findById(@Bind("id") String id);

    @SqlUpdate("insert into statuses (id, json) values (:id, :json)")
    void insert(@StatusBind Status status);

    @SqlUpdate("delete from statuses where id = :id")
    void delete(@Bind("id") String id);

    @SqlUpdate("delete from statuses")
    void deleteAll();
}

