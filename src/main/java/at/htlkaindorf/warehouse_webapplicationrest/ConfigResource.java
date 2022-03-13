package at.htlkaindorf.warehouse_webapplicationrest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/config")
public class ConfigResource {

    @GET
    @Produces("application/json")
    public Response getConfig() {
        try {
            Map<String, Integer> config = IOAccess.getConfig();
            return Response.ok().entity(config).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
