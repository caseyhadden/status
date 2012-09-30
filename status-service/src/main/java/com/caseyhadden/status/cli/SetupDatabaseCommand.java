package com.caseyhadden.status.cli;

import com.caseyhadden.status.StatusConfiguration;
import com.caseyhadden.status.db.StatusDao;
import com.yammer.dropwizard.AbstractService;
import com.yammer.dropwizard.cli.ConfiguredCommand;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.Database;
import com.yammer.dropwizard.db.DatabaseFactory;
import com.yammer.dropwizard.logging.Log;
import org.apache.commons.cli.CommandLine;

public class SetupDatabaseCommand extends ConfiguredCommand<StatusConfiguration> {

    public SetupDatabaseCommand() {
        super("setup", "Setup the database.");
    }

    @Override
    public void run(AbstractService<StatusConfiguration> service,
                    StatusConfiguration configuration, CommandLine params) throws Exception {
        Log log = Log.forClass(SetupDatabaseCommand.class);
        Environment environment = new Environment(service, configuration);
        DatabaseFactory factory = new DatabaseFactory(environment);
        Database db = factory.build(configuration.getDatabaseConfiguration(), "h2");
        StatusDao dao = db.onDemand(StatusDao.class);

        log.info("Creating tables...");
        dao.createTable();
    }

}
