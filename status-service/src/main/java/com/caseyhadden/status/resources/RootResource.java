package com.caseyhadden.status.resources;

import com.caseyhadden.status.api.Link;

import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

    @Context
    UriInfo context;

    @GET
    public List<Link> list() {
        List<Link> result = new ArrayList<Link>();
        result.add(new Link("statuses",
            context.getAbsolutePathBuilder().path(StatusResource.class).build().toString(),
            HttpMethod.GET));
        return result;
    }


}
