package com.caseyhadden.status;

import com.caseyhadden.status.cli.SetupDatabaseCommand;
import com.caseyhadden.status.db.StatusDao;
import com.caseyhadden.status.resources.RootResource;
import com.caseyhadden.status.resources.StatusResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.Database;
import com.yammer.dropwizard.db.DatabaseFactory;

public class StatusService extends Service<StatusConfiguration> {

    public static void main(String[] args) throws Exception {
        new StatusService().run(args);
    }

    private StatusService() {
        super("status");
        addCommand(new SetupDatabaseCommand());
    }

    @Override
    protected void initialize(StatusConfiguration configuration,
                              Environment environment) throws ClassNotFoundException {
        DatabaseFactory factory = new DatabaseFactory(environment);
        Database db = factory.build(configuration.getDatabaseConfiguration(), "h2");
        StatusDao dao = db.onDemand(StatusDao.class);

        environment.addResource(new RootResource());
        environment.addResource(new StatusResource(dao));
    }
}
