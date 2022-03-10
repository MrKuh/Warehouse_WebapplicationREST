package at.htlkaindorf.warehouse_webapplicationrest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;

// /api/customer/2

@Path("/customers")
public class WebDataResource {

    //@Context
    //UriInfo uriInfo;

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) {
        return null;
    }

    //HTTP Methods
    //GET, POST, PUT, DELETE (CRUD-Operations)
    //Create -> POST
    //Read -> GET
    //Update -> PUT
    //Delete -> DELETE

    /*@GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) {
        try {
            Customer customer = CustomerDB.getInstance().getCustomer(id);
            return Response.ok(customer).build();
            //return Response.status(Response.Status.OK).entity(customer).build();
        } catch (NoSuchElementException nsee) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // /api/customers
    @GET
    @Produces("application/json")
    public List<Customer> getAllCustomer() {
        return CustomerDB.getInstance().getCustomerList();
    }

    // /api/customers/42
    @DELETE
    @Produces("application/json")
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        try {
            Customer customer = CustomerDB.getInstance().deleteCustomer(id);
            return Response.ok(customer).build();
            //return Response.status(Response.Status.OK).entity(customer).build();
        } catch (NoSuchElementException nsee) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //Bsp.: Customer mit ID 7
    @POST
    public Response addCustomer(Customer customer) {
        try {
            CustomerDB.getInstance().addCustomer(customer);

            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path("" + customer.getId());

            //http://localhost:8080/custmgmt-1.0-SNAPSHOT/api/customers/7

            return Response.created(uriBuilder.build()).entity(customer).build();
        } catch (KeyAlreadyExistsException kaee) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    public Response replaceCustomer(Customer customer){
        try {
            CustomerDB.getInstance().replaceCustomer(customer);

            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path("" + customer.getId());

            //http://localhost:8080/custmgmt-1.0-SNAPSHOT/api/customers/7

            return Response.created(uriBuilder.build()).entity(customer).build();
        } catch (NoSuchElementException nsee) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    */
}