package at.htlkaindorf.warehouse_webapplicationrest;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;
import at.htlkaindorf.warehouse_webapplicationrest.db.WebDataBase;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

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