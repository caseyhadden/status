package com.caseyhadden.status.db;

import com.caseyhadden.status.api.Status;

import com.yammer.dropwizard.json.Json;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

@BindingAnnotation(StatusBind.StatusBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface StatusBind {

    static class StatusBinderFactory implements BinderFactory {
        static final Json json = new Json();

        @Override
        public Binder build(Annotation annotation) {
            return new Binder<StatusBind, Status>() {
                @Override
                public void bind(SQLStatement<?> q, StatusBind bind, Status status) {
                    q.bind("id", status.getId());
                    q.bind("json", json.writeValueAsString(status));
                }
            };
        }
    }

}
