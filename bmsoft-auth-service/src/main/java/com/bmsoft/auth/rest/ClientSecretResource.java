package com.bmsoft.auth.rest;

import com.bmsoft.auth.dto.ApiClientDTO;
import com.bmsoft.auth.service.ClientSecretService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author keets
 * @date 2017/9/18
 */
@Path("/")
public class ClientSecretResource {

    @Autowired
    private ClientSecretService clientSecretService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/clients")
    public Response createClient(ApiClientDTO apiClientDTO) {
        clientSecretService.createClientSecret(apiClientDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/clients/{clientId}")
    public Response getClient(@PathParam("clientId") String clientId) {
        ApiClientDTO apiClientDTO = clientSecretService.getClientSecretByClientId(clientId);
        return Response.ok(apiClientDTO).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/clients/{clientId}")
    public Response updateClient(@PathParam("clientId") String clientId, ApiClientDTO apiClientDTO) {
        clientSecretService.updateClientSecret(apiClientDTO);
        return Response.ok().build();
    }
}
