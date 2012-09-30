package com.caseyhadden.status.resources;

import com.caseyhadden.status.api.Status;
import com.caseyhadden.status.db.StatusDao;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.*;

@Path("/statuses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatusResource {

    @Context
    UriInfo context;
    StatusDao dao;

    public StatusResource(StatusDao dao) {
        this.dao = dao;
    }

    @GET
    public Collection<Status> list() {
        return dao.findALl();
    }

    @GET
    @Path("{id}")
    public Status get(@PathParam("id") String id) {
        Status status = dao.findById(id);
        if (null == status)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return status;
    }

    @POST
    public Response create(Status status) {
        status.setId(UUID.randomUUID().toString());
        status.setDate(new Date());
        dao.insert(status);
        return Response.created(URI.create(String.valueOf(status.getId()))).build();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        dao.delete(id);
    }

    @DELETE
    public void deleteAll() {
        dao.deleteAll();
    }

}
