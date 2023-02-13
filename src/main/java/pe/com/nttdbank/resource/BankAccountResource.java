package pe.com.nttdbank.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import pe.com.nttdbank.dto.BankAccountDto;
import pe.com.nttdbank.service.BankAccountService;

@Path("/ms-bankaccount/bankaccount")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BankAccountResource {

    @Inject
    BankAccountService bankAccountService;

    @GET
    public Response getAll() {
        List<BankAccountDto> bankAccounts = bankAccountService.getAll();
        return Response.ok(bankAccounts).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long Id) {
        BankAccountDto bankAccountDto = bankAccountService.getById(Id);
        if (bankAccountDto == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(bankAccountDto).build();
    }

    @POST
    public Response create(BankAccountDto bankAccountDto) {
        if (bankAccountService.Create(bankAccountDto)) {
            return Response.ok().status(Status.CREATED).build();
        }
        return Response.status(Status.CONFLICT).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long Id) {
        if (bankAccountService.Delete(Id)) {
            return Response.noContent().build();
        }
        return Response.ok().status(Status.CONFLICT).build();
    }
}
