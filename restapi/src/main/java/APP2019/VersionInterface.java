package APP2019;

import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/books")
public class VersionInterface {

    /*@GET
    @Produces({ MediaType.APPLICATION_JSON})
    public JSONObject getAll() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("version", "0.0.1");
            obj.put("date", "2019-09-23");
        }
        catch(Exception e) {
            System.out.println("Could not set version");
        }
        return obj;
    }*/

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String VersionGet() {
        String s = "some text";
        return s;
    }

    @GET
    @Path("/command1")
    @Produces({MediaType.TEXT_PLAIN})
    public String command1Ops() {
        // Write your DB logic here
        return "hhh";
    }


    //URL:http://localhost:8080/api/command2?param1=200&param2=3
    //@Path("/command2")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getDriversWithParams(@QueryParam("param1") String param1,
                                                  @QueryParam("param2") String param2) {
        // Write your DB logic here
        return "Param Value 1:" + param1+ "Param Value 2:" + param2;
    }


}

