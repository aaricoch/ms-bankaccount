package pe.com.nttdbank.api;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import pe.com.nttdbank.api.model.responseCustomerApi;

@Path("/customer")
@RegisterRestClient
public interface CustomerApi {

    @GET
    @Path("/{id}")
    responseCustomerApi getById(@PathParam("id") String id);

    @GET
    List<responseCustomerApi> getAll();

}
