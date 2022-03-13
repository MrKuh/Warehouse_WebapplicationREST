package at.htlkaindorf.warehouse_webapplicationrest;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// /api/customer/2

@Path("/pick")
public class WebDataResource {

    @GET
    @Produces("application/json")
    public Response getData() {
        try {
            return Response.ok().entity(WebDataBase.getInstance().getData()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces("application/json")
    public Response getNewData() {
        try {
            return Response.ok().entity(WebDataBase.getInstance().getNewData()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static void main(String[] args) {
        int active = 1;
        List<Pick> picks = WebDataBase.getInstance().getPicks(active);
        Map<String, Pick> data = new HashMap<String, Pick>();
        for (int i = 0; i < 6; i++) {
            data.clear();
            if(picks.size() == 1){
                active++;
                picks = WebDataBase.getInstance().getPicks(active);
                for (Pick pick:picks) {
                    pick.setDestination(active);
                }
                data.put("active", picks.get(0));
                data.put("next", picks.get(1));
            }else{
                data.put("active", picks.get(1));
                data.put("next", picks.get(2));
                picks.remove(0);
            }
            System.out.println("data");
            System.out.println(data);
        }
    }

    //@Context
    //UriInfo uriInfo;

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