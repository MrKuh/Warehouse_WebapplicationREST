package at.htlkaindorf.warehouse_webapplicationrest;

import at.htlkaindorf.warehouse_webapplicationrest.db.WebDataBase;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.lang.reflect.Array;
import java.util.*;

// /api/customer/2

@Path("/pick")
public class PickResource {

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

    @POST
    @Path("/reverse")
    @Produces("application/json")
    public Response getLastPick() {
        try {
            return Response.ok().entity(WebDataBase.getInstance().getLastPick()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/setSummary")
    public Response setSummary() {
        try {
            WebDataBase.getInstance().setSummary();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/getSummary")
    @Produces("application/json")
    public Response getSummary() {
        try {
            return Response.ok().entity(WebDataBase.getInstance().getSummary()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/skipOrder")
    public Response skipOrder() {
        try {
            WebDataBase.getInstance().cancelOrderData();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @POST
    @Path("/redoPicks")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response redoPicks(ArrayList<Integer> picks) {
        try {

            System.out.println(picks);
            System.out.println(WebDataBase.getInstance().redoPicks(picks));

            /*
            HashMap<?, ?> dataMap = ((HashMap<?, ?>) data);
            ArrayList<Integer> pickList = ( (ArrayList) dataMap.get("picks"));
            int orderNumber = (int) dataMap.get("orderNumber");

             */

            //System.out.println(pickList);
            //System.out.println(orderNumber);

            //System.out.println(orderNumber);
            //
            //WebDataBase.getInstance().redoPicks(picks);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static void main(String[] args) {
        /*
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

         */
    }
}